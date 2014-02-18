#input=${1:=/user/zhishan/hadoop-book/ch02/input} run.sh: line 1: $1: cannot assign in this way
input=${1:-/user/zhishan/myHadoopBook/sample}
output=${2:-/user/zhishan/myHadoopBook/compress/output} # no assigned 
hadoop fs -rmr -skipTrash $output
#hadoop jar ./target/ch02-1.0-SNAPSHOT.jar com.myhp.ch02.MaxTemperatureMain $input $output 
hadoop jar ./target/ch02-1.0-SNAPSHOT.jar com.myhp.ch02.MaxTemperatureWithMapOutputCompression $input $output 

#mapred.output.dir	hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/output
#mapred.input.dir	hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
