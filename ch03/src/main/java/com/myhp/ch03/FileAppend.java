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
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        FSDataOutputStream out  = fs.append(new Path(dst));
        out.writeBytes("hello hadoop");
        out.close();
    }
}

/*
* Exception in thread "main" org.apache.hadoop.ipc.RemoteException: java.io.IOException: Append is not supported. Please see the dfs.support.append configuration parameter
*/
