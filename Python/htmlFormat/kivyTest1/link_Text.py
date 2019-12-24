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
value = ''
aria_page = []

# change = 'name'
# change = 'title'
# change = 'aria-labelledby'


def search(m, m2):
    variable.append(m2)
    return m


def edit(m, m1, m2, m3, page_count, aria_page, aria_number):

    if m2 in matches:
        change = raw_input(m + "\n aria, name, or title? : ")
        if 'name' in change:
            input_name = raw_input('Rename [' + m + '] in ' + m + ' to: ')
            if input_name:
                m2 = input_name
            return m1 + m2 + m3

        elif 'title' in change:
            if 'title=' not in m1:
                input_name = raw_input('Add [' + 'title' + '] to ' + m + ' as: ')
                if input_name:
                    input_name = ' title="' + input_name + '">'
                    m1 = re.sub(r'>', input_name, m1, flags=re.IGNORECASE)
            return m1 + m2 + m3

        elif 'aria' in change:
            if 'aria-labelledby=' not in m1:
                input_name = raw_input('Add [' + 'aria-labelledby' + '] in ' + m1 + ' as: ')
                if input_name:
                    aria_page.append(page_count)
                    aria_number += 1
                    input_name = ' aria-labelledby="' + input_name + '">'
                    m1 = re.sub(r'>', input_name, m1, flags=re.IGNORECASE)

            return m1 + m2 + m3
    else:
        return m1 + m2 + m3


def add_id(m, m1, m2, m3):

    input_name = raw_input(m1 + "\n Add Id for Aria-Label : ")  # add the label variable

    if input_name:
        m2 = input_name

    return m1 + m2 + m3


def aria_id(file_path, aria_page):
    count = 0
    # Create a Temp File
    fh, abs_path = mkstemp()
    with fdopen(fh, 'w') as new_file:
        with open(file_path) as old_file:
            for line in old_file:
                for item in aria_page:
                    if count == item or count == (item - 1):
                        print line
                        user_input = raw_input('\n Add Aria Id Here? : ')
                        if 'yes' in user_input:
                            user_input = raw_input('\n Tag Name? : ')
                            user_input = "r'(<" + user_input + "[^>]*?>)((?:|.+?))(</" + user_input + ">)'"
                            line = re.sub(user_input, lambda m: add_id(m.group(), m.group(1), m.group(2), m.group(3)),
                                          line, flags=re.IGNORECASE)

            count += 1
            new_file.write(line)


    # Remove the Original File
    remove(file_path)
    # Move to New File
    move(abs_path, file_path)


def repair_file(file_path):
    count = 0
    page_count = 0

    aria_number = 0
    del variable[:]
    del matches[:]
    # Create a Temp File
    fh, abs_path = mkstemp()
    with fdopen(fh, 'w') as new_file:
        with open(file_path) as old_file:
            for line in old_file:
                re.sub(link, lambda m: search(m.group(), m.group(2)), line, flags=re.IGNORECASE)

            for x in variable:
                for y in variable:
                    if x == y:
                        count = count + 1

                if count > 1 and x not in matches:
                    matches.append(x)

            if matches:
                page_count = 0
                old_file.seek(0)
                for line in old_file:
                    print line
                    line = re.sub(link, lambda m: edit(m.group(), m.group(1), m.group(2), m.group(3), page_count,
                                                       aria_page, aria_number), line, flags=re.IGNORECASE)
                    page_count += 1
                    new_file.write(line)
                    print aria_page

    # Remove the Original File
    remove(file_path)
    # Move to New File
    move(abs_path, file_path)

    if aria_page:
        aria_id(file_path, aria_page)

    # for line in old_file:
    # line = re.sub(link, lambda m: edit(m.group(1), m.group(2)), line, flags=re.IGNORECASE)
    # new_file.write(line)
