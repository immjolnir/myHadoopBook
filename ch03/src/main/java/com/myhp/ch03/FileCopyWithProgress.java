package com.myhp.ch03;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;


/**
 * Created on 2/12/14.
 */
public class FileCopyWithProgress {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String localSrc = args[0];
        String dst = args[1];

        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        OutputStream out = fs.create(new Path(dst), new Progressable() {
            @Override
            public void progress() {
                System.out.print(".");
            }
        });
        IOUtils.copyBytes(in, out, 4096, true);
    }
}

/*
*
*   FileSystem Class
*
*
   * Return the number of bytes that large input files should be optimally
   * be split into to minimize i/o time.
   * @deprecated use {@link #getDefaultBlockSize(Path)} instead

@Deprecated
public long getDefaultBlockSize() {
    // default to 32MB: large enough to minimize the impact of seeks
    return getConf().getLong("fs.local.block.size", 32 * 1024 * 1024);
}

  /**
   * Get the default replication.
   * @deprecated use {@link #getDefaultReplication(Path)} instead

@Deprecated
public short getDefaultReplication() { return 1; }

* */