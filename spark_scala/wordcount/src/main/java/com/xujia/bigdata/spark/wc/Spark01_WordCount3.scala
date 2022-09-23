package com.xujia.bigdata.spark.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author xj
 * @create 2022-09-08 14:05
 * */
object Spark01_WordCount3 {
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
    // (you,CompactBuffer((you,1), (you,1)))
    val group: RDD[(String, Iterable[(String, Int)])] = wordOne.groupBy(t => t._1)
    val result = group.map{
      case (word,list) => {  /// t1,t2 表示迭代器中的前后状态--前后(String,Int),类似 t1 = fun(t1, t2)
        list.reduce((t1,t2)=>{(t1._1,t1._2 + t2._2)})
      }
    }

    result.foreach(println)

    sc.stop()
  }

}
