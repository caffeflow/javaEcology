package com.atguigu.mapreduce.flowsum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-07-16:44
 */
public class FlowDriver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        //
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //
        job.setJarByClass(FlowDriver.class);
        //
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);
        //
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //
        boolean b = job.waitForCompletion(true);
        System.out.println(b ? 0 : 1);
    }

    @Test
    public void test_main() throws IOException, InterruptedException, ClassNotFoundException {

        main(new String[] {"D:/11_input/inputflow", "D:/11_input/inputflow_out222"});
    }
}
