#input=${1:=/user/zhishan/hadoop-book/ch02/input} run.sh: line 1: $1: cannot assign in this way
input=${1:-/user/zhishan/hadoop-in-action/ch5/input}
output=${2:-/user/zhishan/hadoop-in-action/ch5/output} # no assigned 
hadoop fs -rmr -skipTrash $output
#hadoop jar ./target/ch02-1.0-SNAPSHOT.jar com.myhp.ch02.MaxTemperatureMain $input $output 
hadoop jar ./target/hadoop_in_action-1.0.jar com.myhp.hadoop_in_action.CartesianProduct $input $output 

#mapred.output.dir	hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/output
#mapred.input.dir	hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
