package other.acmInput;

/**
 * @author xj
 * @create 2022-09-14 7:07
 **/

import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *         //输入描述:
 *         //第一行两个数n,root，分别表示二叉树有n个节点，第root个节点时二叉树的根
 *         //接下来共n行，第i行三个数val_i,left_i,right_i，
 *         //分别表示第i个节点的值val是val_i,左儿子left是第left_i个节点，右儿子right是第right_i个节点。
 *         //节点0表示空。
 *         //1<=n<=100000,保证是合法的二叉树
 *         //输入
 *         //5 1
 *         //5 2 3
 *         //1 0 0
 *         //3 4 5
 *         //4 0 0
 *         //6 0 0
 */
public class TreeInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            int root = scanner.nextInt();
            TreeNode[] nodes = new TreeNode[n];
            TreeNode rootNode = nodes[root];
            int i = 0;
            scanner.nextLine();
            while (i < n){
                List<Integer> line = Arrays.stream(scanner.nextLine().split(" ")).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
                for(int e: line){
                    System.out.print(e + " ");
                }
                System.out.println();
                i++;
            }
            // 处理逻辑
            System.out.println("处理逻辑");
        }
    }

    class TreeNode{
        TreeNode left,right;
        int val;
        public TreeNode(){};
        public TreeNode(int val){this();this.val = val;};
    }
}



