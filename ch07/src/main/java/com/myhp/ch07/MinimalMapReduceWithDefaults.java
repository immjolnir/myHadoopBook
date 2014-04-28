package com.myhp.ch07;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.lib.HashPartitioner;
import org.apache.hadoop.mapred.lib.IdentityMapper;
import org.apache.hadoop.mapred.lib.IdentityReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created on 4/26/14.
 */
public class MinimalMapReduceWithDefaults extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        if( args.length != 2 ){
            System.err.printf("Usage: %s[generic options] <input> <output>\n",
                    getClass().getSimpleName()
                    );
            ToolRunner.printGenericCommandUsage(System.err);
            return  -1;
        }
        // sometimes, the JobBuilder in common-api of current project, will be used here.

        JobConf conf = new JobConf(getConf(), getClass());

        // set input format, default value
        conf.setInputFormat(TextInputFormat.class);

        // default Mapper class
        conf.setNumMapTasks(1);
        conf.setMapperClass(IdentityMapper.class);
        conf.setMapRunnerClass(MapRunner.class);
        conf.setMapOutputKeyClass(LongWritable.class);
        conf.setMapOutputValueClass(org.apache.hadoop.io.Text.class);

        // default Partitioner class
        conf.setPartitionerClass(HashPartitioner.class);

        // default Reduce setting
        conf.setNumReduceTasks(1);
        conf.setReducerClass(IdentityReducer.class);
        conf.setOutputKeyClass(LongWritable.class);
        conf.setOutputValueClass(org.apache.hadoop.io.Text.class);

        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
        JobClient.runJob(conf);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new MinimalMapReduceWithDefaults(), args);
        System.exit(exitCode);
    }

}
