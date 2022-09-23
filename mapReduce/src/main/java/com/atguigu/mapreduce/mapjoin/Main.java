package com.atguigu.mapreduce.mapjoin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author xj
 * @create 2022-02-15-19:26
 */
public class Main {

    public static void main(String[] args) {
        URL resource = Main.class.getResource("/com/atguigu/mapreduce/input");

        String root = resource.getPath().substring(1);
        String cacheFile = "file:///"+ root + "/tablecache/pd.txt";
        System.out.println(cacheFile);
        String[] paths = {root + "/inputtable2" ,cacheFile, root + "/inputtable2_mapjoinOut2"};
        try {
            MapJoinDriver.main(paths);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
