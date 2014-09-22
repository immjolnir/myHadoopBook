startStop=$1
usage="Usage:  $0 start|stop"

case $startStop in
(start)
   echo "start: line starts with space";
	echo "start: line starts with tab"
   ;;

(stop)
	echo "stop: line starts with tab"
   echo "stop: line starts with 2 spaces"
  ;;
(*)
   echo $usage
	;;
esac

echo "case-in-esac done"

case $startStop in
start)
   echo "*start: line starts with space";
	echo "*start: line starts with tab"
   ;;

stop)
	echo "***stop: line starts with tab"
   echo "**stop: line starts with 2 spaces"
  ;;
(*)
	echo "** $usage"
	;;
esac

#The usage as the above is OK to run. the first usage is learned from hadoop-1.2.1/bin/hadoop-daemon.sh
