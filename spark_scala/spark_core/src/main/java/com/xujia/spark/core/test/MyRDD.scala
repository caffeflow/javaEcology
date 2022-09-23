package com.xujia.spark.core.test

/**
 * @author xj    
 * @create 2022-09-09 10:11 
 * */
class MyRDD extends {
  // 在spark中,RDD是最小的逻辑单元(此处只是模拟RDD,并非复现),而完整的逻辑由多个不同的RDD来构建。
  // 完整的数据
  val datas:List[Int] = List(1,2,3,4)
  // 完整的逻辑
  val logic: Int => Int = 2 * _
}
