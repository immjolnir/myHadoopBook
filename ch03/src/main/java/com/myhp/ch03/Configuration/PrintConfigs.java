package com.myhp.ch03.Configuration;

import org.apache.hadoop.conf.Configuration;
import org.junit.Test;

import java.util.Map;

/**
 * Created on 2/22/14.
 */
public class PrintConfigs {

    @Test
    public void printDefaultResourcePath(String[] args) {
        Configuration conf = new Configuration();
        conf.addResource("core-default.xml");
        conf.addResource("core-site.xml");
        conf.addResource("/configuration-1.xml");

        System.out.println(conf.get("hadoop.tmp.dir", "${hadoop.tmp.dir} not found")); // return /tmp/hadoop-zhishan
        System.out.println(conf.getResource("core-default.xml").toString()); // return jar:file:/home/zhishan/.m2/repository/org/apache/hadoop/hadoop-core/1.2.1/hadoop-core-1.2.1.jar!/core-default.xml


    }
}
