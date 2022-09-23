package futureTaskCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xj
 * @create 2022-04-03 13:57
 **/
public class FutureDemo {
    // 主线程计算1+..+100：现在开辟3个辅助线程，线程map1和线程map2分摊子计算，线程reduce规约子计算的结果，主线程main打印线程reduce的结果。

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // callable接口 -- 支持返回值
        FutureTask<Integer> ft1 = new FutureTask<Integer>(()->{
            System.out.println(Thread.currentThread().getName() +"::" + "进行子计算");
            int i = 1,res=0;
            while(i <= 50){res+=i++;}
            return res;
        });

        // callable接口 -- 支持返回值
        FutureTask<Integer> ft2 = new FutureTask<Integer>(()->{
            System.out.println(Thread.currentThread().getName() +"::" + "进行子计算");
            int i = 51,res=0;
            while(i <= 100){res+=i++;}
            return res;
        });

        FutureTask<Integer> ft3 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() +"::" + "等待子计算结果");
            while (!ft1.isDone() || !ft2.isDone()) {} //
            System.out.println(Thread.currentThread().getName() +"::" + "规约子计算结果");
            int res = ft1.get() + ft2.get();
            return res;
        });

        // 执行辅助线程-部分计算
        new Thread(ft1,"map1").start();
        new Thread(ft2,"map2").start();
        // 执行辅助线程-规约计算结果
        new Thread(ft3,"Reduce").start();

        // 主进程打印结果
        while(!ft3.isDone()){};
        System.out.println(Thread.currentThread().getName() + "::计算结果：" + ft3.get());
    }
}


