# -*- coding: utf-8 -*-
"""
Author: Nicholas Jones
Purpose: This Program reads, formats, and processes HTML
Date: 08/02/2018
Version: 1.0
"""
from __future__ import print_function
import re
# Boolean
start_new = False
# Integers
index = last_index = fixes = num_tags = num_attr = j = 0
# Strings
input_file, output_file, data = 'workbench.html', 'testCenterOut.html', ''
# Lists
full, tags, attr, attr_full, name, prop, elem, text, comm, close_tags = ([] for i in range(10))
self_closing_tags = ['area', 'base', 'br', 'col', 'com', 'embed', 'hr', 'img', 'input',
                     'keygen', 'link', 'menuitem', 'meta', 'param', 'source', 'track', 'wbr']
# Depreciated Tag, CSS Value # If clear: all then change to clear both
dep_tags = [['align', 'text-align'], ['width', 'width'], ['height', 'height'], ['clear', 'clear'],
            ['frameborder', 'border'], ['border', 'border'], ['cellpadding', 'padding'], ['face', 'font-family'],
            ['marginwidth', ''], ['marginheight', ''], ['sizcache', ''], ['sizset', '']]
# Regex Variables
search_tags = re.compile(r'<([^\s!/]+) ([^>]+)>([^<]+)?|<!--(.+?)-->|</([^>]+)>', flags=re.IGNORECASE)
search_attr = re.compile(r'([^\s]+?)?=\"(.+?)\"|([^\s]+)', flags=re.IGNORECASE)
tag_empty = re.compile(r'(<[^!][^>|]*?>)((?:|.+?))(<[^>]*?>)', flags=re.IGNORECASE)
tag = re.compile(r'(<[^!][^>]*?>)', flags=re.IGNORECASE)
style = re.compile(r'style=(?:|\s+?)\"([^\"]+?|)\"', flags=re.IGNORECASE)
align = re.compile(r'align=(?:|\s+?)\"([^\"]+?|)\"', flags=re.IGNORECASE)
width = re.compile(r'width=(?:|\s+?)\"([0-9|\spx%]+?)\"', flags=re.IGNORECASE)
height = re.compile(r'height=(?:|\s+?)\"([0-9|\spx%]+?)\"', flags=re.IGNORECASE)
clear = re.compile(r'clear=(?:|\s+?)\"([a-z]+|)\"')
frameborder = re.compile(r'frameborder=(?:|\s+?)\"([^\"]+?|)\"', flags=re.IGNORECASE)
border = re.compile(r'border=(?:|\s+?)\"([^\"]+?|)\"')
cellpadding = re.compile(r'cellpadding=(?:|\s+?)\"([^\"]+?|)\"', flags=re.IGNORECASE)
extra_spaces = re.compile(r'\s\s+', flags=re.IGNORECASE)
extra_semi_colons = re.compile(r';;|;;+', flags=re.IGNORECASE)
extra_colons = re.compile(r'::|::+', flags=re.IGNORECASE)
extra_style = re.compile(r'style=(?:|\s+?)\"(?:|\s+?)\"', flags=re.IGNORECASE)
extra_space_end = re.compile(r'\s+>', flags=re.IGNORECASE)
extra_px = re.compile(r'\s0px', flags=re.IGNORECASE)
font_family = re.compile(r'font-family:(?:|\s+)([^\"]+?|);', flags=re.IGNORECASE)
face = re.compile(r'face=(?:|\s+?)\"([^\"]+?|)\"', flags=re.IGNORECASE)
margin_width = re.compile(r'marginwidth=(?:\s+?|)\"([^\"]+?|)\"', flags=re.IGNORECASE)
margin_height = re.compile(r'marginheight=(?:\s+?|)\"([^\"]+?|)\"', flags=re.IGNORECASE)
sizcache = re.compile(r'sizcache=(?:\s+?|)\"([^\"]+?|)\"', flags=re.IGNORECASE)
sizset = re.compile(r'sizset=(?:\s+?|)\"([^\"]+?|)\"', flags=re.IGNORECASE)
value = ''
no_title_needed = re.compile(
    r'title=(?:|\s+?)\"([^\"]+?|)\"|type=(?:|\s+?)\"([\s]*?hidden[\s]*?)\"|type=(?:|\s+?)\"([\s]*?submit[\s]*?)\"',
    flags=re.IGNORECASE)
ntn_iframe = re.compile(r'title=(?:|\s+?)\"([^\"]+?|)\"', flags=re.IGNORECASE)


# Start of Functions
def file_in(file_path):
    """Input's Data From HTML File"""
    global data
    with open(file_path, 'r') as file_reader:
        data = file_reader.read().replace('\n', '').replace('\t', '').replace(r'  ', '')


def split_op1(m, open_tag_content, attr_content, text_content, comm_content, close_tag_content):
    """Split's HTML Document Into Components"""
    global data, index
    if open_tag_content is not None:
        tags.insert(index, open_tag_content)
        full.append('open')
    else:
        tags.insert(index, None)
    if attr_content is not None:
        attr.insert(index, attr_content)
        full.append('attr')
    else:
        attr.insert(index, None)
    if text_content is not None:
        text.insert(index, text_content)
        full.append('text')
    else:
        text.insert(index, None)
    if comm_content is not None:
        comm.insert(index, comm_content)
        full.append('comm')
    else:
        comm.insert(index, None)
    if close_tag_content is not None:
        close_tags.insert(index, close_tag_content)
        full.append('clos')
    else:
        close_tags.insert(index, None)
    index += 1
    return m


def split_op2(m, m1, m2, m3, i):
    """Split's Attributes Into Components"""
    global j, start_new

    if start_new:
        # Makes a 2d List Array
        name.append([])
        prop.append([])
        elem.append([])
        start_new = False
    if m1 is not None:
        name[i].insert(j, m1)
    else:
        pass
    if m2 is not None:
        prop[i].insert(j, m2)
    else:
        pass
    if m3 is not None:
        elem[i].insert(j, m3)
    else:
        pass
    j += 1

    return m


def split_data():
    """This Split's HTML Document Into Components, and Split's Attributes Into Components"""
    global data, j, num_tags, num_attr, name, start_new
    re.sub(search_tags, lambda m: split_op1(m.group(), m.group(1), m.group(2), m.group(3), m.group(4), m.group(5)),
           data)
    for i in range(0, len(attr), 1):
        temp = str(attr[i])
        start_new = True
        re.sub(search_attr, lambda m: split_op2(m.group(), m.group(1), m.group(2), m.group(3), i), temp)
        j = 0


def empty_tags():
    """Removes Empty HTML Tags"""
    # TODO Empty Tags (Needs Text in Tags like p h1-h6 etc)
    pass


def accessibility():
    """Makes Tags Accessible To End-Users"""
    # TODO IF width, height or border with numeric value only then add px
    # TODO If clear with 'all' change to 'both'
    # TODO Add sans serif to font-family: css tag
    for i in range(0, len(name), 1):
        for h in range(0, len(name[i]), 1):
            for t in range(0, len(dep_tags), 1):
                if dep_tags[t][0] in name[i][h]:
                    name[i][h] = 'style'
                    prop[i][h] = str(dep_tags[t][1]) + ': ' + str(prop[i][h]) + ';'
                else:
                    pass
            # End Depreciated Tags For Loop
        # End # of Items for Entries For Loop
    # End # of Entries For Loop


def combine_styling():
    """Combines Attribute Changes To CSS Styling to One Single Inline Style"""
    pass


def attribute_fixes():
    """This Performs HTML Depreciation and Accessibility Checks"""
    empty_tags()
    accessibility()


def update_attributes():
    """Updates Attribute Changes From Name, Prop, Elem Lists into the Original Attr List"""
    for i in range(0, len(name), 1):
        has_style, attr_value, style_value = False, '', ''
        for h in range(0, len(name[i]), 1):
            if 'style' in name[i][h]:
                style_value += str(prop[i][h])
                has_style = True
            else:
                attr_value += (str(name[i][h]) + '="' + str(prop[i][h]) + '" ')
        if has_style:
            attr_value += 'style="' + style_value + '"'
        for h in range(0, len(elem[i]), 1):
            attr_value += (' ' + str(elem[i][h]))
        attr.insert(i, attr_value)


def add_tabs(tabs):
    """Returns Num of tabs as 4 spaces"""
    tab_value = ''
    # tab_type = '  '  # 2 Spaces
    tab_type = '    '  # 4 Spaces
    # tab_type = '\t'  # Tab Character
    for i in range(0, tabs - 1, 1):
        tab_value += tab_type
    return tab_value


def output_string():
    """Processes File Format and Builds The Data String"""
    global data
    code_value = ''
    tab_count = 1
    for i in range(0, num_tags, 1):
        self_close = False
        if tags[i] is not None:
            code_value += add_tabs(tab_count)
            for tag_item in self_closing_tags:
                if tag_item in tags[i]:
                    self_close = True
            if self_close is False:
                tab_count += 1
            code_value += '<'
            code_value += tags[i]
        if attr[i] is not None:
            code_value += ' '
            code_value += attr[i]
            code_value += '>\n'
        elif tags[i] is not None:
            code_value += '>\n'
        if text[i] is not None:
            code_value += add_tabs(tab_count)
            # tab_count += 1
            code_value += text[i]
            code_value += '\n'
        if comm[i] is not None:
            code_value += add_tabs(tab_count)
            # tab_count += 1
            code_value += '<!--'
            code_value += comm[i]
            code_value += '-->\n'
        if close_tags[i] is not None:
            tab_count -= 1
            code_value += add_tabs(tab_count)
            code_value += '</'
            code_value += close_tags[i]
            code_value += '>\n'

    data = code_value


def file_out(file_path):
    """Output's Data String to a File"""
    global data
    with open(file_path, 'w') as file_writer:
        file_writer.write(data)
        # print(data)


# Function Calls
file_in(input_file)
split_data()
empty_tags()
accessibility()
attribute_fixes()
combine_styling()
update_attributes()
# output_string()
# file_out(output_file)
