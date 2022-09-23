package myLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xj
 * @create 2022-03-29 17:50
 **/
public class ShareResource {
    // 多线程编程 第一步： 创建资源类，创建属性和操作方法
    // 多线程编程 第二步： 在操作方法中，1 判断 2 干活 3 通知；  操作需要被synchronized修饰，或者使用lock等。
    // 多线程目标： A B C 三个任务，“A打印5次资源，B打印10次资源，C打印15次资源“ 如此循环3轮

    // 资源
    private int num=0;
    // 锁
    ReentrantLock lock =  new ReentrantLock();
    Condition condition_print5 = lock.newCondition(); // 每个操作方法 对应 一个 condition控制
    Condition condition_print10 = lock.newCondition();
    Condition condition_print15 = lock.newCondition();
    int seq = 0; // 线程执行的附加条件。

    public void print1(int loop) {
        // 注意：第一步就是上锁，上锁后，不满足条件再await。
        // 上锁
        lock.lock();
        try{
            // 判断
            while(seq != 0){
                condition_print5.await();  // 阻塞当前线程
            }
            // 干活
            while(--loop >=0){
                System.out.println(Thread.currentThread().getName() + "::" + num);
            }
            // 通知
            seq = 1; // 指定下一位线程的附加执行条件。
            condition_print5.signalAll();  // 所有阻塞线程进入就绪状态
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public void print2(int loop){
        lock.lock();
        try {
            while(seq != 1){
                condition_print10.await();
            }
            while(--loop >=0 ){
                System.out.println(Thread.currentThread().getName() + "::" + num);
            }
            seq=2;
            condition_print10.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print3(int loop){
        lock.lock();
        try {
            while(seq != 2){
                condition_print15.await();
            }
            while(--loop >=0){
                System.out.println(Thread.currentThread().getName() + "::" + num);
            }
            seq=0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            condition_print15.signalAll();
            lock.unlock();
        }

    }

}
