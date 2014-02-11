input=${1:-/user/zhishan/hadoop-book/ch02/cache.input/input.txt}
output=${2:-/user/zhishan/hadoop-book/ch02/output.cache}

$HADOOP_INSTALL/bin/hadoop fs -rmr -skipTrash $output
hadoop fs -text $input | wc --bytes

$HADOOP_INSTALL/bin/hadoop jar $HADOOP_INSTALL/./contrib/streaming/hadoop-streaming-1.2.1.jar \
        -D mapred.reduce.tasks=0 \
        -input $input \
        -output $output \
	-mapper "xargs cat" \
	-reducer "cat" \
	-cacheArchive '/user/zhishan/hadoop-book/ch02/cache.input/cachedir.jar#testlink' \
	-jobconf mapred.map.tasks=1 \
	-jobconf mapred.reduce.tasks=1 \
	-jobconf mapred.job.name="hadoopStreamingCacheFile Experiment"

