package com.myhp.ch02;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.mapred.JobConf;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



/**
 * Created by zhishan on 2/10/14.
 */
public class MaxTemperatureDriverTest {

    @Test
    public void test() throws Exception {
        JobConf conf = new JobConf();
        conf.set("fs.default.name", "file:///");
        conf.set("mapred.job.tracker","local");
        // case 1
        //Path input = new Path("/input/ncdc/micro");
        //cause:org.apache.hadoop.mapred.InvalidInputException: Input path does not exist: file:/home/zhishan/workspace/git/myHadoopBook/ch02/input/ncdc/micro

        //case 2; Input path does not exist: file:/input/ncdc/micro
        //Path input = new Path("/input/ncdc/micro");

        // case 3:

        Path input = new Path(getClass().getResource("/input/ncdc/micro").getPath());
        Path output = new  Path("src/test/resources/output");

        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output,true); // delete old output

        MaxTemperatureDriver driver = new MaxTemperatureDriver();
        driver.setConf(conf); // inherit from Configured class

        int exitCode = driver.run(new String[] {
            input.toString(), output.toString()
        });
        Assert.assertEquals(exitCode, 0);



        checkOutput(conf, output);
        }


    private void checkOutput(Configuration conf, Path output) throws IOException {
        FileSystem fs = FileSystem.getLocal(conf);
        Path[] outputFiles = FileUtil.stat2Paths(
                fs.listStatus(output, new OutputLogFilter()));
        Assert.assertEquals(outputFiles.length, 1);

        BufferedReader actual = asBufferedReader(fs.open(outputFiles[0]));
        BufferedReader expected = asBufferedReader(
                getClass().getResourceAsStream("/expected.txt"));
        String expectedLine;
        while ((expectedLine = expected.readLine()) != null) {
            Assert.assertEquals(actual.readLine(), expectedLine);
        }

        actual.close();
        expected.close();
    }

    private BufferedReader asBufferedReader(InputStream in) throws IOException {
        return new BufferedReader(new InputStreamReader(in));
    }

    public static class OutputLogFilter implements PathFilter {
        public boolean accept(Path path){
            return !path.getName().startsWith("_");
        }
    }
}
