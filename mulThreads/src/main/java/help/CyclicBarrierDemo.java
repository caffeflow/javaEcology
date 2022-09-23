package help;

import java.util.concurrent.CyclicBarrier;

/**
 * @author xj
 * @create 2022-04-03 14:58
 **/
public class CyclicBarrierDemo {
    static int NUM = 7;

    public static void main(String[] args) {
        // 集合7颗龙珠，可以召唤神龙
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM, () -> {
            System.out.println("集齐7颗龙珠，可以召唤神龙");
        });
        // 集齐7颗龙珠的过程
        for (int i=1;i<=7;i++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " 龙珠被收集了");
                    cyclicBarrier.await(); // 等待
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }

    }
}
