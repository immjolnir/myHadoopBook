package com.myhp.ch02;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;


public class MaxTemperatureMapper extends MapReduceBase
    implements Mapper<LongWritable, Text, Text, IntWritable>{

    private static final int MISSING = 9999;
    public static final Log log = LogFactory.getLog(MaxTemperatureDriver.class);

    public void map(LongWritable key, Text value,
                    OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException{
        // input record
        // 0029029070999991901010413004+64333+023450FM-12+000599999V0202301N008219999999N0000001N9+00441+99999102261ADDGF108991999999999999999999
        System.out.println("*** System.out.println: " + value.toString()); // getMapperClass

        log.info(log.isInfoEnabled() ? "[Info Enabled] " + value.toString() : "[Info Not Enabled]");
        String line = value.toString();
        String year = line.substring(15, 19);
        int airTemperature;

        if(line.charAt(87) == '+'){
            // parseInt doesn't like leading plus signs
            airTemperature = Integer.parseInt(line.substring(88,92));
        } else {
            airTemperature = Integer.parseInt(line.substring(87,92));
        }

        String quality = line.substring(92,93);
        if ( airTemperature != MISSING && quality.matches("[01459]")) {
            output.collect(new Text(year), new IntWritable(airTemperature));
        }
    }
}
/*
     * @param      beginIndex   the beginning index, inclusive.
     * @param      endIndex     the ending index, exclusive.
     public String substring(int beginIndex, int endIndex) ;
     e.g
     * "hamburger".substring(4, 8) returns "urge"
     * "smiles".substring(1, 5) returns "mile"
*/
