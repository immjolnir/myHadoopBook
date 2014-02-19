package com.myhp.ch04.sequence;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/18/14
 * Time: 5:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class SequenceFileMeta {

    public static void readSquenceFileMeta(String abfFile) throws IOException {
        Configuration conf = new Configuration();
        Path filePath = new Path(abfFile);
        FileSystem fs = filePath.getFileSystem(conf);

        SequenceFile.Reader reader = null;
        try {
            reader = new SequenceFile.Reader(fs, filePath, conf);
            System.out.println("Sequence File Key class: " + reader.getKeyClass().getName() + " | " + reader.getKeyClassName());
            System.out.println("Sequence File Value Class: " + reader.getValueClass().getName() + "  | " + reader.getValueClassName());
            System.out.println("CompressionCodec: "  + reader.getCompressionCodec().toString());
            System.out.println("Meta Data: " + reader.getMetadata().toString());
        } finally {
            IOUtils.closeStream(reader);
        }
    }

    public static void main(String[] args) throws IOException {
        readSquenceFileMeta(args[0]);
    }
}
