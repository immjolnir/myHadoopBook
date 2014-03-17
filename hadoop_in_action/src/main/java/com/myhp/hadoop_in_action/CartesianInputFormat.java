package com.myhp.hadoop_in_action;

import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.net.NetworkTopology;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 3/17/14
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */

public class CartesianInputFormat extends FileInputFormat {
    public CartesianInputFormat() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected String[] getSplitHosts(BlockLocation[] blkLocations, long offset, long splitSize, NetworkTopology clusterMap) throws IOException {
        return super.getSplitHosts(blkLocations, offset, splitSize, clusterMap);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected int getBlockIndex(BlockLocation[] blkLocations, long offset) {
        return super.getBlockIndex(blkLocations, offset);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected long computeSplitSize(long goalSize, long minSize, long blockSize) {
        return super.computeSplitSize(goalSize, minSize, blockSize);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public InputSplit[] getSplits(JobConf job, int numSplits) throws IOException {
        return super.getSplits(job, numSplits);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected FileStatus[] listStatus(JobConf job) throws IOException {
        return super.listStatus(job);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public RecordReader getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected boolean isSplitable(FileSystem fs, Path filename) {
        return super.isSplitable(fs, filename);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void setMinSplitSize(long minSplitSize) {
        super.setMinSplitSize(minSplitSize);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
