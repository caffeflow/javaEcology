package com.xujia.spark.core.test

import java.io.{ObjectOutputStream, OutputStream}
import java.net.Socket

/**
 * @author xj    
 * @create 2022-09-09 9:55 
 * */
object Driver {

  def main(args: Array[String]): Unit = {
    // 连接服务器
    val client1 = new Socket("localhost", 9999)
    val client2 = new Socket("localhost", 9988)

    // 分布式处理
    // ---  将客户端的任务对象通过序列化的方式网络传输到服务端
    // ---  ---- 该任务对象有数据、处理逻辑、计算方法,其中计算方法被服务器端调用，所以在远程计算。
    val dataStruct = new MyRDD()
    def process(client:Socket,subTask:SubTask){
      val out: OutputStream = client.getOutputStream
      val objOut: ObjectOutputStream = new ObjectOutputStream(out)
      objOut.writeObject(subTask)
      println(s"$client,${subTask},任务已发送完毕")
      objOut.flush()
      objOut.close()
      out.close()
      client.close()
    }

    // 子任务1
    val subTask1 = new SubTask()
    subTask1.datas = dataStruct.datas.take(dataStruct.datas.length/2)
    subTask1.logic = dataStruct.logic
    process(client1,subTask1)

    // 子任务2
    val subTask2 = new SubTask()
    subTask2.datas = dataStruct.datas.takeRight(dataStruct.datas.length/2)
    subTask2.logic = dataStruct.logic
    process(client2,subTask2)
  }

}
