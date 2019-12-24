with TEXT_IO;
package VSTRINGS is

   MAX_LENGTH: constant := 500;  -- Maximum length of a string
   subtype LENGTH_SUBTYPE is INTEGER range 0..MAX_LENGTH;

   type VSTRING(LEN:LENGTH_SUBTYPE := 0) is private;
   NULL_STRING: constant VSTRING;

   procedure GET(S: out VSTRING);

   procedure PUT(S: in VSTRING);
   procedure PUT_LINE(S: in VSTRING);

   procedure VCREATE(FILE: in out TEXT_IO.FILE_TYPE;
                    MODE: in TEXT_IO.FILE_MODE := TEXT_IO.OUT_FILE;
                    NAME: in VSTRING);
   procedure VOPEN(FILE: in out TEXT_IO.FILE_TYPE;
                    MODE: in TEXT_IO.FILE_MODE;
                    NAME: in VSTRING);
   procedure VCLOSE(FILE: in out TEXT_IO.FILE_TYPE) renames TEXT_IO.CLOSE;
   procedure VGET_LINE(FILE: in TEXT_IO.FILE_TYPE; S: out VSTRING);
   procedure VPUT_LINE(FILE: in TEXT_IO.FILE_TYPE; S: in VSTRING);

   function LENGTH(S: VSTRING) return NATURAL;
   function "&"(LEFT, RIGHT: VSTRING) return VSTRING;

   function SUB(S: VSTRING; START: INTEGER;
      SUBLEN: INTEGER := INTEGER'LAST) return VSTRING;


   function MATCH(S, PATTERN: VSTRING) return NATURAL;

   function "<"(LEFT, RIGHT: VSTRING) return BOOLEAN;
   function "<="(LEFT, RIGHT: VSTRING) return BOOLEAN;
   function ">"(LEFT, RIGHT: VSTRING) return BOOLEAN;
   function ">="(LEFT, RIGHT: VSTRING) return BOOLEAN;

   procedure SUBSTITUTE(S: in out VSTRING; START: in INTEGER;
      SUBLEN: in INTEGER; REPLACEMENT: in VSTRING);

   function VSTR(S: STRING) return VSTRING;
   function VSTR(C: CHARACTER) return VSTRING;
   function STRNG(S: VSTRING; LEN: POSITIVE) return STRING;

   function  EXTRACT_CHAR(S: VSTRING; POSITION: NATURAL)
      return CHARACTER;

private
   type VSTRING(LEN:LENGTH_SUBTYPE := 0) is

   record
      STR: STRING(1..LEN);
   end record;
   NULL_STRING: constant VSTRING := (0, "");
end VSTRINGS;