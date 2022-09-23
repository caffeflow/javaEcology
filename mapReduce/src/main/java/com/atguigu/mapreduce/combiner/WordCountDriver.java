package com.atguigu.mapreduce.combiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-04-17:49
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        // 1 获取配置信息和获取job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        // 2 关联driver程序的jar
        job.setJarByClass(WordCountDriver.class);
        // 3 关联mapper和reducer的jar
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        // 4 关联mapper输出
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);
        // 5 关联最终输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 7 自定义切片大小
        job.setInputFormatClass(CombineTextInputFormat.class);
        CombineTextInputFormat.setMaxInputSplitSize(job,4194304); // 多个文件联合切片
        // 增加combiner 帮助减小reducer负担
        job.setCombinerClass(WordCountReducer.class);
        // 6 设置输入输出的路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        // 提交JOB
        boolean res = job.waitForCompletion(true);
        System.out.println(res ? 0 : 1);
    }

    @Test
    public void test() throws IOException, InterruptedException, ClassNotFoundException {
        String data = WordCountDriver.class.getClassLoader().getResource("input").getPath().substring(1);
        String[] strings = {data + "/inputcombinetextinputformat" , data + "/inputcombinetextinputformat_out2"};
        main(strings);
    }
}
