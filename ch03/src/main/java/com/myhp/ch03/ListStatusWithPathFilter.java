package com.myhp.ch03;

import com.myhp.ch03.pathFilters.RegexExcludePathFilter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * Created on 2/12/14.
 */
// cc ListStatus Shows the file statuses for a collection of paths in a Hadoop filesystem


// vv ListStatus
public class ListStatusWithPathFilter {

    public static void main(String[] args) throws Exception {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path[] paths = new Path[args.length];
        for (int i = 0; i < paths.length; i++) {
            paths[i] = new Path(args[i]);
        }
        for (Path p : paths) {
            System.out.println(p + ", fileName: " + p.getName() + ", directoryName: " + p.getParent().toString());
        }
        System.out.println("\nafter pre print files\n");
        // test 1
        //FileStatus[] status = fs.listStatus(paths, new RegexExcludePathFilter("^[_.].*"));
        // test2
        FileStatus[] status = fs.listStatus(paths, new RegexExcludePathFilter(".*/[_.].*$"));
        Path[] listedPaths = FileUtil.stat2Paths(status);
        for (Path p : listedPaths) {
            System.out.println(p + ", fileName: " + p.getName() + ", directoryName: " + p.getParent().toString());
        }

        /*output:
        >sh run.sh
hdfs://localhost:9000/user/zhishan/ch03, fileName: ch03, directoryName: hdfs://localhost:9000/user/zhishan
hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input, fileName: input, directoryName: hdfs://localhost:9000/user/zhishan/hadoop-book/ch02

after pre print files


hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/.ab, fileName: .ab, directoryName: hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/_LOG, fileName: _LOG, directoryName: hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/_SUCCESS, fileName: _SUCCESS, directoryName: hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/sample.txt, fileName: sample.txt, directoryName: hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
        * */

        // test2 result
      /*
        * after pre print files

hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input/sample.txt, fileName: sample.txt, directoryName: hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
*/
    }
}


// ^^ globStatus

/*
* the "fs.listStatus invoked" as below
*
*   private void listStatus(ArrayList<FileStatus> results, Path f,
      PathFilter filter) throws IOException {
    FileStatus listing[] = listStatus(f);
    if (listing != null) {
      for (int i = 0; i < listing.length; i++) {
        if (filter.accept(listing[i].getPath())) {
          results.add(listing[i]);
        }
      }
    }
  }

* hadoop jar ../target/ch03-1.0-SNAPSHOT.jar com.myhp.ch03.ListStatusWithPathFilter hdfs://localhost:9000/user/zhishan/ch03 hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
* >hadoop fs -ls hdfs://localhost:9000/user/zhishan/ch03
ls: Cannot access hdfs://localhost:9000/user/zhishan/ch03: No such file or directory.
* >hadoop fs -ls /user/zhishan/hadoop-book/ch02/input
Found 4 items
-rw-r--r--   1 zhishan supergroup          3 2014-02-12 07:25 /user/zhishan/hadoop-book/ch02/input/.ab
drwxr-xr-x   - zhishan supergroup          0 2014-02-12 07:25 /user/zhishan/hadoop-book/ch02/input/_LOG
-rw-r--r--   1 zhishan supergroup          0 2014-02-12 07:24 /user/zhishan/hadoop-book/ch02/input/_SUCCESS
-rw-r--r--   1 zhishan supergroup        529 2014-02-12 03:41 /user/zhishan/hadoop-book/ch02/input/sample.txt
* */


