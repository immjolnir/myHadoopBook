package com.myhp.ch04.sequence;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;


/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/18/14
 * Time: 5:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class SequenceFileMetaTest {

    @Test
    public void testReadSequenceFileMeta() /*throws Exception*/ {
        URL seqPath = getClass().getResource("/sequence/input/part-00000");
        System.out.println(seqPath.getPath()); ///home/zhishan/workspace/git/myHadoopBook/ch04/target/test-classes/sequence/input/part-00000

        try {
            SequenceFileMeta.readSquenceFileMeta(seqPath.getFile());
        } catch (IOException e) {
            System.out.println(e);
        } //java.lang.RuntimeException: java.io.IOException: WritableName can't load class: org.apache.hadoop.hive.ql.io.RCFile$KeyBuffer
        // add the dependency: hive-exec into the pom.xml, the exception removed.
        /* output
        *
        * Sequence File Key class: org.apache.hadoop.hive.ql.io.RCFile$KeyBuffer | org.apache.hadoop.hive.ql.io.RCFile$KeyBuffer
          Sequence File Value Class: org.apache.hadoop.hive.ql.io.RCFile$ValueBuffer  | org.apache.hadoop.hive.ql.io.RCFile$ValueBuffer
          CompressionCodec: org.apache.hadoop.io.compress.DefaultCodec@3f9586c8
          Meta Data: size: 2
	      SerDeClass	com.yahoo.uda.fetl.ABF_v1
	      hive.io.rcfile.column.number	79

        * */
    }
}
