package com.atguigu.mapreduce.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-15-16:36
 */
public class TableMapper extends Mapper<LongWritable,Text, Text,TableBean> {
    // 将数据转载到tablebean中。
    private String curFileName;
    private Text pid = new Text();
    private TableBean tableBean = new TableBean();;

    @Override
    protected void setup(Mapper<LongWritable, Text, Text, TableBean>.Context context) throws IOException, InterruptedException {
        // 在初始化时，获取当前处理文件的名字
        InputSplit inputSplit = context.getInputSplit();
        curFileName = ((FileSplit) inputSplit).getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, TableBean>.Context context) throws IOException, InterruptedException {
        // super.map(key, value, context);
        String[] split = value.toString().split("\t");
        if(curFileName.contains("order")){
            pid.set(split[1]);
            tableBean.setId(split[0]);
            tableBean.setPid(split[1]);
            tableBean.setAmount(Integer.parseInt(split[2]));
            tableBean.setPname("");
            tableBean.setFlag("order");
        }else{
            pid.set(split[0]);
            tableBean.setId("");
            tableBean.setPid(split[0]);
            tableBean.setAmount(0);
            tableBean.setPname(split[1]);
            tableBean.setFlag("pd");
        }

        context.write(pid,tableBean);
    }
}
