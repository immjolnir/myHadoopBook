package com.myhp.ch02;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Properties;

/**
 * Created by zhishan on 2/10/14.
 */
public class MaxTemperatureDriver extends Configured implements Tool {
    public static final Log log = LogFactory.getLog(MaxTemperatureDriver.class);

    @Override
    public int run(String[] args) throws  Exception {
        if ( args.length != 2) {
            System.err.printf("Usage: %s [generic options] <input> <output>\n",
                    getClass().getSimpleName()
                    );
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }

        JobConf conf = new JobConf(getConf(), getClass());
        conf.setJobName("Max temperature(Configured & Tool)");

        FileInputFormat.addInputPath(conf,new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        conf.setOutputKeyClass(org.apache.hadoop.io.Text.class);
        conf.setOutputValueClass(IntWritable.class);
        conf.setMapperClass(MaxTemperatureMapper.class);
        conf.setCombinerClass(MaxTemperatureReducer.class);
        conf.setReducerClass(MaxTemperatureReducer.class);
        /*System.out.println log showed in the console*/
        System.out.println("*** System.out.println: " + conf.get("user.home", "user.home not found as a setting")); // output: its default value: not found
        // because the user.home is the JVM properties Of Hadoop instance,
        System.out.println("*** System.out.println: " + "JVM property - user.home : " + new Properties().get("user.home"));
        System.out.println("*** System.out.println: " + conf.get("mapred.mapper.class", "mapred.mapper.class not found as a setting")); // getMapperClass
        System.out.println("*** System.out.println: " + conf.get("dfs.name.dir", "dfs.name.dir not found as a setting")); // getMapperClass

        log.info(log.isInfoEnabled() ? "[Info Enabled] dfs.name.dir: " + conf.get("dfs.name.dir", "not found as a setting") : "[Info Not Enabled]");
        log.info(log.isInfoEnabled() ? "[Info Enabled] user.home: " + conf.get("user.home", "not found as a setting") : "[Info Not Enabled");
        log.info(log.isInfoEnabled() ? "[Info Enabled] Mapper Class: " + conf.getMapperClass().toString() : "[Info Not Enabled]" );

        JobClient.runJob(conf);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new MaxTemperatureDriver(), args);
        System.exit(exitCode);
    }
}
