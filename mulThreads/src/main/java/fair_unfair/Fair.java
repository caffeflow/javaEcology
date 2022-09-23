package fair_unfair;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xj
 * @create 2022-04-03 13:19
 **/
public class Fair {
    public static void main(String[] args) {
        Resouce resouce = new Resouce(false);  // 公平
        new Thread(()->{
            for(int i=0; i < 100;i++){
                resouce.sub(getRandom(1,6));
            }
        },"A").start();
        new Thread(()->{
            for(int i=0; i < 100;i++){
                resouce.sub(getRandom(1,6));
            }
        },"B").start();
        new Thread(()->{
            for(int i=0; i < 100;i++){
                resouce.sub(getRandom(1,6));
            }
        },"C").start();
    }

    public static int getRandom(int min,int max){
        return (int)((max-min)*Math.random() + min);
    }
}


class Resouce{
    // 定义锁，定义资源，定义资源的操作，定义资源的操作条件。
    public int total = 100;
    public ReentrantLock lock;
    public Condition con;

    public Resouce(boolean fair){
        lock = new ReentrantLock(fair); // 公平锁
        con = lock.newCondition();
    }


    public void sub(int num) {
        lock.lock();
        try{
            while(total < num){
                con.await();
            }
            total -= num;
            System.out.println(Thread.currentThread().getName() +"::销售"+ num +"::剩余"  + total);
            con.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
