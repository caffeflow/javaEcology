package com.atguigu.mapreduce.mapjoin;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

/**
 * @author xj
 * @create 2022-02-15-19:27
 */
public class MapJoinMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    private HashMap<String, String> pdMap;
    private Text text = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
//        super.setup(context);
        // 通过缓存文件得到小表数据
        pdMap = new HashMap<>();
        URI[] cacheFiles = context.getCacheFiles();
        FileSystem fs = FileSystem.get(context.getConfiguration());
        FSDataInputStream fis = fs.open(new Path(cacheFiles[0]));// 开流
        BufferedReader breader = new BufferedReader(new InputStreamReader(fis, "utf-8"));
        String line;
        while(StringUtils.isNotEmpty(line = breader.readLine())){
            String[] split = line.split("\t");
            pdMap.put(split[0],split[1]); // pid pname
        }
        //
        IOUtils.closeStreams(breader);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        super.map(key, value, context);
        String[] split = value.toString().split("\t");
        text.set(split[0] +"\t" + pdMap.get(split[1]) + "\t" + split[2]);
        context.write(text,NullWritable.get());
    }
}
