package com.myhp.ch03;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;


/**
 * Created on 2/12/14.
 */
public class FileAppend {
    public static void main(String[] args) throws IOException{
        String src = args[0];
        String dst = args[1];

        Configuration conf = new Configuration();
        System.out.println("dfs.support.append = " + conf.get("dfs.support.append", "false"));
        conf.setBoolean("dfs.support.append", true);
        System.out.println("dfs.support.append = " + conf.get("dfs.support.append", "false"));
        /*
        * output, show such setting not work except add it to hdfs-site.xml and restart hadoop server.
        * dfs.support.append = false
        * dfs.support.append = true
        * Exception in thread "main" org.apache.hadoop.ipc.RemoteException: java.io.IOException: Append is not supported. Please see the dfs.support.append configuration parameter
        * */
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        FSDataOutputStream out  = fs.append(new Path(dst));
        out.writeBytes("hello hadoop");
        out.close();
    }
}

/*
* Exception in thread "main" org.apache.hadoop.ipc.RemoteException: java.io.IOException: Append is not supported.
* Please see the dfs.support.append configuration parameter
*
* in hdfs-site.xml
*   <property>
    <name>dfs.support.append</name>
    <value>true</value>
    <description>Enable or disable append. Defaults to false</description>
  </property
  but in hdfs-default.xml, it shows as below:

* <property>
  <name>dfs.support.append</name>
  <description>
    This option is no longer supported. HBase no longer requires that
    this option be enabled as sync is now enabled by default. See
    HADOOP-8230 for additional information.
  </description>
</property>
*/
