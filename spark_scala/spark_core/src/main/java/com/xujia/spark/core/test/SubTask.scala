package com.xujia.spark.core.test

/**
 * @author xj    
 * @create 2022-09-09 14:06 
 * */
class SubTask extends Serializable {
  var datas : List[Int] = _
  var logic :Int =>Int = _
  // 计算
  def compute():List[Int] = {
    datas.map(logic)
  }
}
