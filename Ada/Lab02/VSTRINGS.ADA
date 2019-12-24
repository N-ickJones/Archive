-- The package VSTRINGS was written to provide convenient varying-length
-- string operations. This package is available at the directory
-- RES1:[csc.data.ji.cs165]

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

-- =====================================================================

package body VSTRINGS is

   procedure GET(S: out VSTRING) is
      -- This procedure reads a varying-length string from the standard
      -- input stream. The string should be terminated with an end-of-
      -- line.

      BUFFER: STRING(1..MAX_LENGTH);
      LAST: NATURAL;

   begin -- GET

      TEXT_IO.GET_LINE(BUFFER, LAST);
      IF LAST = 0 THEN
         TEXT_IO.GET_LINE(BUFFER, LAST);
      END IF;
      S := (LAST, BUFFER(1..LAST));
   end GET;

-- ---------------------------------------------------------------------
   procedure PUT(S: in VSTRING) is
      -- Given a varying-length string, this procedure writes it to the
      -- standard output stream.
   begin
      TEXT_IO.PUT(S.STR);
   end PUT;

-- ---------------------------------------------------------------------
   procedure PUT_LINE(S: in VSTRING) is
      -- Given a varying-length string, this procedure writes it to the
      -- standard output stream, and then moves to the next line.

   begin
      TEXT_IO.PUT_LINE(S.STR);
   end PUT_LINE;

-- ---------------------------------------------------------------------
   procedure VCREATE(FILE: in out TEXT_IO.FILE_TYPE;
                    MODE: in TEXT_IO.FILE_MODE := TEXT_IO.OUT_FILE;
                    NAME: in VSTRING) is
      -- Create a file named NAME
   begin
      TEXT_IO.CREATE(FILE, MODE, NAME.STR);
   end VCREATE;

-- ---------------------------------------------------------------------
   procedure VOPEN(FILE: in out TEXT_IO.FILE_TYPE;
                    MODE: in TEXT_IO.FILE_MODE;
                    NAME: in VSTRING) is
      -- Open a file named NAME
   begin

      TEXT_IO.OPEN(FILE, MODE, NAME.STR);
   end VOPEN;

-- ---------------------------------------------------------------------
   procedure VGET_LINE(FILE: in TEXT_IO.FILE_TYPE; S: out VSTRING) is
      -- This procedure reads a line of the varying-length string
      -- from the file FILE to S.
      BUFFER: STRING(1..MAX_LENGTH);
      LAST: NATURAL;
   begin -- GET_LINE
      TEXT_IO.GET_LINE(FILE, BUFFER, LAST);
      IF LAST = 0 THEN
         TEXT_IO.GET_LINE(FILE, BUFFER, LAST);
      END IF;
      S := (LAST, BUFFER(1..LAST));
   end VGET_LINE;

-- ---------------------------------------------------------------------
   procedure VPUT_LINE(FILE: in TEXT_IO.FILE_TYPE; S: in VSTRING) is

      -- Given a varying-length string, this procedure writes it to the
      -- file FILE as one line.
   begin
      TEXT_IO.PUT_LINE(FILE, S.STR);
   end VPUT_LINE;

-- ---------------------------------------------------------------------
   function LENGTH(S: VSTRING) return NATURAL is
      -- Given a varying-length string, this function returns its length.
   begin
      return S.LEN;
   end LENGTH;

-- ---------------------------------------------------------------------
   function "&"(LEFT, RIGHT: VSTRING) return VSTRING is
      -- Given two varying-length strings, this operator concatenates
      -- them.
   begin
      return(LEFT.LEN + RIGHT.LEN, LEFT.STR & RIGHT.STR);

   end "&";

-- ---------------------------------------------------------------------
   function SUB(
         S: VSTRING;
         START: INTEGER;
         SUBLEN: INTEGER := INTEGER'LAST
               ) return VSTRING is
      -- Given a varying-length string, a start position, and a length, this
      -- function returns the requested substring.

      FINISH: INTEGER;
      ACTUAL_SUBLEN: INTEGER;
      LEN_S: constant INTEGER := LENGTH(S);
      TEMP: STRING(1..MAX_LENGTH);

   begin -- SUB
      -- Test for special cases
      if SUBLEN <= 0 or else START <= 0 or else START > LEN_S

      then return NULL_STRING;
      end if;

      -- Calculate length of substring
      if SUBLEN = INTEGER'LAST
      then FINISH := LEN_S;
      else FINISH := START + SUBLEN - 1;
         if FINISH > LEN_S
         then FINISH := LEN_S;
         end if;
      end if;
      ACTUAL_SUBLEN := FINISH - START + 1;

      -- Return substring
      for i in 1..ACTUAL_SUBLEN loop
      TEMP(i) := S.STR(i + START - 1);
      end loop;
      return(VSTR(TEMP(1..ACTUAL_SUBLEN)));
   end SUB;


-- ---------------------------------------------------------------------
   function MATCH(S, PATTERN: VSTRING) return NATURAL is
      -- Given a varying-length string in which to search, and a pattern,
      -- this function returns the position of the first occurrence of the
      -- pattern in the given string. If the pattern is not found, 0 is
      -- returned.

      LEN_PAT: constant INTEGER := LENGTH(PATTERN);

   begin -- MATCH
      -- Special cases:
      if S = NULL_STRING or PATTERN = NULL_STRING then
         return 0;
      end if;

      -- Establish loop to search for pattern
      for K in 1..LENGTH(S) - LEN_PAT + 1
         loop

            -- Does it match at this position?
            if SUB(S, K, LEN_PAT) = PATTERN then
               return K;      -- Found it
            end if;
         end loop;

      -- Pattern has not been found
      return 0;
   end MATCH;

-- ---------------------------------------------------------------------
   -- Comparison operations for varying-length strings:

   function "<"(LEFT, RIGHT: VSTRING) return BOOLEAN is
   begin
      return LEFT.STR < RIGHT.STR;
   end "<";

   function "<="(LEFT, RIGHT: VSTRING) return BOOLEAN is

   begin
      return LEFT.STR <= RIGHT.STR;
   end "<=";

   function ">"(LEFT, RIGHT: VSTRING) return BOOLEAN is
   begin
      return LEFT.STR > RIGHT.STR;
   end ">";

   function ">="(LEFT, RIGHT: VSTRING) return BOOLEAN is
   begin
      return LEFT.STR >= RIGHT.STR;
   end ">=";

-- ---------------------------------------------------------------------
   procedure SUBSTITUTE(
         S: in out VSTRING;          -- String operated on
         START: in INTEGER;          -- Start of substring
         SUBLEN: in INTEGER;         -- Length of substring

         REPLACEMENT: in VSTRING     -- Replacement for substring
                       ) is
      --- Given a string to operate on, start position, a length, and a
      -- new substring, this procedure replaces the specified substring
      -- with the new substring.
   begin
      S := SUB(S, 1, START - 1) & REPLACEMENT
           & SUB(S, START + SUBLEN);
   end SUBSTITUTE;

-- ---------------------------------------------------------------------
   function VSTR(S: STRING) return VSTRING is
      -- Given a fixed-length string, this function returns the equivalent
      -- varying-length string.
   begin
      return(S'LENGTH, S);
   end VSTR;

   function VSTR(C: CHARACTER) return VSTRING is

      -- Given a character, this function returns the equivalent varying-
      -- length string.
   begin
      return(1, (1=> C));
   end VSTR;

   function STRNG(S: VSTRING; LEN: POSITIVE) return STRING IS
      -- Given a vstring, S, this function returns its first LEN characters.
   begin
      return S.STR(1..LEN);
   end STRNG;

-- ---------------------------------------------------------------------
   function  EXTRACT_CHAR(S: VSTRING; POSITION: NATURAL)
      return CHARACTER is
      -- Given a varying-length string and a position, this function
      -- returns the character found at that position within the string.
   begin
      return S.STR(POSITION);

   end EXTRACT_CHAR;

end VSTRINGS;



