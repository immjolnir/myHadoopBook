output=${1:-/tmp/out} # no assigned 
hadoop fs -rmr -f -skipTrash $output
hadoop jar ./target/hadoop-examples-1.2.1.jar \
org.apache.hadoop.examples.RandomWriter \
-fs file:/// \
-jt local \
$output 

#When -fs is give local, "WARN fs.FileSystem: "local" is a deprecated filesystem name. Use "file:///" instead." is raised. so use "file:///" instead.


#MapR
#14/06/04 16:18:25 INFO mapred.JobClient:  map 80% reduce 0%
#14/06/04 16:19:06 INFO mapred.JobClient:  map 100% reduce 0%
#14/06/04 16:19:11 INFO mapred.JobClient: Job complete: job_201406041510_0001
#14/06/04 16:19:11 INFO mapred.JobClient: Counters: 21
#14/06/04 16:19:11 INFO mapred.JobClient:   Job Counters 
#14/06/04 16:19:11 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=476188
#Local
#14/06/04 16:31:51 INFO util.NativeCodeLoader: Loaded the native-hadoop library
#14/06/04 16:31:52 INFO mapred.JobClient: Running job: job_local170089976_0001
#14/06/04 16:31:52 INFO mapred.LocalJobRunner: Waiting for map tasks
#14/06/04 16:31:52 INFO mapred.LocalJobRunner: Starting task: attempt_local170089976_0001_m_000000_0
#14/06/04 16:31:52 INFO util.ProcessTree: setsid exited with exit code 0
#14/06/04 16:31:52 INFO mapred.Task:  Using ResourceCalculatorPlugin : org.apache.hadoop.util.LinuxResourceCalculatorPlugin@fcc0cf
#14/06/04 16:31:52 INFO mapred.MapTask: Processing split: hdfs://localhost:9000/tmp/out/dummy-split-0:0+1
#14/06/04 16:31:52 INFO mapred.MapTask: numReduceTasks: 0
#14/06/04 16:31:53 INFO mapred.JobClient:  map 0% reduce 0%
#14/06/04 16:31:58 INFO mapred.LocalJobRunner: wrote record 1238000. 938134567 bytes left.
#14/06/04 16:32:01 INFO mapred.LocalJobRunner: wrote record 1924400. 862947018 bytes left.
#14/06/04 16:32:04 INFO mapred.LocalJobRunner: wrote record 2625200. 786179983 bytes left.
#14/06/04 16:32:07 INFO mapred.LocalJobRunner: wrote record 3223200. 720718906 bytes left.
#14/06/04 16:32:10 INFO mapred.LocalJobRunner: wrote record 3937000. 642542978 bytes left.
#14/06/04 16:32:13 INFO mapred.LocalJobRunner: wrote record 4622000. 567566099 bytes left.
#14/06/04 16:32:16 INFO mapred.LocalJobRunner: wrote record 5341200. 488806331 bytes left.
#14/06/04 16:32:19 INFO mapred.LocalJobRunner: wrote record 6082800. 407573079 bytes left.
#14/06/04 16:32:22 INFO mapred.LocalJobRunner: wrote record 6816800. 327167430 bytes left.
#14/06/04 16:32:25 INFO mapred.LocalJobRunner: wrote record 7569600. 244807894 bytes left.
#14/06/04 16:32:28 INFO mapred.LocalJobRunner: wrote record 8315800. 163058830 bytes left.
#14/06/04 16:32:31 INFO mapred.LocalJobRunner: wrote record 9066200. 80865032 bytes left.
#14/06/04 16:32:34 INFO mapred.LocalJobRunner: done with 9805763 records.
#14/06/04 16:32:34 INFO mapred.Task: Task:attempt_local170089976_0001_m_000000_0 is done. And is in the process of commiting
#14/06/04 16:32:34 INFO mapred.LocalJobRunner: done with 9805763 records.
#14/06/04 16:32:34 INFO mapred.Task: Task attempt_local170089976_0001_m_000000_0 is allowed to commit now
#14/06/04 16:32:34 INFO mapred.FileOutputCommitter: Saved output of task 'attempt_local170089976_0001_m_000000_0' to hdfs://localhost:9000/tmp/out
#14/06/04 16:32:34 INFO mapred.LocalJobRunner: done with 9805763 records.
#14/06/04 16:32:34 INFO mapred.Task: Task 'attempt_local170089976_0001_m_000000_0' done.
#14/06/04 16:32:34 INFO mapred.LocalJobRunner: Finishing task: attempt_local170089976_0001_m_000000_0
#14/06/04 16:32:34 INFO mapred.LocalJobRunner: Map task executor complete.
