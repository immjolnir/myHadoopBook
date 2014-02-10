package com.myhp.ch02;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;

/**
 * Created by zhishan on 2/9/14.
 */
public class MaxTemperatureMain {
    public static void main(String[] args) throws IOException {
        if ( args.length != 2) {
            System.err.println("Usage: MaxTemperatureMain <input path> <output path>");
            System.exit(-1);
        }

        JobConf conf = new JobConf(MaxTemperatureMain.class);
        conf.setJobName("Max Temperature(setJobName)");
        conf.set("mapred.job.name", "Max Temperature(set)");
        conf.setInt("mapred.map.max.attempts", 100);
        //conf.setInt("mapred.reduce.tasks",0);
        /*
        *  public void setMaxReduceAttempts(int n) {
            setInt("mapred.reduce.max.attempts", n);
         }
         setMaxMapAttempts
         setNumReduceTasks
        * */ 

        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        conf.setMapperClass(MaxTemperatureMapper.class);
        conf.setCombinerClass(MaxTemperatureReducer.class); //mapred.combiner.class
        conf.setReducerClass(MaxTemperatureReducer.class);
        conf.setOutputKeyClass(Text.class);//the key class for the job output data. "mapred.output.key.class"
        conf.setOutputValueClass(IntWritable.class);//mapred.output.value.class

        JobClient.runJob(conf);
    }
}

// if set the mapred.reduce.tasks=0, Map only run; And then add the mapred.combiner.class=MaxTemperatureReducer.class.
// they are have the same output. is it the combiner not work?
