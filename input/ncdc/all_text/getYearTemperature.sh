awk '
    BEGIN { print "(YEAR, TEMPERATURE)"}
    { 
     year = substr($0, 16, 4); 
     temp = substr($0, 88, 5) + 0;	
     printf "(%s, %d)\n", year, temp
    }'
