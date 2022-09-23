
import cn.hutool.core.util.RandomUtil;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xj
 * @create 2022-09-19 21:16
 **/
public class Test {
    public static void main(String[] args) {
        BlockingQueue[] blockingQueueArr = new LinkedBlockingQueue[2];
        for (int i = 0; i < blockingQueueArr.length; i++) {
            blockingQueueArr[i] = new LinkedBlockingQueue<List<Data>>(1000);
        }
        new Thread(()->{
            new Producer().build(5,blockingQueueArr,2).startAll();
        }).start();
        new Thread(()->{
            new Consumer().build(5,blockingQueueArr).startAll();
        }).start();
    }
}
@lombok.Data
@lombok.Builder
class Data{
    Integer age;
    String name;

    @Override
    public String toString() {
        return "age=" + age + ", name='" + name ;
    }
}
class Producer{
    int threadNum = 1;
    int batchSize = 1;
    ThreadPoolExecutor threadPool = null;
    BlockingQueue[] blockingQueueArr = null;
    public Producer build(int threadNum, BlockingQueue[] blockingQueueArr,int batchSize){
        this.threadNum = threadNum;
        this.batchSize = batchSize;
        this.blockingQueueArr = blockingQueueArr;
        threadPool =  new ThreadPoolExecutor(threadNum, threadNum, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return this;
    }
    public void startAll(){
        AtomicInteger batchCount = new AtomicInteger(0);
        // 随机数迭代器 --- 模拟JDBC连接CSV文件
        Iterator<Data> syncReader = new Iterator<Data>(){
            ReentrantLock lock = new ReentrantLock();
            @Override
            public boolean hasNext() {
                return true;
            }
            int num  = 0;

            @Override
            public Data next() {
                lock.lock();
                int curNum = num++;
                lock.unlock();
                Data data = Data.builder()
                        .name(RandomUtil.randomString(5))
//                        .age(RandomUtil.randomInt(2))
                        .age(curNum)
                        .build();
                return data;
            }
        };
        for (int i = 0; i < threadNum; i++) {
            start(syncReader,batchSize,batchCount);
        }
    }
    public void start(Iterator<Data> syncReader,int batchSize, AtomicInteger batchCount){
        threadPool.execute(()->{
            while(true){
                // 产生批数据。
                List<Data> batch = new ArrayList<>();
                for (int i = 0; i < batchSize && syncReader.hasNext(); i++) {
                    Data data = syncReader.next();
                    batch.add(data);
                }
                int channelId = batchCount.getAndIncrement() % this.blockingQueueArr.length;
                try {
                    this.blockingQueueArr[channelId].put(batch);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


class Consumer{
    BlockingQueue[] blockingQueueArr;
    ThreadPoolExecutor threadPool;
    int threadNum = 1;
    public Consumer build(int threadNum, BlockingQueue[] blockingQueueArr){
        this.threadNum = threadNum;
        this.blockingQueueArr = blockingQueueArr;
        threadPool = new ThreadPoolExecutor(
                threadNum,
                threadNum * 5,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue <Runnable> (),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                executor.getQueue().put(r);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        return this;
    }

    public void startAll(){
        AtomicInteger batchCount = new AtomicInteger(0);
        for (int i = 0; i < threadNum; i++) {
            start(batchCount);
        }
    }

    public void start(AtomicInteger batchCount){
        threadPool.execute(()->{
            while(true){
                int channelId = batchCount.getAndIncrement() % this.blockingQueueArr.length;
                try {
                    List<Data> batch = (List<Data>) this.blockingQueueArr[channelId].take();
                    for (int i = 0; i < batch.size(); i++) {
                        Data data = batch.get(i);
                        System.out.println(Thread.currentThread().getName() + ":" + data.toString() + " ----队列" + channelId +",大小:" + this.blockingQueueArr[channelId].size());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
