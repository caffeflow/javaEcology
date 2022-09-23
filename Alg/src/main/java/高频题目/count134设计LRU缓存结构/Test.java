package 高频题目.count134设计LRU缓存结构;

import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author xj
 * @create 2022-09-15 10:54
 **/
public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution(2);
        solution.set(1,1);
        solution.set(2,2);
        System.out.println(solution.get(1));
        solution.set(3,3);
        System.out.println(solution.get(2));
        solution.set(4,4);
        System.out.printf("", solution.get(1));
        System.out.println(solution.get(3));
        System.out.println(solution.get(4));
    }
}

class Solution{
    int capacity;
    Linked linked;
    Map<Integer,Node> dic;
    public Solution(int capacity){
        this.capacity = capacity;
        this.linked = new Linked();
        this.dic = new HashMap(); // 通过key,快速判断链表中否存在目标结点
    }

    public int get(int key){
        if (dic.containsKey(key)){
            Node usedNode = dic.get(key);
            this.linked.remove(usedNode);
            this.linked.insertFirst(usedNode);
            return usedNode.value;
        }else{
            return -1;  // key不存在
        }
    }

    public void set(int key, int value){
        int _key = key,_value = value;
        if (dic.containsKey(key)){ // set -> update
            Node usedNode = dic.get(key);
            usedNode.value = value;
            this.linked.remove(usedNode);
            this.linked.insertFirst(usedNode);
        }else{
            if (this.capacity > this.linked.size()){ // set --> add
                Node usedNode = new Node(){{this.key = _key;this.value = _value;}};
                this.linked.insertFirst(usedNode);
                this.dic.put(key,usedNode);
            }else{  // set ---> {remove --> add}
                Node oldestNode = this.linked.getFakeTail().pre;
                Node usedNode = new Node(){{this.key = _key;this.value = _value;}};
                this.linked.remove(oldestNode);
                this.linked.insertFirst(usedNode);
                this.dic.remove(oldestNode.key);
                this.dic.put(key,usedNode);
            }
        }
    }


    static class Node{
        int key;
        int value;
        Node pre;
        Node next;
    }

    static class Linked{
        private Node fakeHead;
        private Node fakeTail;
        private int size;
        public Linked(){
            fakeHead = new Node();
            fakeTail = new Node();
            fakeHead.next = fakeTail;
            fakeTail.pre = fakeHead;
            size = 0;
        }
        Node getFakeHead(){return fakeHead;}
        Node getFakeTail(){return fakeTail;}
        int size(){return size;}
        void remove(Node node){
            Node preNode = node.pre,nextNode = node.next;
            preNode.next = nextNode;
            nextNode.pre = preNode;
            node.pre = null;
            node.next = null;
            size--;
        }
        void insertFirst(Node node){
            Node preNode = this.fakeHead;
            Node nextNode = this.fakeHead.next;
            preNode.next = node;
            node.pre = preNode;
            nextNode.pre = node;
            node.next = nextNode;
            size++;
        }
    }
}