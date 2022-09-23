package com.atguigu.mapreduce.outputformat;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-11-22:24
 */
public class Main {

    public static void main(String[] args) {
        String path = Main.class.getResource("../").getPath().substring(1);
        String  input_dir = path + "input/inputoutputformat";
        String output_dir =  path + "input/inputoutputformat_out2/";
        String[] paths = {input_dir, output_dir + "atguigu.log",
                output_dir + "other.log",
                output_dir + "success"};
        try {
            LogDriver.main(paths);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
