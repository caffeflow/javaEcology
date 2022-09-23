package com.xujia.spark.core.test

import java.io.{InputStream, ObjectInputStream}
import java.net.{ServerSocket, Socket}

/**
 * @author xj    
 * @create 2022-09-09 10:11 
 * */
object Executor {
  def main(args: Array[String]): Unit = {
    // 启动服务器，接受数据
    val port = 9999
    val server = new ServerSocket(port)// 服务器--监听端口
    println(s"服务器启动...监听端口=$port")
    // 等待客户端连接 --- 拿到序列化数据，并转化为对象，并调用对象中的计算方法，执行计算过程。
    while(true){
      val client: Socket = server.accept()
      val in : InputStream = client.getInputStream()
      val objIn = new ObjectInputStream(in)
      val task: SubTask = objIn.readObject().asInstanceOf[SubTask]
      val ints: List[Int] = task.compute()
      println(s"计算节点[$port]的结果为: $ints")
      objIn.close()
      in.close()
      client.close()
    }
  }

}
