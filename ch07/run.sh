#input=${1:=/user/zhishan/hadoop-book/ch02/input} run.sh: line 1: $1: cannot assign in this way
#input=${1:-/user/zhishan/myHadoopBook/input/ncdc/190{1,2}.gz}
input=${1:-/tmp/ch07} # no assigned 
output=${2:-/tmp/out} # no assigned 
hadoop fs -rmr -f -skipTrash $output
#hadoop jar ./target/ch02-1.0-SNAPSHOT.jar com.myhp.ch02.MaxTemperatureMain $input $output 
hadoop jar ./target/ch07-1.0-SNAPSHOT.jar com.myhp.ch07.MinimalMapReduce $input $output 

#mapred.output.dir	hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/output
#mapred.input.dir	hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
