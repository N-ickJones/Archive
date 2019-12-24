# html_Format.py

# Purpose: GUI for Accessibility Tool
# Author: Nicholas Jones
# Date: 10.27.2018
# Version: 2.0

import re
from tempfile import mkstemp
from shutil import move
from os import fdopen, remove


class HtmlFormat:

    def __init__(self):

        # Regex Variables
        self.__tag_empty = r'(<[^\/!][^>|]*?>)((?:|.+?))(<[^>]*?>)'
        self.__tag = r'(<[^\/!][^>]*?>)'
        self.__style = r'style=(?:|\s+?|)\"([^\"]+?|)\"'
        self.__align = r'align=(?:|\s+?|)\"([^\"]+?|)\"'
        self.__width = r'width=(?:|\s+?|)\"([0-9|\s|px|%]+?)\"'
        self.__height = r'height=(?:|\s+?|)\"([0-9|\s|px]+?)\"'
        self.__clear = r'clear=(?:|\s+?|)\"([a-z]+|)\"'
        self.__frameborder = r'frameborder=(?:|\s+?|)\"([^\"]+?|)\"'
        self.__border = r'border=(?:|\s+?|)\"([^\"]+?|)\"'
        self.__cellpadding = r'cellpadding=(?:|\s+?|)\"([^\"]+?|)\"'
        self.__extra_spaces = r'\s\s+'
        self.__extra_semi_colons = r';;|;;+'
        self.__extra_colons = r'::|::+'
        self.__extra_style = r'style=(?:|\s+?|)\"(?:|\s+?)\"'
        self.__extra_space_end = r'\s+>'
        self.__extra_px = r'\s0px'
        self.__font_family = r'font-family:(?:|\s+|)([^\"]+?|);'
        self.__face = r'face=(?:|\s+?)\"([^\"]+?|)\"'
        self.__margin_width = r'marginwidth=(?:\s+?|)\"([^\"]+?|)\"'
        self.__margin_height = r'marginheight=(?:\s+?|)\"([^\"]+?|)\"'
        self.__sizcache = r'sizcache=(?:\s+?|)\"([^\"]+?|)\"'
        self.__sizset = r'sizset=(?:\s+?|)\"([^\"]+?|)\"'
        self.__value = ''
        self.__no_title_needed = \
            r'title=(?:|\s+?|)\"([^\"]+?|)\"|type=(?:|\s+?|)\"([\s]*?hidden[\s]*?)\"|type=(?:|\s+?|)\"([\s]*?submit[\s]*?)\"'
        self.__ntn_iframe = r'title=(?:|\s+?|)\"([^\"]+?|)\"'

        # Other Variables
        self.__fixes = 0
        self.__tabs = 0
        self.__change = False
        self.__up = False
        self.__down = False
        self.__variable = []

        self.structure_file('workbench.html')
        self.remove_blank_lines('workbench.html')

        # (<[^\s|/|>]*[\s|>]) Gets the opening tag Name
        # (<[^>]*?>)([^<]*) Gets Tag and Contents
        # (<[^>]*?>) Gets Both Opening and Closing Tags

    def __set_value(self, a):
        self.__value = a
        return ''

    # noinspection PyUnusedLocal
    def __style_fix(self, group, content, html_name, css_name):

        if 'width' in css_name or 'height' in css_name or 'border' in css_name or 'padding' in css_name:
            if re.search(r'\s', self.__value) is not None:
                value = re.sub(r'\s', '', self.__value)
            if self.__value.isdigit():
                self.__value = self.__value + 'px'

        if re.search(r'([^-]|^)' + css_name, content, re.IGNORECASE) is None:
            return 'style="' + content + '; ' + css_name + ': ' + self.__value + ';"'

        else:
            return group

    def __style_fix_clear(self, group, content, html_name, css_name):

        if html_name not in content:
            return 'style="' + content + '; ' + css_name + ': ' + self.__value + ';"'

        else:
            return group

    def __accessible(self, m):

        if 'style=' not in m:
            m = re.sub(r'/>|>', ' style="">', m, flags=re.IGNORECASE)
        else:
            pass

        if 'style' in m:  # has style
            if 'align=' in m:
                m = re.sub(self.__align, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                m = re.sub(self.__style, lambda s: self.__style_fix(s.group(), s.group(1), 'align', 'text-align'), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'width=' in m:
                m = re.sub(self.__width, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                m = re.sub(self.__style, lambda s: self.__style_fix(s.group(), s.group(1), 'width', 'width'), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'height=' in m:
                m = re.sub(self.__height, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                m = re.sub(self.__style, lambda s: self.__style_fix(s.group(), s.group(1), 'height', 'height'), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'clear=' in m:
                m = re.sub(self.__clear, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                m = re.sub(self.__style, lambda s: self.__style_fix_clear(s.group(), s.group(1), 'clear', 'clear'), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'frameborder=' in m:
                m = re.sub(self.__frameborder, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                m = re.sub(self.__style, lambda s: self.__style_fix(s.group(), s.group(1), 'frameborder', 'border'), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'border=' in m:
                m = re.sub(self.__border, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                m = re.sub(self.__style, lambda s: self.__style_fix(s.group(), s.group(1), 'border', 'border'), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'cellpadding=' in m:
                m = re.sub(self.__cellpadding, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                m = re.sub(self.__style, lambda s: self.__style_fix(s.group(), s.group(1), 'cellpadding', 'padding'), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'face=' in m:
                m = re.sub(self.__face, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                m = re.sub(self.__style, lambda s: self.__style_fix(s.group(), s.group(1), 'face', 'font-family'), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'marginwidth=' in m:
                m = re.sub(self.__margin_width, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'marginheight=' in m:
                m = re.sub(self.__margin_height, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'sizcache=' in m:
                m = re.sub(self.__sizcache, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                self.__fixes += 1

            if 'sizset=' in m:
                m = re.sub(self.__sizset, lambda a: self.__set_value(a.group(1)), m, flags=re.IGNORECASE)
                self.__fixes += 1

        if 'clear:' in m:
            m = re.sub(r'clear: all', 'clear: both', m, re.IGNORECASE)

        # These check for extra spaces and empty style tags
        m = re.sub(self.__extra_spaces, ' ', m, re.IGNORECASE)
        m = re.sub(self.__extra_style, '', m, re.IGNORECASE)
        m = re.sub(self.__extra_space_end, '>', m, re.IGNORECASE)

        return m

    def __empty_tags(self, m, m1, m2, m3):
        valid_tag = r'<h\d'
        valid_closing_tag = r'</h\d'
        if re.search(valid_tag, m1) is not None:
            if re.search(valid_closing_tag, m3) is not None:
                if re.search(r'.', m2) is None:
                    m = re.sub(r'.+', '', m)
                    self.__fixes += 1
        return m

    def repair_file(self, file_path):
        # Create a Temp File
        self.__fixes = 0
        fh, abs_path = mkstemp()
        with fdopen(fh, 'w') as new_file:
            with open(file_path) as old_file:
                for line in old_file:
                    # TODO structure content
                    line = re.sub(self.__tag_empty, lambda m: self.__empty_tags(m.group(), m.group(1), m.group(2), m.group(3)),
                                  line, flags=re.IGNORECASE)
                    line = re.sub(self.__tag, lambda m: self.__accessible(m.group(1)), line, flags=re.IGNORECASE)
                    line = re.sub(r'";', '"', line, flags=re.IGNORECASE)
                    line = re.sub(self.__extra_semi_colons, ';', line, flags=re.IGNORECASE)
                    line = re.sub(self.__extra_px, ' 0', line, flags=re.IGNORECASE)
                    if 'font-family' in line:
                        line = re.sub(self.__font_family, 'font-family: ' + r"\1" + ', sans-serif;', line,
                                      flags=re.IGNORECASE)
                    if not line.strip():
                        continue
                    new_file.write(line)
        # Remove the Original File
        remove(file_path)
        # Move to New File
        move(abs_path, file_path)
        return self.__fixes

    @staticmethod
    def remove_blank_lines(file_path):
        fh, abs_path = mkstemp()
        with fdopen(fh, 'w') as new_file:
            with open(file_path) as old_file:
                for line in old_file:
                    if not line.strip():
                        continue
                    new_file.write(line)
        # Remove the Original File
        remove(file_path)
        # Move to New File
        move(abs_path, file_path)

    @staticmethod
    def check_tag(m):
        return m

    def __structure_tags(self, m):
        tab_value = ''
        if '</' in m and '</a>' not in m:
            self.__tabs -= 1
            for i in range(0, self.__tabs, 1):
                tab_value += '\t'
            input_value = '\n' + tab_value + '<'
            m = re.sub(r'<', input_value, m, flags=re.IGNORECASE)

        elif '</a>' not in m:
            for i in range(0, self.__tabs, 1):
                tab_value = '\t'
            input_value = '\n' + tab_value + '<'
            # input_value = '\n' + '<'
            m = re.sub(r'<', input_value, m, flags=re.IGNORECASE)
            if '/>' in m:
                self.__tabs -= 1
                self.__tabs += 1
        return m

    def structure_file(self, file_path):
        self.__tabs = 0
        fh, abs_path = mkstemp()
        with fdopen(fh, 'w') as new_file:
            with open(file_path) as old_file:
                for line in old_file:
                    # TODO IF it contains internal content then keep on the same line otherwise return
                    line = re.sub(r'(<[^>]*?>)', lambda m: self.__structure_tags(m.group(1)), line, flags=re.IGNORECASE)
                    new_file.write(line)
        # Remove the Original File
        remove(file_path)
        # Move to New File
        move(abs_path, file_path)
