package com.atguigu.mapreduce.reducejoin;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-15-17:10
 */
public class Main {
    public static void main(String[] args) {
        String root = Main.class.getResource("/com/atguigu/mapreduce/input").getPath().substring(1);
        String[] paths = {root + "/inputtable",root + "/inputtable_out4"};
        try {
            TableDriver.main(paths);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
