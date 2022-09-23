package deadLock;

/**
 * @author xj
 * @create 2022-04-03 9:59
 * 死锁演示，进程间相互等待资源。资源：等待对方的输出，或等待对方释放锁
 **/
public class DeadLock {
    // 定义两个资源，作为锁。
    static Object obj1  = new Object();
    static Object obj2 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (obj1){
                System.out.println(Thread.currentThread().getName() +"::"+ "持有锁obj1,等待锁obj2" );
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName() + "::" + "获取到锁obj2");
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized (obj2){
                System.out.println(Thread.currentThread().getName() +"::"+ "持有锁obj2,等待锁obj1" );
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName() + "::" + "获取到锁obj1");
                }
            }
        },"B").start();
    }
}
