package com.atguigu.mapreduce.combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-04-17:27
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable>{
    Text k  = new Text();
    IntWritable v = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");
        for (String word : words){
            k.set(word);
            context.write(k,v);
        }
    }

}
