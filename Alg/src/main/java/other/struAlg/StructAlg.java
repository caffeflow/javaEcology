package other.struAlg;

import org.junit.Test;

import javax.lang.model.element.VariableElement;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author xj
 * @create 2022-09-14 8:15
 **/
public class StructAlg {


    /**
     * 栈
     * NOTE: 不推荐使用 Java 的 Vector 以及其子类 Stack ，而一般将 LinkedList 作为栈来使用
     * ArrayDeque实现Deque接口，Stack继承于Vector，LinkedList实现Deque与List接口
     */
    @Test
    public void  stack(){
        Stack<Integer> stack = new Stack<>();
        Integer push = stack.push(1);
        System.out.println(push);
        Integer pop = stack.pop();
        System.out.println(pop);
    }

    /**
     * linkedList  --- 队列/栈
     * 它非同步类 ----  使用Collections工具类中synchronizedXxx()将线程不同步的ArrayDeque以及LinkedList转换成线程同步。
     */
    @Test
    public void linkedListLikeStack(){
        LinkedList<Integer> stack =  new LinkedList<>();
        stack.addLast(1); //         stack.push(1);
        stack.addLast(2); //         stack.push(1);
        System.out.println(stack.removeLast()); // stack.pop()
        System.out.println(stack.removeLast()); // stack.pop()

        LinkedList<Integer> queue =  new LinkedList<>();
        queue.addLast(1); // queue.offer(1);
        queue.offer(2); //queue.offer(2);
        System.out.println(queue.removeFirst());  // queue.poll()
        System.out.println(queue.removeFirst()); // queue.poll()

        // 同步方法
//        List<Integer> integers = Collections.synchronizedList(stack);
//        ConcurrentLinkedDeque<Integer> stack1 = new ConcurrentLinkedDeque<>();
    }
    /**
     * 树
     */


    /**
     * 图 -- 表示方法：邻接矩阵
     */
    @Test
    public void graph1(){
        // 顶点集
        Integer[] vertices = {1,2,3};
        // 边集  --- 分为有向图和无向图
        Integer[][] edges = {  // nxn矩阵,矩阵元素0或1表示是否有边
                {0,1,1},
                {0,1,0},
                {0,0,1}
        };
    }

    /**
     * 图 -- 表示方法：邻接表
     */
    @Test
    public void graph2(){
        // 顶点集
        Integer[] vertices = {1,2,3};
        // 边集  --- 分为有向图和无向图
        // 二维容器,第一维表示具体结点,第二维度表示边
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(0,1,2)));
        edges.add(new ArrayList<>(Arrays.asList(0,2)));
        edges.add(new ArrayList<>(Arrays.asList(1,2)));
    }

    /**
     * 散列表
     */
    @Test
    public void dic(){
        Map<String,Integer> dic = new HashMap<>();
        Integer xujia1 = dic.put("xujia", 20);
        Integer xujia = dic.get("xujia");
        Set<String> strings = dic.keySet();
        Integer[] vals = new Integer[dic.size()];
        dic.values().toArray(vals);
        System.out.println(vals[0]);
    }

    /**
     * 堆
     * 堆是一种基于「完全二叉树」的数据结构，可使用数组实现。
     */
    @Test
    public void priorityQueue(){
        // 小顶堆
        Queue<Integer> heap = new PriorityQueue<>();
        // 大顶堆
        // Queue<Integer> heap = new PriorityQueue<>((x,y) -> (y-x));
        heap.add(1);
        heap.add(3);
        heap.add(2);
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
    }
}




