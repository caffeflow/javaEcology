package com.atguigu.mapreduce.outputformat;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-11-22:32
 */
public class LogReducer extends Reducer<Text, NullWritable,Text, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        // 防止有相同数据，迭代写出
        for (NullWritable value : values) {
            context.write(key, NullWritable.get());
        }
    }
}
