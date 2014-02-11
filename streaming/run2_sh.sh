# haodop version 1.2.1

input=${1:-/user/zhishan/hadoop-book/ch02/input/sample.txt}
output=${2:-/user/zhishan/hadoop-book/ch02/output.stream}

$HADOOP_INSTALL/bin/hadoop fs -rmr -skipTrash $output
hadoop fs -text $input | wc --bytes

$HADOOP_INSTALL/bin/hadoop jar $HADOOP_INSTALL/./contrib/streaming/hadoop-streaming-1.2.1.jar \
	-D mapred.mapoutput.key.class=org.apache.hadoop.io.LongWritable.class \
	-D mapred.mapoutput.value.class=org.apache.hadoop.io.Text.class \
	-D mapred.reduce.tasks=0 \
	-input $input \
	-output $output \
	-mapper org.apache.hadoop.mapred.lib.IdentityMapper \
	#-reducer "wc --bytes"
#hadoop fs -text $output/part-00000 | wc -c

echo " Done
To be backward compatible, Hadoop Streaming also supports the "-reduce NONE" option, which is equivalent to "-jobconf mapred.reduce.tasks=0"
">/dev/null
echo 'error:
java.io.IOException: Type mismatch in key from map: expected org.apache.hadoop.io.Text, recieved org.apache.hadoop.io.LongWritable
	at org.apache.hadoop.mapred.MapTask$MapOutputBuffer.collect(MapTask.java:1019)
	at org.apache.hadoop.mapred.MapTask$OldOutputCollector.collect(MapTask.java:591)
	at org.apache.hadoop.mapred.lib.IdentityMapper.map(IdentityMapper.java:38)
	at org.apache.hadoop.mapred.MapRunner.run(MapRunner.java:50)
	at org.apache.hadoop.mapred.MapTask.runOldMapper(MapTask.java:430)
	at org.apache.hadoop.mapred.MapTask.run(MapTask.java:366)
	at org.apache.hadoop.mapred.Child$4.run(Child.java:255)
	at java.security.AccessController.doPrivileged(Native Method)
	at javax.security.auth.Subject.doAs(Subject.java:415)
	at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1190)
	at org.apache.hadoop.mapred.Child.main(Child.java:249
' > /dev/null

echo " map only output, the first field is key, the other is value
318	0043012650999991949032412004+62300+010750FM-12+048599999V0202701N00461220001CN0500001N9+01111+99999999999
424	0043012650999991949032418004+62300+010750FM-12+048599999V0202701N00461220001CN0500001N9+00781+99999999999
0	0067011990999991950051507004+68750+023550FM-12+038299999V0203301N00671220001CN9999999N9+00001+99999999999
106	0043011990999991950051512004+68750+023550FM-12+038299999V0203201N00671220001CN9999999N9+00221+99999999999
212	0043011990999991950051518004+68750+023550FM-12+038299999V0203201N00261220001CN9999999N9-00111+99999999999
" > /dev/null
