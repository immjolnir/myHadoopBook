# haodop version 1.2.1

input=${1:-/user/zhishan/hadoop-book/ch02/input/sample.txt}
output=${2:-/user/zhishan/hadoop-book/ch02/output.stream}

$HADOOP_INSTALL/bin/hadoop fs -rmr -skipTrash $output
hadoop fs -text $input | wc --bytes

$HADOOP_INSTALL/bin/hadoop jar $HADOOP_INSTALL/./contrib/streaming/hadoop-streaming-1.2.1.jar \
#	-D mapred.reduce.tasks=0 \
	-input $input \
	-output $output \
	-mapper cat \
	-reducer "wc --bytes"
hadoop fs -text $output/part-00000 | wc -c

echo '
Usage: $HADOOP_HOME/bin/hadoop jar \
          $HADOOP_HOME/hadoop-streaming.jar [options]
Options:
  -input    <path>     DFS input file(s) for the Map step
  -output   <path>     DFS output directory for the Reduce step
  -mapper   <cmd|JavaClassName>      The streaming command to run
  -combiner <cmd|JavaClassName> The streaming command to run
  -reducer  <cmd|JavaClassName>      The streaming command to run
  -file     <file>     File/dir to be shipped in the Job jar file
  -inputformat TextInputFormat(default)|SequenceFileAsTextInputFormat|JavaClassName Optional.
  -outputformat TextOutputFormat(default)|JavaClassName  Optional.
  -partitioner JavaClassName  Optional.
  -numReduceTasks <num>  Optional.
  -inputreader <spec>  Optional.
  -cmdenv   <n>=<v>    Optional. Pass env.var to streaming commands
  -mapdebug <path>  Optional. To run this script when a map task fails 
  -reducedebug <path>  Optional. To run this script when a reduce task fails 
  -io <identifier>  Optional.
  -verbose

Generic options supported are
-conf <configuration file>     specify an application configuration file
-D <property=value>            use value for given property
-fs <local|namenode:port>      specify a namenode
-jt <local|jobtracker:port>    specify a job tracker
-files <comma separated list of files>    specify comma separated files to be copied to the map reduce cluster
-libjars <comma separated list of jars>    specify comma separated jar files to include in the classpath.
-archives <comma separated list of archives>    specify comma separated archives to be unarchived on the compute machines.

The general command line syntax is
bin/hadoop command [genericOptions] [commandOptions]


For more details about these options:
Use $HADOOP_HOME/bin/hadoop jar build/hadoop-streaming.jar -info
' > /dev/null
