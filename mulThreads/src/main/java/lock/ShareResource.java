package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xj
 * @create 2022-03-29 17:50
 **/
public class ShareResource {
    // 多线程编程 第一步： 创建资源类，创建属性和操作方法
    // 多线程编程 第二步： 在操作方法中，1 判断 2 干活 3 通知；  操作需要被synchronized修饰，或者使用lock等。

    // 创建lock
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // 共享资源
    private int num=0;

    public void subOne() throws InterruptedException {
        // 上锁
        lock.lock();
        // 判断： 每次被notify都需要判断，若使用if,会产生虚假唤醒问题。
        while (num != 1){
            condition.await();
        }
        // 干活
        try {
            num--;
            System.out.println(Thread.currentThread().getName() + " :: " + num);
        }finally {
            // 通知
            condition.signalAll();
            // 解锁
            lock.unlock();
        }

    }

    public void addOne() throws InterruptedException {
        lock.lock();
        while(num != 0){
            condition.await();
        }
        try {
            num++;
            System.out.println(Thread.currentThread().getName() + " :: " + num);
        }finally {
            // 通知
            condition.signalAll();
            lock.notifyAll();
        }

    }
}
