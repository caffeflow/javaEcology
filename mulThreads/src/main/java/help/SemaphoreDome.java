package help;

import java.util.concurrent.Semaphore;

/**
 * @author xj
 * @create 2022-04-03 15:10
 **/
public class SemaphoreDome {
    // 6辆汽车停在3个停车位

    static int NUM  =3;

    public static void main(String[] args) {
        // 创建信号量，设置许可数量
        Semaphore semaphore = new Semaphore(3);

        // 模拟6辆汽车停车
        for (int i=1;i<=6;i++){
            new Thread(()->{
                try{
                    // 抢占许可证
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"::"+"抢到停车位" );
                    Thread.sleep((int)(5*Math.random()));
                    System.out.println(Thread.currentThread().getName()+"::"+"-----离开停车位" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },""+i).start();
        }
    }
}
