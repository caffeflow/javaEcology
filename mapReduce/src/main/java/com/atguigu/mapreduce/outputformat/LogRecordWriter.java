package com.atguigu.mapreduce.outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-12-6:02
 */
public class LogRecordWriter extends RecordWriter<Text, NullWritable> {

    private FSDataOutputStream atguiguOut;
    private FSDataOutputStream otherOut;



    @Override
    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException { // 写入
        // 处理输入
        String log = text.toString() + "\n";
        // 处理输出
        if(log.contains("atguigu")){
            atguiguOut.writeBytes(log);
        }else{
            otherOut.writeBytes(log);
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {  // 关闭自定义流
        IOUtils.closeStreams(atguiguOut,otherOut); // 是xxx.close()的封装
    }


    public LogRecordWriter(TaskAttemptContext job,Path atguiguOut,Path otherOut) {  // 创建自定义流
        try {
            // 获取文件系统对象
            FileSystem fs = FileSystem.get(job.getConfiguration());
            // 用文件系统对象创建流
            this.atguiguOut = fs.create(atguiguOut);
            this.otherOut = fs.create(otherOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
