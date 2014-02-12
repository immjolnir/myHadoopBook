
#hadoop jar ../target/ch03-1.0-SNAPSHOT.jar com.myhp.ch03.URLCat  hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/sample.txt
#hadoop jar ../target/ch03-1.0-SNAPSHOT.jar com.myhp.ch03.FileSystemCat  hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/sample.txt
#hadoop jar ../target/ch03-1.0-SNAPSHOT.jar com.myhp.ch03.FileSystemDoubleCat  hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/sample.txt
#hadoop jar ../target/ch03-1.0-SNAPSHOT.jar com.myhp.ch03.FileAppend input/sample.txt hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/sample.txt
#hadoop jar ../target/ch03-1.0-SNAPSHOT.jar com.myhp.ch03.ListStatus hdfs://localhost:9000/user/zhishan/ch03 hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
hadoop jar ../target/ch03-1.0-SNAPSHOT.jar com.myhp.ch03.ListStatusWithPathFilter hdfs://localhost:9000/user/zhishan/ch03 hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input

echo "
hdfs://localhost/127.0.0.1:8020

HDFS namenode default port is 8020 at localhost as above.
">/dev/null
