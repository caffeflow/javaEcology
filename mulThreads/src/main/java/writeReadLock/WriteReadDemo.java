package writeReadLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xj
 * @create 2022-04-03 15:51
 **/
public class WriteReadDemo {
    // 对资源的并发读写进行控制(hashmap作为资源)  目标：写是独占的，读是共享的。

    public static void main(String[] args) {
        // 创建资源
        MyCache myCache = new MyCache();

        // 模拟3个写线程
        for (int i=1;i<=3;i++){
            myCache.put(i+"",i+"");
        }
        // 模拟3个读线程
        for (int i=1; i<=3;i++){
            myCache.get(i+"");
        }
    }
}

// 定义资源类 -- map集合为资源
class MyCache{
    // 定义资源
    public volatile Map<String,Object> map = new HashMap<>();
    // 定义读写锁
    private ReentrantReadWriteLock rwLock =  new ReentrantReadWriteLock();

    // 定义资源操作
    public void put(String k,String v){
        // 写锁 -- 独占锁
        rwLock.writeLock().lock();
        try{
            // 判断
            // 执行
            System.out.println(Thread.currentThread().getName() + "::"+ k +"::" +"写ing");
            map.put(k,v);
            Thread.sleep((int)(4* Math.random()));
            System.out.println(Thread.currentThread().getName() + "::" + k +"::"+"写ok");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放写锁
            rwLock.writeLock().unlock();
        }
    }

    public Object get(String k){
        // 读锁 -- 共享锁
        rwLock.readLock().lock();
        Object res = null;
        try{
            // 判断
            // 执行
            System.out.println(Thread.currentThread().getName() + "::" + k +"::"+"读ing");
            res =  map.get(k);
            Thread.sleep((int)(4* Math.random()));
            System.out.println(Thread.currentThread().getName() + "::" + k +"::" + "读ok");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放读锁
            rwLock.readLock().unlock();
        }
        return res;  // 注意返回值
    }
}


