package 高频题目.count196反转链表;

/**
 * @author xj
 * @create 2022-09-14 12:50
 **/
public class Solution {
    public ListNode ReverseList(ListNode head){
        if (head == null || head.next== null) return head;
        ListNode p = head.next;
        ListNode reverse = ReverseList(p);
        p.next = head;
        head.next = null;  // 别忘了
        return reverse;
    }
}

/**
 *
 * 其他思路：
 * p1,p2,p3三个指针, p1和p2用于反转两个相邻的结点,这个过程会将链表拆为已反转和未反转的两段,p3用于保存为反转的段。
 */


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}