package com.atguigu.mapreduce.wordcount2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-04-17:39
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text,IntWritable> {
    int sum;
    IntWritable v = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        sum = 0;
        for (IntWritable count : values){
            sum += count.get();
        }
        v.set(sum);
        context.write(key,v);
    }

}
