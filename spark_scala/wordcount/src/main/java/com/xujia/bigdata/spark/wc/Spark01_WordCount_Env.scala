package com.xujia.bigdata.spark.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author xj
 * @create 2022-09-08 9:54
 * */
object Spark01_WordCount_Env {
  def main(args: Array[String]): Unit = {
    // TODO 使用spark --- 计算框架
    // TODO 1 增加spark依赖
    // TODO 2 获取spark连接(setMaster指定环境,setAppName指定任务名称) 注：本地运行注意控制内存
    val conf = new SparkConf().setMaster("local").setAppName("WordCount").set("spark.testing.memory","471859200")
    val sc = new SparkContext(conf);
    // TODO 3 执行业务操作
    // 读取文件
    val lines : RDD[String] = sc.textFile("data/word.txt")
    // 将文件数据进行分词
    val words : RDD[String] = lines.flatMap(_.split(" "))
    // 将分词后的数据进行分组
    val wordGroup : RDD[(String,Iterable[String])] = words.groupBy(word => word)
    // 将分组后的数据进行统计
    val wordCount : RDD[(String,Int)] = wordGroup.mapValues(_.size)
    // 将统计结果打印在控制台
    wordCount.collect().foreach(println)
    // TODO 最后一步：释放Spark连接
    sc.stop();
  }
}
