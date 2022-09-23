package com.atguigu.mapreduce.flowsum;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-07-15:46
 */
public class FlowBean implements Writable {
    private long upflow = 0;
    private long downflow = 0;
    private long sumflow  = 0;

    @Override
    public String toString() {
        return upflow + "\t" + downflow + "\t" + sumflow;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upflow);
        dataOutput.writeLong(downflow);
        dataOutput.writeLong(sumflow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        upflow = dataInput.readLong();
        downflow = dataInput.readLong();
        sumflow = dataInput.readLong();
    }

    public long getUpflow() {
        return upflow;
    }

    public void setUpflow(long upflow) {
        this.upflow = upflow;
    }

    public long getDownflow() {
        return downflow;
    }

    public void setDownflow(long downflow) {
        this.downflow = downflow;
    }

    public void setSumflow(){
        this.sumflow = this.downflow + this.upflow;
    }

    public long getSumflow(){
        this.setSumflow();
        return sumflow;
    }

}
