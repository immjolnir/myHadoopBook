package com.myhp.ch03.FileSystem;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
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
public class FileSystemDoubleCat {
    public static void main(String[] args) throws IOException{
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FSDataInputStream in = null;
        try {
            in = fs.open(new Path(uri)); // open() return FSDataInputStream object
            IOUtils.copyBytes(in, System.out, 4096, false);
            System.out.println("currect offset(bytes): " + in.getPos());
            in.seek(0); // go back to the start of the file
            IOUtils.copyBytes(in, System.out, 4096, false);
            System.out.println("currect offset(bytes): " + in.getPos());

            //in.seek(0);
            byte[] buffer = new byte[128];
            int readedBnum = in.read(0,buffer,0,127);
            System.out.println(new String(buffer) + "readed bytes number" + readedBnum);

        } finally {
            IOUtils.closeStream(in);
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

/*
*
* public class FSDataInputStream extends DataInputStream
    implements Seekable, PositionedReadable, Closeable, HasFileDescriptor {
    }

    final public class FileDescriptor {
    private AtomicInteger useCount;
 * Instances of the file descriptor class serve as an opaque handle
 * to the underlying machine-specific structure representing an open
 * file, an open socket, or another source or sink of bytes. The
 * main practical use for a file descriptor is to create a
 * <code>FileInputStream</code> or <code>FileOutputStream</code> to
 * contain it.

    }
*/