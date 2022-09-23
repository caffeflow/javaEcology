package com.atguigu.mapreduce.flowsum4_WritableComparable2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-07-16:03
 */
public class FlowMapper extends Mapper<LongWritable, Text, FlowBean,Text> {
    // 函数输入     行号、13975057813	11058	48243	59301
    // 函数输出     FlowBean、手机号  （这里使用flowBean作为key来进行轴的排序，当在reduce阶段时再将手机号作为key输出到结果）

    Text outV = new Text();
    FlowBean outK = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 处理输入
        String[] flow = value.toString().split("\t");
        String phoneNum = flow[0],upFlow=flow[1],downFlow=flow[2],sumFlow = flow[3];
        // 处理输出
        outK.setUpflow(Long.parseLong (upFlow));
        outK.setDownflow(Long.parseLong(downFlow));
        outK.setSumflow(Long.parseLong(sumFlow));
        outV.set(phoneNum);
        // write out
        context.write(outK,outV);
    }
}
