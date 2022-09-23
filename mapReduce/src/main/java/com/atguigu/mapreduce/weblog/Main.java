package com.atguigu.mapreduce.weblog;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-02-16-16:01
 */
public class Main {

    public static void main(String[] args) {
        String substring = Main.class.getResource("/com/atguigu/mapreduce/input").getPath().substring(1);
        String[] paths = {substring + "/web",substring + "/web_out3"};
        System.out.println(paths[0]);
//        System.exit(0);
        try {
            WebLogDriver.main(paths);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
