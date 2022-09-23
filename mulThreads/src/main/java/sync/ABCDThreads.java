package sync;

/**
 * @author xj
 * @create 2022-03-29 17:44
 *
 * A B C D 四个任务，A和C只在资源num=1时，做-1操作，B和D只在资源num=0时，做+1操作。
 **/
public class ABCDThreads {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource(); // 创建共享资源对象

        // 多线程编程 第三步： 创建多个线程，调用资源类的定义的方法
        new Thread(()->{
            for (int i = 1; i <= 10; i++){
                try {
                    shareResource.addOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++){
                try {
                    shareResource.subOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++){
                try {
                    shareResource.addOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++){
                try {
                    shareResource.subOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

