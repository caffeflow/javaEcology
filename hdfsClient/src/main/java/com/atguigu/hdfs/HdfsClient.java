package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class HdfsClient {

    @Test
    public void tetsMkdirs() throws IOException, URISyntaxException, InterruptedException {
        // 1
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration, "atguigu");
        // 2
        fs.mkdirs(new Path("/xiyou/huaguoshan/"));
        // 3
        fs.close();
    }

    @Test
    public void testCopyFromLocalFile() throws URISyntaxException, IOException, InterruptedException {
        // 1
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","2");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration, "atguigu");
        // 2
        fs.copyFromLocalFile(new Path("F:/bigdata/demo/sunwukong.txt"),new Path("/xiyou/huaguoshan"));
        // 3
        fs.close();
    }

    @Test
    public void testCopyToLocalFile() throws URISyntaxException, IOException, InterruptedException {
        // 1
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","2");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration, "atguigu");
        // 2
        fs.copyToLocalFile(false,new Path("/xiyou/huaguoshan/sunwukong.txt"),new Path("f:/bigdata/demo/sunwukong2.txt"));
        // 3
        fs.close();
    }


    @Test
    public void testRename() throws URISyntaxException, IOException, InterruptedException {
        // 1
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","2");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration, "atguigu");
        // 2
        fs.rename(new Path("/xiyou/huaguoshan/sunwukong.txt"),new Path("/xiyou/huaguoshan/meihouwang.txt"));
    }


    @Test
    public void testDelFile() throws URISyntaxException, IOException, InterruptedException {
        // 1
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","2");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"),configuration,"atguigu");
        // 2
        fs.delete(new Path("/xiyou/"),true);
        // 3
        fs.close();
    }

    @Test
    public void testListFile() throws URISyntaxException, IOException, InterruptedException {
        // 1
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","2");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"),configuration,"atguigu");
        // 2
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"),true);
        while (listFiles.hasNext()){
            LocatedFileStatus ll = listFiles.next();
            System.out.println("=====" + ll.getPath() + "=====");
            System.out.println(ll.getPermission());
            System.out.println(ll.getOwner());
            System.out.println(ll.getGroup());
            System.out.println(ll.getLen());
            System.out.println(ll.getModificationTime());
            System.out.println(ll.getBlockSize());
            System.out.println(ll.getReplication());
            System.out.println(ll.getPath().getName());
            //  获取块信息
            System.out.println("块信息");
            BlockLocation[] blockLocations = ll.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
        }
        // -1
        fs.close();
    }

    @Test
    public void testListStatus() throws URISyntaxException, IOException, InterruptedException {
        // 1
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication","2");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"),configuration,"atguigu");
        // 2
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses){
            if (fileStatus.isFile()){
                System.out.println("f:" + fileStatus.getPath().getName() + "/");
            }else{
                System.out.println("d:" + fileStatus.getPath().getName() + "/");
            }
        }
        // 3
        fs.close();
    }
}
