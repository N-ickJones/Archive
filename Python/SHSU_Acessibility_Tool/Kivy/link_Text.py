# Author: Nicholas Jones
# Purpose: To improve accessibility with html
# Date: 07/013/2018
# Version: 1.0

from tempfile import mkstemp
from shutil import move
from os import fdopen, remove
import re

link = r'(<a[^>]*?>)((?:|.+?))(</a>)'
variable = []
matches = []


def search(m, m2):
    variable.append(m2)
    return m


def search_file(file_path):
    del variable[:]
    del matches[:]
    # TODO Don't think i need lines
    lines = []
    # Create a Temp File
    fh, abs_path = mkstemp()
    with fdopen(fh, 'w') as new_file:
        with open(file_path) as old_file:
            for line in old_file:
                # Search for Links
                re.sub(link, lambda m: search(m.group(), m.group(2)), line, flags=re.IGNORECASE)
                new_file.write(line)
                lines.append(line)
            for x in variable:
                count = 0
                for y in variable:
                    if x == y:
                        count = count + 1
                if count > 1 and x not in matches:
                    matches.append(x)
    # Remove the Original File
    remove(file_path)
    # Move to New File
    move(abs_path, file_path)
    return matches


def get_lines(file_path):
    lines = []
    with open(file_path) as old_file:
        for line in old_file:
            lines.append(line)
    return lines


def fix_lines(m1, m2, m3, change, name):
    if 'name' in change:
        m2 = name
        return m1 + m2 + m3

    elif 'title' in change:
        if 'title=' not in m1:
                input_name = ' title="' + name + '">'
                m1 = re.sub(r'>', input_name, m1, flags=re.IGNORECASE)
        return m1 + m2 + m3

    elif 'aria' in change:
        if 'aria-labelledby=' not in m1:
            input_name = ' aria-labelledby="' + name + '">'
            m1 = re.sub(r'>', input_name, m1, flags=re.IGNORECASE)
            return m1 + m2 + m3

    else:
        return m1 + m2 + m3


def fix(line, selected, name):
    line = re.sub(link, lambda m: fix_lines(m.group(1), m.group(2), m.group(3), selected, name), line,
                  flags=re.IGNORECASE)
    return line


def update_file(file_path, matched_line, matched_line_number):
    line_count = 0
    index = 0
    fh, abs_path = mkstemp()
    with fdopen(fh, 'w') as new_file:
        with open(file_path) as old_file:
            for line in old_file:
                if index < len(matched_line):
                    if line_count == matched_line_number[index]:
                        line = re.sub(link, matched_line[index], line,
                                      flags=re.IGNORECASE)
                        index += 1

                line_count += 1
                new_file.write(line)

    # Remove the Original File
    remove(file_path)
    # Move to New File
    move(abs_path, file_path)
