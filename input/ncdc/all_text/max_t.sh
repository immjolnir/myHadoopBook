awk '{ 
     temp = substr($0, 88, 5) + 0; 
     q = substr($0, 93, 1);	
     if ( temp!=9999 && q ~/[01459]/ && temp > max) { max = temp; qualityCode = q;}
  } 
  END {printf "temperature: %d, quality code: %d\n", max, q}'
