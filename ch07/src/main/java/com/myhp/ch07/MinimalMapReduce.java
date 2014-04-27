package com.myhp.ch07;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.FileOutputStream;

/**
 * Created on 4/26/14.
 */
public class MinimalMapReduce extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        if( args.length != 2 ){
            System.err.printf("Usage: %s[generic options] <input> <output>\n",
                    getClass().getSimpleName()
                    );
            ToolRunner.printGenericCommandUsage(System.err);
            return  -1;
        }

        JobConf conf = new JobConf(getConf(), getClass());
        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
        JobClient.runJob(conf);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new MinimalMapReduce(), args);
        System.exit(exitCode);
    }

}
