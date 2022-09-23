package com.atguigu.mapreduce.weblog;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-16-16:01
 */
public class WebLogMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
//        super.map(key, value, context);
        //
        String s = value.toString();
        boolean b = parseLog(s, context);
        if (!b){
            return;
        }
        context.write(value,NullWritable.get());

    }

    private boolean parseLog(String line,Context context){
        String[] split = line.split(" ");
        if (split.length <= 11){
            return false;
        }else{
            return true;
        }

    }
}
