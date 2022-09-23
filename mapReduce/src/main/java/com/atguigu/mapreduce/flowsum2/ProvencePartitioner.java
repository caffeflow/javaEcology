package com.atguigu.mapreduce.flowsum2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author xj
 * @create 2022-02-08-22:16
 */
public class ProvencePartitioner extends Partitioner<Text,FlowBean> {

    public static int numPartitions = 5;
    @Override
    public int getPartition(Text key, FlowBean value, int numPartitions) {
        // 手机号前3位来判别省份，并做数据分区。
        String prePhone = key.toString().substring(0,3);
        //
        int partition;
        if(prePhone.equals("136")){
            partition = 0;
        } else if (prePhone.equals("137")) {
            partition= 1;
        } else if (prePhone.equals("138")) {
            partition = 2;
        } else if (prePhone.equals("139")) {
            partition = 3;
        }else {
            partition= 4;
        }
        // 返回分区号
        return partition;
    }

}
