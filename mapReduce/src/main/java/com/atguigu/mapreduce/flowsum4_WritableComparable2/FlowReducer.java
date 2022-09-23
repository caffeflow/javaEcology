package com.atguigu.mapreduce.flowsum4_WritableComparable2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-07-16:34
 */
public class FlowReducer extends Reducer<FlowBean,Text, Text, FlowBean> {
    // 输入见Mapper输出
    FlowBean outV = new FlowBean();

    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            // 调换kv位置，反向写出
            context.write(value,key);
        }
    }
}
