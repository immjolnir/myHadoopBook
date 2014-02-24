package com.myhp.ch03.Configuration;// cc ConfigurationPrinter An example Tool implementation for printing the properties in a Configuration

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Map.Entry;

// vv ConfigurationPrinter
public class PropertiesPrinter extends Configured implements Tool {

    static {
        Configuration.addDefaultResource("hdfs-default.xml");
        Configuration.addDefaultResource("hdfs-site.xml");
        Configuration.addDefaultResource("mapred-default.xml");
        Configuration.addDefaultResource("mapred-site.xml");
    }

    @Override
    public int run(String[] args) throws Exception {
        // To printer the properties in the Hadoop JVM instance.
        System.getProperties().list(System.out);
        //user.home=/home/zhishan
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new PropertiesPrinter(), args);
        System.exit(exitCode);
    }
}
// ^^ PropertiesPrinter
/*
* output
-- listing properties --
java.runtime.name=Java(TM) SE Runtime Environment
sun.boot.library.path=/home/zhishan/workspace/bin/jdk1.7.0_...
java.vm.version=24.45-b08
java.vm.vendor=Oracle Corporation
java.vendor.url=http://java.oracle.com/
path.separator=:
java.vm.name=Java HotSpot(TM) Server VM
file.encoding.pkg=sun.io
idea.launcher.port=7534
user.country=US
sun.java.launcher=SUN_STANDARD
sun.os.patch.level=unknown
java.vm.specification.name=Java Virtual Machine Specification
user.dir=/home/zhishan/workspace/git/myHadoopBook
java.runtime.version=1.7.0_45-b18
java.awt.graphicsenv=sun.awt.X11GraphicsEnvironment
java.endorsed.dirs=/home/zhishan/workspace/bin/jdk1.7.0_...
os.arch=i386
java.io.tmpdir=/tmp
line.separator=

java.vm.specification.vendor=Oracle Corporation
os.name=Linux
sun.jnu.encoding=UTF-8
java.library.path=/home/zhishan/bin/intellijIdea/bin::/...
java.specification.name=Java Platform API Specification
java.class.version=51.0
sun.management.compiler=HotSpot Tiered Compilers
os.version=3.12.7-300.fc20.i686+PAE
user.home=/home/zhishan
user.timezone=Asia/Shanghai
java.awt.printerjob=sun.print.PSPrinterJob
file.encoding=UTF-8
idea.launcher.bin.path=/home/zhishan/workspace/bin/idea-IC-1...
java.specification.version=1.7
user.name=zhishan
java.class.path=/home/zhishan/bin/jdk/jre/lib/javaws....
java.vm.specification.version=1.7
sun.arch.data.model=32
java.home=/home/zhishan/workspace/bin/jdk1.7.0_...
sun.java.command=com.intellij.rt.execution.application...
java.specification.vendor=Oracle Corporation
user.language=en
awt.toolkit=sun.awt.X11.XToolkit
java.vm.info=mixed mode
java.version=1.7.0_45
java.ext.dirs=/home/zhishan/workspace/bin/jdk1.7.0_...
sun.boot.class.path=/home/zhishan/workspace/bin/jdk1.7.0_...
java.vendor=Oracle Corporation
file.separator=/
java.vendor.url.bug=http://bugreport.sun.com/bugreport/
sun.cpu.endian=little
sun.io.unicode.encoding=UnicodeLittle
sun.desktop=gnome
sun.cpu.isalist=

Process finished with exit code 0

* */