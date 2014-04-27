package com.myhp.ch07;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.SplittableCompressionCodec;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.lib.CombineFileInputFormat;

import java.io.IOException;

/**
 * Created on 4/27/14.
 * To make the UserCombineFileInputFormat same as the TextInputFormat, copy its attributes and extends getRecordReader() method.
 *
 * Example 1:
 * http://shiyanjun.cn/archives/299.html
 * 在使用Hadoop处理海量小文件的应用场景中，如果你选择使用CombineFileInputFormat，而且你是第一次使用，可能你会感到有点迷惑。虽然，从这个处理方案的思想上很容易理解，但是可能会遇到这样那样的问题。
 使用CombineFileInputFormat作为Map任务的输入规格描述，首先需要实现一个自定义的RecordReader。
 CombineFileInputFormat的大致原理是，他会将输入多个数据文件（小文件）的元数据全部包装到CombineFileSplit类里面。也就是说，因为小文件的情况下，在HDFS中都是单Block的文件，即一个文件一个Block，一个CombineFileSplit包含了一组文件Block，包括每个文件的起始偏移（offset），长度（length），Block位置（localtions）等元数据。如果想要处理一个CombineFileSplit，很容易想到，对其包含的每个InputSplit（实际上这里面没有这个，你需要读取一个小文件块的时候，需要构造一个FileInputSplit对象）。
 在执行MapReduce任务的时候，需要读取文件的文本行（简单一点是文本行，也可能是其他格式数据）。那么对于CombineFileSplit来说，你需要处理其包含的小文件Block，就要对应设置一个RecordReader，才能正确读取文件数据内容。通常情况下，我们有一批小文件，格式通常是相同的，只需要在为CombineFileSplit实现一个RecordReader的时候，内置另一个用来读取小文件Block的RecordReader，这样就能保证读取CombineFileSplit内部聚积的小文件。
 *
 * 2. Java Doc
 * https://hadoop.apache.org/docs/r2.2.0/api/org/apache/hadoop/mapred/lib/CombineFileInputFormat.html
 * An abstract InputFormat that returns CombineFileSplit's in InputFormat.getSplits(JobConf, int) method.
 * Splits are constructed from the files under the input paths.
 * A split cannot have files from different pools.
 * Each split returned may contain blocks from different files.
 * If a maxSplitSize is specified, then blocks on the same node are combined to form a single split.
 * Blocks that are left over are then combined with other blocks in the same rack.
 * If maxSplitSize is not specified, then blocks from the same rack are combined in a single split; no attempt is made to create node-local splits.
 * If the maxSplitSize is equal to the block size, then this class is similar to the default spliting behaviour in Hadoop: each block is a locally processed split.
 * Subclasses implement InputFormat.getRecordReader(InputSplit, JobConf, Reporter) to construct RecordReader's for CombineFileSplit's.
 * 3. http://www.idryman.org/blog/2013/09/22/process-small-files-on-hadoop-using-combinefileinputformat-1/
 * 4. http://www.idryman.org/blog/2013/09/22/process-small-files-on-hadoop-using-combinefileinputformat-2/
 *
 **/
public class UserCombineFileInputFormat extends CombineFileInputFormat
        implements JobConfigurable {

    // start, from TextInputFormat
    private CompressionCodecFactory compressionCodecs = null;

    public void configure(JobConf conf) {
        compressionCodecs = new CompressionCodecFactory(conf);
    }

    protected boolean isSplitable(FileSystem fs, Path file) {
        final CompressionCodec codec = compressionCodecs.getCodec(file);
        if (null == codec) {
            return true;
        }
        return codec instanceof SplittableCompressionCodec;
    }

    @Override
    public RecordReader<LongWritable, Text> getRecordReader(
            InputSplit genericSplit, JobConf job,
            Reporter reporter)
            throws IOException {

        reporter.setStatus(genericSplit.toString());
        return new LineRecordReader(job, (FileSplit) genericSplit);
    }
    // end, from TextInputFormat
}
