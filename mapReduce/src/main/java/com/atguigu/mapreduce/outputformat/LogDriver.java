package com.atguigu.mapreduce.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-12-6:24
 */
public class LogDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        // args: input目录 atguiguOut文件  otherOut文件   SUCCESS文件目录
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(LogDriver.class);
        job.setReducerClass(LogReducer.class);
        // Mapper<LongWritable, Text,Text,NullWritable>
        job.setMapperClass(LogMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        //Reducer<Text, NullWritable,Text, NullWritable>
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        // 输入目录
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        // NOTE: 自定义outputformat,它的父类fileoutputformat会输出一个SUCCESS文件，虽然LogOutPutFormat已经设置了io目录，但这里还要指定SUCCESS文件的输出目录。
        job.setOutputFormatClass(LogOutputFormat.class);
        LogOutputFormat.setOutputs(args[1],args[2]); // 目标文件的IO目录
        FileOutputFormat.setOutputPath(job,new Path(args[3])); // SUCCESS文件
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
