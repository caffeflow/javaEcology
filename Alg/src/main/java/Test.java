import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xj
 * @create 2022-09-15 16:39
 **/
public class Test {

    static AtomicInteger num = new AtomicInteger(100);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                while (num.get() > 0){
                    System.out.println("thread name:" + Thread.currentThread().getName() + ":" + num.decrementAndGet());
                }
            }).start();
        }
    }
}

class  TestAQS extends AbstractQueuedSynchronizer{
    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }

    public static void main(String[] args) {
        TestAQS aqs = new TestAQS();
        aqs.acquire(100); // 等待获取锁
        // 执行逻辑
        // 释放锁
        aqs.release(100);

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
    }
}
