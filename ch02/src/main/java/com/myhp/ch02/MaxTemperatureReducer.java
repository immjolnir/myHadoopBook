package com.myhp.ch02;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.Iterator;

public class MaxTemperatureReducer extends MapReduceBase
    implements Reducer<Text, IntWritable, Text, IntWritable>  {
    public static final Log log = LogFactory.getLog(MaxTemperatureReducer.class);

    public void reduce(Text key, Iterator<IntWritable> values,
                       OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
    // input (Text, IntWritable) = (1901, 317)
        System.out.println("*** System.out.println: " + values.toString()); // getMapperClass

        log.info(log.isInfoEnabled() ? "[Info Enabled] " + values.toString() : "[Info Not Enabled]");

        int maxValue = Integer.MIN_VALUE;
        while(values.hasNext()){
            maxValue = Math.max(maxValue, values.next().get());
        }
        output.collect(key, new IntWritable(maxValue));
    }

    @Override
    public void configure(JobConf job) {
        System.out.println("[system]: Reducer: configure");
        log.info("Reducer: configure()");
        super.configure(job);
    }

    @Override
    public void close() throws IOException {
        System.out.println("[system]: Reducer: close");
        log.info("Reducer: close()");
        super.close();
    }
}
