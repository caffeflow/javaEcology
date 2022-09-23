package com.atguigu.mapreduce.reducejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author xj
 * @create 2022-02-15-16:39
 */
public class TableReducer extends Reducer<Text,TableBean,TableBean, NullWritable> {
    ArrayList<TableBean> orderTableBeans = new ArrayList<TableBean>();
    TableBean pdTableBean;

    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Reducer<Text, TableBean, TableBean, NullWritable>.Context context) throws IOException, InterruptedException {
//        super.reduce(key, values, context);
        for (TableBean value : values) {

            if ("order".equals(value.getFlag())){
                TableBean tableBean = new TableBean();
                try {
                    orderTableBeans.add((TableBean) BeanUtils.cloneBean(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    pdTableBean =  (TableBean)BeanUtils.cloneBean(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            for (TableBean orderTableBean : orderTableBeans) {
                orderTableBean.setPname(pdTableBean.getPname());
                context.write(orderTableBean,NullWritable.get());
            }
        }
    }
}
