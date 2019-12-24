# Purpose: Finds Tags That Need Titles
# Author: Nicholas Jones (SHSU Student Worker)
# Date: 07.27.2018
# Version: 1.0

import re
from tempfile import mkstemp
from shutil import move
from os import fdopen, remove

tag = r'(<[^\/!][^>]*?>)'
no_title_needed = \
    r'title=(?:|\s+?|)\"([^\"]+?|)\"|type=(?:|\s+?|)\"([\s]*?hidden[\s]*?)\"|type=(?:|\s+?|)\"([\s]*?submit[\s]*?)\"'
ntn_iframe = r'title=(?:|\s+?|)\"([^\"]+?|)\"'
variable = []
matches = []


def search(m):
    # IF Select, Input or Iframe without a Title then append
    if '<select' in m:
        if re.search(no_title_needed, m, flags=re.IGNORECASE) is None:
            variable.append(m)
        else:
            pass

    elif '<input' in m:
        if re.search(no_title_needed, m, flags=re.IGNORECASE) is None:
            variable.append(m)
        else:
            pass

    elif '<iframe' in m:
        if re.search(ntn_iframe, m, flags=re.IGNORECASE) is None:
            variable.append(m)
        else:
            pass
    else:
        pass
    return m


def search_file(file_path):
    del variable[:]
    # Create a Temp File
    fh, abs_path = mkstemp()
    with fdopen(fh, 'w') as new_file:
        with open(file_path) as old_file:
            for line in old_file:
                # Search for Tags
                re.sub(tag, lambda m: search(m.group()), line, flags=re.IGNORECASE)
                new_file.write(line)
    # Remove the Original File
    remove(file_path)
    # Move to New File
    move(abs_path, file_path)
    return variable


def get_lines(file_path):
    lines = []
    with open(file_path) as old_file:
        for line in old_file:
            lines.append(line)
    return lines


def fix_lines(m, name):
    if '<select' in m:
        if re.search(no_title_needed, m, flags=re.IGNORECASE) is None:
            if 'skip' not in name:
                name = 'title="' + name + '"'
                m = re.sub(r'<select', '<select ' + name, m, flags=re.IGNORECASE)
        else:
            pass

    elif '<input' in m:
        if re.search(no_title_needed, m, flags=re.IGNORECASE) is None:
            if 'skip' not in name:
                name = 'title="' + name + '"'
                m = re.sub(r'<input', '<input ' + name, m, flags=re.IGNORECASE)
        else:
            pass

    elif '<iframe' in m:
        if re.search(ntn_iframe, m, flags=re.IGNORECASE) is None:
            if 'skip' not in name:
                name = 'title="' + name + '"'
                m = re.sub(r'<iframe', '<iframe ' + name, m, flags=re.IGNORECASE)
        else:
            pass
    else:
        pass
    return m


def fix(line, name):
    line = re.sub(tag, lambda m: fix_lines(m.group(1), name), line,
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
                        print matched_line[index]
                        line = re.sub(line, matched_line[index], line,
                                      flags=re.IGNORECASE)
                        index += 1

                line_count += 1
                new_file.write(line)

    # Remove the Original File
    remove(file_path)
    # Move to New File
    move(abs_path, file_path)
