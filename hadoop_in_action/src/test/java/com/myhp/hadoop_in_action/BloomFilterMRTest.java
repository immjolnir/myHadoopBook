package com.myhp.hadoop_in_action;

import org.junit.Test;

/**
 * Created on 3/16/14.
 */
public class BloomFilterMRTest {
    @Test
    public void stringFormatTest(){

        String jobId=String.format("job_%s_%s","201402270246", "14");
        System.out.println(jobId);
    }
}
