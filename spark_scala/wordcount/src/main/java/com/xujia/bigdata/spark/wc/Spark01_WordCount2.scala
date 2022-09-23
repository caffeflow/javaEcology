package com.xujia.bigdata.spark.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author xj    
 * @create 2022-09-08 14:05 
 * */
object Spark01_WordCount2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("wc").set("spark.testing.memory","471859200")
    val sc = new SparkContext(conf)
    // 数据源 -> rdd对象
//    val file : String = Spark01_WordCount2.getClass.getClassLoader.getResource("data/word.txt").getFile
    val file :String = "data/word.txt"
    val line : RDD[String] = sc.textFile(file)
    // 转化过程
    val word : RDD[String] = line.flatMap(x => x.split(" "))
    val wordOne : RDD[(String,Int)] = word.map(x => (x,1))
    val result : RDD[(String,Int)] = wordOne.reduceByKey((x,y)=>{
      println(x + ":" + y)
      x+y

    })
    result.foreach(println)
    sc.stop()
  }

}
