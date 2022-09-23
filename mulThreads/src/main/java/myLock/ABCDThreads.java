package myLock;

/**
 * @author xj
 * @create 2022-03-29 17:44
 *
 * A B C 三个任务，“A打印5次资源，B打印10次资源，C打印15次资源“ 如此循环3轮
 *
 **/
public class ABCDThreads {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource(); // 创建共享资源对象

        // 多线程编程 第三步： 创建多个线程，调用资源类的定义的方法
        new Thread(() -> {
            shareResource.print1(5);

        }, "A").start();

        new Thread(()->{
            shareResource.print2(10);
        },"B").start();

        new Thread(()->{
            shareResource.print3(15);
        },"C").start();

    }

}

