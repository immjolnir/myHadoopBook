package com.myhp.ch03;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

/**
 * Created on 2/12/14.
 */
// cc ListStatus Shows the file statuses for a collection of paths in a Hadoop filesystem


// vv ListStatus
public class ListStatus {

    public static void main(String[] args) throws Exception {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path[] paths = new Path[args.length];
        for (int i = 0; i < paths.length; i++) {
            paths[i] = new Path(args[i]);
        }

        FileStatus[] status = fs.listStatus(paths);
        Path[] listedPaths = FileUtil.stat2Paths(status);
        for (Path p : listedPaths) {
            System.out.println(p);
        }
    }
}
// ^^ ListStatus

/*
* >hadoop fs -ls /user/zhishan/hadoop-book/ch02/input
Found 4 items
-rw-r--r--   1 zhishan supergroup          3 2014-02-12 07:25 /user/zhishan/hadoop-book/ch02/input/.ab
drwxr-xr-x   - zhishan supergroup          0 2014-02-12 07:25 /user/zhishan/hadoop-book/ch02/input/_LOG
-rw-r--r--   1 zhishan supergroup          0 2014-02-12 07:24 /user/zhishan/hadoop-book/ch02/input/_SUCCESS
-rw-r--r--   1 zhishan supergroup        529 2014-02-12 03:41 /user/zhishan/hadoop-book/ch02/input/sample.txt

$ hadoop jar ../target/ch03-1.0-SNAPSHOT.jar com.myhp.ch03.ListStatus hdfs://localhost:9000/user/zhishan/ch03 hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
>sh run.sh
hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/.ab
hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/_LOG
hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/_SUCCESS
hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/sample.txt

no file is filtered. The reason is:
FileStatus[] status = fs.listStatus(paths);

source code:
  public FileStatus[] listStatus(Path[] files)
      throws IOException {
    return listStatus(files, DEFAULT_FILTER);
  }

what is the DEFAULT_FILTER?
in FileSystem.java
  final private static PathFilter DEFAULT_FILTER = new PathFilter() {
      public boolean accept(Path file) {
        return true;
      }
    };

    So, no path filters in such DEFAULT_FILTER.

* */


/*
FileStatus java

*   private Path path;
  private long length;
  private boolean isdir;
  private short block_replication;
  private long blocksize;
  private long modification_time;
  private long access_time;
  private FsPermission permission;
  private String owner;
  private String group;
* */

