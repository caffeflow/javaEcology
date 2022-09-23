package help;

import java.util.concurrent.CountDownLatch;

/**
 * @author xj
 * @create 2022-04-03 14:38
 **/
public class CountDownLatchDomo {
    // 6个同学都离开教室，班长才锁门。
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6); // key
        for (int i=1;i<=6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +"::"+ "离开教室");
                countDownLatch.countDown(); // key
            },""+i).start();
        }

        // 等待
        countDownLatch.await(); // key

        System.out.println("班长锁门了");
    }
}
