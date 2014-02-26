#input=${1:=/user/zhishan/hadoop-book/ch02/input} run.sh: line 1: $1: cannot assign in this way
input=${1:-/user/zhishan/hadoop-book/ch02/input}
output=${2:-/user/zhishan/hadoop-book/ch02/output} # no assigned 
hadoop fs -rmr -skipTrash $output
#hadoop jar ./target/ch02-1.0-SNAPSHOT.jar com.myhp.ch02.MaxTemperatureMain $input $output 
hadoop jar ./target/SerDe_Avro-1.0.jar com.myhp.serDe.avro.mapreduce.AvroGenericMaxTemperature $input $output 

#mapred.output.dir	hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/output
#mapred.input.dir	hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
