package autils;


/**
 * @author xj
 * @create 2022-02-07-20:40
 */


public class GenerateLinkedList {




    public static int getRandomValue(int maxValue){
        return (int)(Math.random() * (maxValue + 1));
    }


    // for test
    public static Node generateRandomLinkedList(int maxLen, int maxValue) {
        int size = getRandomValue(maxLen);
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node(getRandomValue(maxValue));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node(getRandomValue(maxValue));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }



    // for test
    public static DoubleNode generateRandomDoubleList(int maxLen, int maxValue) {
        int size = getRandomValue(maxLen);
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode(getRandomValue(maxValue));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode(getRandomValue(maxValue));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
            size--;
        }
        return head;
    }
}

