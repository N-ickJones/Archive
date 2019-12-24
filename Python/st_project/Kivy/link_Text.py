# link_Text.py

# Purpose: GUI for Accessibility Tool
# Author: Nicholas Jones
# Date: 10.27.2018
# Version: 2.0

from tempfile import mkstemp
from shutil import move
from os import fdopen, remove
import re


class LinkText:

    def __init__(self):
        self.__link = r'(<a[^>]*?>)((?:|.+?))(</a>)'
        self.__variable = []
        self.__matches = []

    # Got a False Positive with <h3>Course Evaluation Rubrics and Example Documentation          </h3>

    def __search(self, m, m2):
        self.__variable.append(m2)
        return m

    def search_file(self, file_path):
        del self.__variable[:]
        del self.__matches[:]
        # TODO Don't think i need lines self.__Variable
        lines = []
        # Create a Temp File
        fh, abs_path = mkstemp()
        with fdopen(fh, 'w') as new_file:
            with open(file_path) as old_file:
                for line in old_file:
                    # Search for Links
                    re.sub(self.__link, lambda m: self.__search(m.group(), m.group(2)), line, flags=re.IGNORECASE)
                    new_file.write(line)
                    lines.append(line)
                for x in self.__variable:
                    count = 0
                    for y in self.__variable:
                        if x == y:
                            count = count + 1
                    if count > 1 and x not in self.__matches:
                        self.__matches.append(x)
        # Remove the Original File
        remove(file_path)
        # Move to New File
        move(abs_path, file_path)
        return self.__matches

    @staticmethod
    def get_lines(file_path):
        lines = []
        with open(file_path) as old_file:
            for line in old_file:
                lines.append(line)
        return lines

    @staticmethod
    def __fix_lines(m1, m2, m3, change, name):
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

    def fix(self, line, selected, name):
        # print line
        line = re.sub(self.__link, lambda m: self.__fix_lines(m.group(1), m.group(2), m.group(3), selected, name), line,
                      flags=re.IGNORECASE)
        # print line
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
                    new_file.write(line)
        # Remove the Original File
        remove(file_path)
        # Move to New File
        move(abs_path, file_path)
