package com.atguigu.mapreduce.flowsum2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-07-16:03
 */
public class FlowMapper extends Mapper<LongWritable, Text,Text, FlowBean> {
    FlowBean v = new FlowBean();
    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split("\t");
        //  key
        String phoneNum = split[1];
        k.set(phoneNum); // key:手机号
        // value
        v.setUpflow(Long.parseLong ((split[split.length-3])));
        v.setDownflow(Long.parseLong((split[split.length-2])));
        v.setSumflow();
        // write out
        context.write(k,v);
    }
}
