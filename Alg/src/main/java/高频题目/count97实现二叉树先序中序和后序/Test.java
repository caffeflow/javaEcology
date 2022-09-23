package 高频题目.count97实现二叉树先序中序和后序;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author xj
 * @create 2022-09-16 7:55
 **/
public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

    }
}



class Solution{
    /**
     * 描述： 给定一棵二叉树，分别按照二叉树先序，中序和后序打印所有的节点。
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders (TreeNode root) {
        // write code here
        ArrayList<Integer> path0 = new ArrayList<>();
        ArrayList<Integer> path1 = new ArrayList<>();
        ArrayList<Integer> path2 = new ArrayList<>();
        orderDFS(root,path0,0);
        orderDFS(root,path1,1);
        orderDFS(root,path2,2);
        int[][] res = new int[3][path0.size()];
        for (int i = 0; i < path0.size(); i++) {
            res[0][i] = path0.get(i);
            res[1][i] = path1.get(i);
            res[2][i] = path2.get(i);
        }
        return res;
    }

    public void orderDFS(TreeNode root, List<Integer> path, int order){
        if (order!=0 && order!=1 && order != 2)
            throw new RuntimeException("order must in [0,1,2]");
        if (root == null) return;
        int curVal = root.val;
        if (order == 0)
            path.add(curVal);
        orderDFS(root.left,path,order);
        if (order == 1)
            path.add(curVal);
        orderDFS(root.right,path,order);
        if (order == 2)
            path.add(curVal);
    }

    static class TreeNode{
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }
}





