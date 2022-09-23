package other.acmInput;

/**
 * @author xj
 * @create 2022-09-14 6:44
 **/

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *         //输入是一串数字，请将其转换成单链表格式之后，再进行操作
 *         //输入描述:
 *         //一串数字，用逗号分隔
 *         //输入
 *         //1,2,3,4,5
 *
 */
public class LinkListInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String[] split = scanner.next().toString().split(",");
            List<Integer> nums = Arrays.stream(split).map(x -> Integer.valueOf(x)).collect(Collectors.toList());
            LinkedNode fakeHead = new LinkedNode();
            LinkedNode p = fakeHead;
            for (int i = 0; i < nums.size(); i++) {
                p.next = new LinkedNode(nums.get(i));
                p = p.next;
            }
            // 处理逻辑
            System.out.println("处理逻辑");
        }
    }
}

class LinkedNode{
    Integer val;
    LinkedNode next;
    public LinkedNode(){};
    public LinkedNode(int val){this();this.val = val;}
}