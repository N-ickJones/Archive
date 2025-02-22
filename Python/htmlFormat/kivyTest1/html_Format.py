# Author: Nicholas Jones
# Purpose: To improve accessibility with html
# Date: 07/05/2018
# Version: 1.0

import kivyT
import re
from tempfile import mkstemp
from shutil import move
from os import fdopen, remove


# regex Variables


tag_empty = r'(<[^\/!][^>|]*?>)((?:|.+?))(<[^>]*?>)'
tag = r'(<[^\/!][^>]*?>)'
style = r'style=(?:|\s+?|)\"([^\"]+?|)\"'
align = r'align=(?:|\s+?|)\"([^\"]+?|)\"'
width = r'width=(?:|\s+?|)\"([0-9|\s|px]+?)\"'
height = r'height=(?:|\s+?|)\"([0-9|\s|px]+?)\"'
clear = r'clear=(?:|\s+?|)\"([a-z]+|)\"'
frameborder = r'frameborder=(?:|\s+?|)\"([^\"]+?|)\"'
border = r'border=(?:|\s+?|)\"([^\"]+?|)\"'
cellpadding = r'cellpadding=(?:|\s+?|)\"([^\"]+?|)\"'
extra_spaces = r'\s\s+'
extra_semi_colons = r';;|;;+'
extra_colons = r'::|::+'
extra_style = r'style=(?:|\s+?|)\"(?:|\s+?)\"'
extra_space_end = r'\s+>'
extra_px = r'\s0px'
font_family = r'font-family:(?:|\s+|)([^\"]+?|);'  # Need to figure out solution for no semi-colon case
face = r'face=(?:|\s+?)\"([^\"]+?|)\"'
margin_width = r'marginwidth=(?:\s+?|)\"([^\"]+?|)\"'
margin_height = r'marginheight=(?:\s+?|)\"([^\"]+?|)\"'
sizcache = r'sizcache=(?:\s+?|)\"([^\"]+?|)\"'
sizset = r'sizset=(?:\s+?|)\"([^\"]+?|)\"'
value = ''

# Functionality
no_title_needed = \
    r'title=(?:|\s+?|)\"([^\"]+?|)\"|type=(?:|\s+?|)\"([\s]*?hidden[\s]*?)\"|type=(?:|\s+?|)\"([\s]*?submit[\s]*?)\"'
ntn_iframe = r'title=(?:|\s+?|)\"([^\"]+?|)\"'


# Start Functions

def set_value(a):
    global value
    value = a
    return ''


def style_fix(group, content, html_name, css_name):

    global value

    if 'width' in css_name or 'height' in css_name or 'border' in css_name or 'padding' in css_name:
        if re.search(r'\s', value) is not None:
            value = re.sub(r'\s', '', value)
        if value.isdigit():
            value = value + 'px'

    if re.search(r'([^-]|^)' + css_name, content, re.IGNORECASE) is None:
        return 'style="' + content + '; ' + css_name + ': ' + value + ';"'

    else:
        return group


def style_fix_clear(group, content, html_name, css_name):

    if html_name not in content:
        return 'style="' + content + '; ' + css_name + ': ' + value + ';"'

    else:
        return group


def accessible(m):
    if 'style=' not in m:
        m = re.sub(r'/>|>', ' style="">', m, flags=re.IGNORECASE)

    if 'style' in m:  # has style
        if 'align=' in m:
            m = re.sub(align, lambda a:  set_value(a.group(1)), m, flags=re.IGNORECASE)
            m = re.sub(style, lambda s: style_fix(s.group(), s.group(1), 'align', 'text-align'), m, flags=re.IGNORECASE)

        if 'width=' in m:
            m = re.sub(width, lambda a:  set_value(a.group(1)), m, flags=re.IGNORECASE)
            m = re.sub(style, lambda s: style_fix(s.group(), s.group(1), 'width', 'width'), m, flags=re.IGNORECASE)

        if 'height=' in m:
            m = re.sub(height, lambda a:  set_value(a.group(1)), m, flags=re.IGNORECASE)
            m = re.sub(style, lambda s: style_fix(s.group(), s.group(1), 'height', 'height'), m, flags=re.IGNORECASE)

        if 'clear=' in m:
            m = re.sub(clear, lambda a: set_value(a.group(1)), m, flags=re.IGNORECASE)
            m = re.sub(style, lambda s: style_fix_clear(s.group(), s.group(1),
                                                        'clear', 'clear'), m, flags=re.IGNORECASE)

        if 'frameborder=' in m:
            m = re.sub(frameborder, lambda a: set_value(a.group(1)), m, flags=re.IGNORECASE)
            m = re.sub(style, lambda s: style_fix(s.group(), s.group(1),
                                                  'frameborder', 'border'), m, flags=re.IGNORECASE)

        if 'border=' in m:
            m = re.sub(border, lambda a: set_value(a.group(1)), m, flags=re.IGNORECASE)
            m = re.sub(style, lambda s: style_fix(s.group(), s.group(1), 'border', 'border'), m, flags=re.IGNORECASE)

        if 'cellpadding=' in m:
            m = re.sub(cellpadding, lambda a: set_value(a.group(1)), m, flags=re.IGNORECASE)
            m = re.sub(style, lambda s: style_fix(s.group(), s.group(1),
                                                  'cellpadding', 'padding'), m, flags=re.IGNORECASE)

        if 'face=' in m:
            m = re.sub(face, lambda a: set_value(a.group(1)), m, flags=re.IGNORECASE)
            m = re.sub(style, lambda s: style_fix(s.group(), s.group(1), 'face', 'font-family'), m, flags=re.IGNORECASE)

        if 'marginwidth=' in m:
            m = re.sub(margin_width, lambda a: set_value(a.group(1)), m, flags=re.IGNORECASE)

        if 'marginheight=' in m:
            m = re.sub(margin_height, lambda a: set_value(a.group(1)), m, flags=re.IGNORECASE)

        if 'sizcache=' in m:
            m = re.sub(sizcache, lambda a: set_value(a.group(1)), m, flags=re.IGNORECASE)

        if 'sizset=' in m:
            m = re.sub(sizset, lambda a: set_value(a.group(1)), m, flags=re.IGNORECASE)

    if 'clear:' in m:
        m = re.sub(r'clear: all', 'clear: both', m, re.IGNORECASE)

    # These check for extra spaces and empty style tags
    m = re.sub(extra_spaces, ' ', m, re.IGNORECASE)
    m = re.sub(extra_style, '', m, re.IGNORECASE)
    m = re.sub(extra_space_end, '>', m, re.IGNORECASE)

    return m


# Accessibility with Manual Changes
def functionality(m):
    if '<select' in m:
        if re.search(no_title_needed, m, flags=re.IGNORECASE) is None:
            select_title = 'title="' + raw_input('Enter a title for <select> tag[type skip to go to next] :') + '"'
            if 'skip' not in select_title:
                m = re.sub(r'<select', '<select ' + select_title, m, flags=re.IGNORECASE)

    if '<input' in m:
        if re.search(no_title_needed, m, flags=re.IGNORECASE) is None:
            print m
            input_title = 'title="' + raw_input('Enter a title for <input> tag type skip to go to next] :') + '"'
            if 'skip' not in input_title:
                m = re.sub(r'<input', '<input ' + input_title, m, flags=re.IGNORECASE)

    if '<iframe' in m:
        if re.search(ntn_iframe, m, flags=re.IGNORECASE) is None:
            print m
            input_title = 'title="' + raw_input('Enter a title for <input> tag type skip to go to next] :') + '"'
            if 'skip' not in input_title:
                m = re.sub(r'<iframe', '<iframe ' + input_title, m, flags=re.IGNORECASE)

    return m


def empty_tags(m, m1, m2, m3):

    valid_tag = r'<h\d'
    valid_closing_tag = r'</h\d'

    if re.search(valid_tag, m1) is not None:
        if re.search(valid_closing_tag, m3) is not None:
            if re.search(r'.', m2) is None:
                m = re.sub(r'.+', '', m)

    return m


def repair_file(file_path):
    # Create a Temp File
    fh, abs_path = mkstemp()
    with fdopen(fh, 'w') as new_file:
        with open(file_path) as old_file:
            for line in old_file:
                line = re.sub(tag_empty, lambda m: empty_tags(m.group(), m.group(1), m.group(2), m.group(3)),
                              line, flags=re.IGNORECASE)
                line = re.sub(tag, lambda m: accessible(m.group(1)), line, flags=re.IGNORECASE)
                line = re.sub(tag, lambda m: functionality(m.group(1)), line, flags=re.IGNORECASE)
                line = re.sub(r'";', '"', line, flags=re.IGNORECASE)
                # line = re.sub(r'"\s', '"', line, flags=re.IGNORECASE)
                line = re.sub(extra_semi_colons, ';', line, flags=re.IGNORECASE)
                # line = re.sub(extra_colons, ':', line, flags=re.IGNORECASE)
                line = re.sub(extra_px, ' 0', line, flags=re.IGNORECASE)
                if 'font-family' in line:
                    line = re.sub(font_family, 'font-family: ' + r"\1" + ', sans-serif;', line, flags=re.IGNORECASE)

                if not line.strip():
                    continue
                new_file.write(line)

    # Remove the Original File
    remove(file_path)
    # Move to New File
    move(abs_path, file_path)


# Function Calls
# repair_file('workbench.html')
