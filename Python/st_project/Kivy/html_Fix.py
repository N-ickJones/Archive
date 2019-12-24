# html_Fix.py

# Purpose: GUI for Accessibility Tool
# Author: Nicholas Jones
# Date: 10.27.2018
# Version: 2.0

import re
from tempfile import mkstemp
from shutil import move
from os import fdopen, remove


class HtmlTool:

    def __init__(self):
        self.__tag = r'(<[^\/!][^>]*?>)'
        self.__no_title_needed = r'title=(?:|\s+?|)\"([^\"]+?|)\"|type=(?:|\s+?|)\"([\s]*?hidden[\s]*?)\"|type=(?:|\s+?|)\"([\s]*?submit[\s]*?)\"'
        self.__ntn_iframe = r'title=(?:|\s+?|)\"([^\"]+?|)\"'
        self.variable = []
        self.__matches = []

    def search(self, m):
        # IF Select, Input or Iframe without a Title then append
        if '<select' in m:
            if re.search(self.__no_title_needed, m, flags=re.IGNORECASE) is None:
                self.variable.append(m)
            else:
                pass

        elif '<input' in m:
            if re.search(self.__no_title_needed, m, flags=re.IGNORECASE) is None:
                self.variable.append(m)
            else:
                pass

        elif '<iframe' in m:
            if re.search(self.__ntn_iframe, m, flags=re.IGNORECASE) is None:
                self.variable.append(m)
            else:
                pass
        else:
            pass
        return m

    def search_file(self, file_path):
        del self.variable[:]
        # Create a Temp File
        fh, abs_path = mkstemp()
        with fdopen(fh, 'w') as new_file:
            with open(file_path) as old_file:
                for line in old_file:
                    # Search for self.__tags
                    re.sub(self.__tag, lambda m: self.search(m.group()), line, flags=re.IGNORECASE)
                    new_file.write(line)
        # Remove the Original File
        remove(file_path)
        # Move to New File
        move(abs_path, file_path)
        return self.variable

    @staticmethod
    def get_lines(file_path):
        lines = []
        with open(file_path) as old_file:
            for line in old_file:
                lines.append(line)
        return lines

    def fix_lines(self, m, name):
        if '<select' in m:
            if re.search(self.__no_title_needed, m, flags=re.IGNORECASE) is None:
                if 'skip' not in name:
                    name = 'title="' + name + '"'
                    m = re.sub(r'<select', '<select ' + name, m, flags=re.IGNORECASE)
            else:
                pass

        elif '<input' in m:
            if re.search(self.__no_title_needed, m, flags=re.IGNORECASE) is None:
                if 'skip' not in name:
                    name = 'title="' + name + '"'
                    m = re.sub(r'<input', '<input ' + name, m, flags=re.IGNORECASE)
            else:
                pass

        elif '<iframe' in m:
            if re.search(self.__ntn_iframe, m, flags=re.IGNORECASE) is None:
                if 'skip' not in name:
                    name = 'title="' + name + '"'
                    m = re.sub(r'<iframe', '<iframe ' + name, m, flags=re.IGNORECASE)
            else:
                pass
        else:
            pass
        return m

    def fix(self, line, name):
        line = re.sub(self.__tag, lambda m: self.fix_lines(m.group(1), name), line,
                      flags=re.IGNORECASE)
        return line

    @staticmethod
    def update_file(file_path, matched_line, matched_line_number):
        line_count = 0
        index = 0
        fh, abs_path = mkstemp()
        with fdopen(fh, 'w') as new_file:
            with open(file_path) as old_file:
                for line in old_file:
                    if index < len(matched_line):
                        if line_count == matched_line_number[index]:
                            line = re.sub(r'<.*>', matched_line[index], line,
                                          flags=re.IGNORECASE)
                            index += 1
                    line_count += 1
                    if not line.strip():
                        continue
                    new_file.write(line)
                    # TODO Check for Blank Lines
        # Remove the Original File
        remove(file_path)
        # Move to New File
        move(abs_path, file_path)
