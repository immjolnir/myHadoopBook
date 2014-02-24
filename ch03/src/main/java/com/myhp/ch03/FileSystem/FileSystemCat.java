package com.myhp.ch03.FileSystem;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;


/**
 * Created on 2/11/14.
 */
public class FileSystemCat {
    public static void main(String[] args) throws IOException{
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        InputStream in = null;
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
            System.out.println("currect offset(bytes): " + ((DFSClient.DFSDataInputStream)in).getPos());
        } finally {
            org.apache.hadoop.io.IOUtils.closeStream(in);
        }

    }
}

/*
* why "4096" always set in IOUtils.copyBytes?
*
*   /**
   * Opens an FSDataInputStream at the indicated Path.
   * @param f the file to open

public FSDataInputStream open(Path f) throws IOException {
    return open(f, getConf().getInt("io.file.buffer.size", 4096));
}

Hadoop set the "io.file.buffer.size" default value as 4096
* */