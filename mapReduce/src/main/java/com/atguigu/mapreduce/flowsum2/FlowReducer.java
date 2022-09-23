package com.atguigu.mapreduce.flowsum2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-07-16:34
 */
public class FlowReducer extends Reducer<Text, FlowBean,Text, FlowBean> {
    FlowBean v = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow = 0;
        long sumDownFlow = 0;
        for (FlowBean value : values) {
            sumUpFlow += value.getUpflow();
            sumDownFlow += value.getDownflow();
        }
        v.setUpflow(sumUpFlow);
        v.setDownflow(sumDownFlow);
        v.setSumflow();
        context.write(key,v);
    }
}
