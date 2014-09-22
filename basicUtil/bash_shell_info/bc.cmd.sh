bc
 NUMBERS
       The  most  basic element in bc is the number.  Numbers are arbitrary precision numbers.  This precision is both in the integer part and the
       fractional part.  All numbers are represented internally in decimal and all computation  is  done  in  decimal.   (This  version  truncates
       results from divide and multiply operations.)  There are two attributes of numbers, the length and the scale.  The length is the total num‐
       ber of significant decimal digits in a number and the scale is the total number of decimal digits after the decimal point.  For example:
               .000001 has a length of 6 and scale of 6.
               1935.000 has a length of 7 and a scale of 3.

   VARIABLES
       Numbers are stored in two types of variables, simple variables and arrays.  Both simple variables and array  variables  are  named.   Names
       begin  with a letter followed by any number of letters, digits and underscores.  All letters must be lower case.  (Full alpha-numeric names
       are an extension. In POSIX bc all names are a single lower case letter.)  The type of variable is clear by the context  because  all  array
       variable names will be followed by brackets ([]).

       There  are  four  special  variables, scale, ibase, obase, and last.  scale defines how some operations use digits after the decimal point.
       The default value of scale is 0. ibase and obase define the conversion base for input and output numbers.  The default for both  input  and
       output  is  base  10.  last (an extension) is a variable that has the value of the last printed number.  These will be discussed in further
       detail where appropriate.  All of these variables may have values assigned to them as well as used in expressions.



[zhishan@lakala bin]$ bc
bc 1.06.95
Copyright 1991-1994, 1997, 1998, 2000, 2004, 2006 Free Software Foundation, Inc.
This is free software with ABSOLUTELY NO WARRANTY.
For details type `warranty'. 
scale=10;
1/3
.3333333333
/*this is comment*/
2/4
.5000000000
last
.5000000000
#single line comment
2*3.1415926^2
19.7392081286
3.1415926^2
9.8696040643

