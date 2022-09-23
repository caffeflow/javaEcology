package com.atguigu.mapreduce.outputformat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-11-22:36
 */
public class LogOutputFormat extends FileOutputFormat<Text, NullWritable> {

    private static Path atguiguOut;
    private static Path otherOut;


    public static void setOutputs(String atguiguOut,String otherOut) {
        LogOutputFormat.atguiguOut = new Path(atguiguOut);
        LogOutputFormat.otherOut = new Path(otherOut);
    }
    @Override
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        LogRecordWriter logRecordWriter = new LogRecordWriter(job,this.atguiguOut,this.otherOut);
        return logRecordWriter;
    }
}
