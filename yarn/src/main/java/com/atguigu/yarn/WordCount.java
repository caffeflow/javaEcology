package com.atguigu.yarn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-17-9:54
 */
public class WordCount implements Tool {

    private Configuration conf;

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(conf);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        job.setJarByClass(WordCountDriver.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        return job.waitForCompletion(true) ? 0 : 1;
    }

    @Override
    public void setConf(Configuration configuration) {
        conf = configuration;
    }

    @Override
    public Configuration getConf() {
        return conf;
    }

    public static class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
        private Text text = new Text();
        private IntWritable intWritable = new IntWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
//            super.map(key, value, context);
            String[] s = value.toString().split(" ");
            for (String s1 : s) {
                text.set(s1);
                context.write(text,intWritable);
            }
        }
    }

    public static class WordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
        private int sum = 0;
        private IntWritable intWritable = new IntWritable();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
//            super.reduce(key, values, context);
            sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            intWritable.set(sum);
            context.write(key,intWritable);
        }
    }
}
