package com.atguigu.mapreduce.flowsum4_WritableComparable2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author xj
 * @create 2022-02-11-21:08
 */
public class ProvincePartitioner2 extends Partitioner<FlowBean, Text> {

    @Override
    public int getPartition(FlowBean flowBean, Text text, int i) {
        // 处理输入
        String prePhoneNum = text.toString().substring(3);
        // 返回分区号
        if ("136".equals(prePhoneNum)){
            return 0;
        } else if ("137".equals(prePhoneNum)) {
            return 1;
        }else if ("138".equals(prePhoneNum)){
            return 2;
        }else if ("139".equals(prePhoneNum)){
            return 3;
        }else{
            return 4;
        }
    }

    public static int getPartitions(){  // 非必须代码
        return 5;
    }
}
