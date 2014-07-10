       ${parameter:-word}
              Use Default Values.  If parameter is unset or null, the expansion of word is substituted.  Otherwise, the value of parameter is sub‐
              stituted.
       ${parameter:=word}
              Assign Default Values.  If parameter is unset or null, the expansion of word is assigned to parameter.  The value  of  parameter  is
              then substituted.  Positional parameters and special parameters may not be assigned to in this way.
       ${parameter:?word}
              Display  Error  if  Null or Unset.  If parameter is null or unset, the expansion of word (or a message to that effect if word is not
              present) is written to the standard error and the shell, if it is not interactive, exits.  Otherwise, the value of parameter is sub‐
              stituted.
       ${parameter:+word}
              Use Alternate Value.  If parameter is null or unset, nothing is substituted, otherwise the expansion of word is substituted.
       ${parameter:offset}
       ${parameter:offset:length}
              Substring  Expansion.   Expands to up to length characters of parameter starting at the character specified by offset.  If length is
              omitted, expands to the substring of parameter starting at the character specified by offset.   length  and  offset  are  arithmetic
              expressions  (see ARITHMETIC EVALUATION below).  If offset evaluates to a number less than zero, the value is used as an offset from
              the end of the value of parameter.  If length evaluates to a number less than zero, and parameter is not @ and  not  an  indexed  or
              associative array, it is interpreted as an offset from the end of the value of parameter rather than a number of characters, and the
              expansion is the characters between the two offsets.  If parameter is @, the result is length  positional  parameters  beginning  at
              offset.   If  parameter is an indexed array name subscripted by @ or *, the result is the length members of the array beginning with
              ${parameter[offset]}.  A negative offset is taken relative to one greater than the maximum index of the specified array.   Substring
              expansion  applied to an associative array produces undefined results.  Note that a negative offset must be separated from the colon
              by at least one space to avoid being confused with the :- expansion.  Substring indexing is zero-based unless the positional parame‐
              ters  are  used,  in  which case the indexing starts at 1 by default.  If offset is 0, and the positional parameters are used, $0 is
              prefixed to the list.

