import html_Format
import link_Text


source = 'workbench.html'

issue_1 = 'HTML is used to format content'
issue_2 = 'Link text used for multiple different destinations'
# issue_3 = 'Heading is missing text'
# issue_4 = 'iFrame is missing a description '
# issue_5 = 'Image with no alt attribute '
# issue_6 = ''
# issue_7 = ''
# issue_8 = ''
# issue_9 = ''
# issue_10 = ''

target = issue_1

if issue_1 in target:
    html_Format.repair_file(source)

if issue_2 in target:
    link_Text.repair_file(source)

# if issue_3 in target:
    # html_Format.repair_file(source)

# if issue_4 in target:
    #  html_Format.repair_file(source)

# if issue_5 in target:
    # html_Format.repair_file(source)

# if issue_6 in target:
    # html_Format.repair_file(source)

# if issue_7 in target:
    # html_Format.repair_file(source)

# if issue_8 in target:
    # html_Format.repair_file(source)

# if issue_9 in target:
    # html_Format.repair_file(source)

# if issue_10 in target:
    # html_Format.repair_file(source)
