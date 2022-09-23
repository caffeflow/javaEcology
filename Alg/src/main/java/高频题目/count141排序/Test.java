package 高频题目.count141排序;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author xj
 * @create 2022-09-14 13:18
 *
 * 描述
 * 给定一个长度为 n 的数组，请你编写一个函数，返回该数组按升序排序后的结果。
 *
 * 数据范围： 0≤n≤1×1030 \le n \le 1\times10^30≤n≤1×103，数组中每个元素都满足 0≤val≤1090 \le val \le 10^90≤val≤109
 * 要求：时间复杂度 O(n2)O(n^2)O(n2)，空间复杂度 O(n)O(n)O(n)
 * 进阶：时间复杂度 O(nlogn)O(nlogn)O(nlogn)，空间复杂度 O(n)O(n)O(n)
 *
 * 注：本题数据范围允许绝大部分排序算法，请尝试多种排序算法的实现。
 **/

public class Test{
    public static void main(String[] args) {
        int[] res = new Solution3().MySort(new int[]{4,2,16,2});
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
class Solution1 {
    /**
     * 归并排序
     * ---- 使用Comparator自定义比较器作为参数
     * @param arr
     * @return
     */
    public int[] MySort(int[] arr){
        mergeSort(arr,0,arr.length-1);
        return arr;
    }

    public void mergeSort(int[] arr,int l,int r){
        if(l == r) return;
        int mid = l + ((r-l) >>1);
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r,(x,y)->(y-x));
    }
    public void merge(int[] arr, int l, int mid, int r, Comparator<Integer> comparator){
        int[] tmpArr = new int[r-l+1];
        int lp = l,rp = mid+1,tmpp = 0;
        while (lp <= mid && rp <= r){
            tmpArr[tmpp++] = comparator.compare(arr[lp],arr[rp]) >= 0 ? arr[lp++] :  arr[rp++];
        }
        while(lp <= mid){
            tmpArr[tmpp++] = arr[lp++];
        }
        while(rp <= r){
            tmpArr[tmpp++] = arr[rp++];
        }
        for (int i = 0; i < tmpArr.length; i++) {
            arr[l+i] = tmpArr[i];
        }
    }
}

class Solution2{
    /**
     * 堆排序  --- 数组构造完全二叉树 --- arr --- 下标i从0开始,左孩子下标2*i+1,右孩子小标2*i+2 --- (i-1)//2为父节点(i=0是根结点)
     */
    public int[] MySort2(int[] arr){
        heapSort(arr,(x, y)->{return y - x;});
        return arr;
    }
    public void heapSort(int[] arr,Comparator<Integer> comparator){
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length; i++) {
            tryUp(arr,i,comparator); // 构建堆
        }
        // 堆 --> 排序 (不断删除堆顶与重构堆的过程 ---> 一个简单方法是堆顶与末尾元素交换,size--,重构堆)
        int size = arr.length;  // 注意 堆顶下标idx = 0, 末尾元素下标tailIdx = size - 1
        while(size > 1){
            int topIdx = 0,tailIdx = size - 1;
            swap(arr,topIdx,tailIdx);
            tryDown(arr,--size,topIdx,comparator);
        }
    }
    public void tryUp(int[] arr,int idx,Comparator<Integer> comparator){ // 从idx到0,从下向上建堆
        int fatherIdx = (idx -1) / 2;  // father >= 0
        while(comparator.compare(arr[fatherIdx],arr[idx]) < 0){
            swap(arr,fatherIdx,idx);
            idx = fatherIdx;
        }
    }

    public void tryDown(int[] arr,int size,int idx,Comparator<Integer> comparator){ // 从idx到size-1, 从上向下建堆

        int leftIdx = 2 * idx + 1;
        while(leftIdx < size){
            int rightIdx = leftIdx + 1;
            int swapIdx = (rightIdx >= size) || comparator.compare(arr[leftIdx],arr[rightIdx]) > 0 ? leftIdx : rightIdx;
            if (comparator.compare(arr[idx],arr[swapIdx]) >= 0)
                break;
            swap(arr,idx,swapIdx);
            idx = swapIdx;
            leftIdx = 2 * idx + 1;
        }

    }
    public void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}


class Solution3{
    /**
     * java-优先队列 --- 实现了堆
     */
    public int[] MySort(int[] arr){
        prioritySort(arr,(x,y) -> {return y - x;});
        return arr;
    }

    public void prioritySort(int[] arr,Comparator<Integer> comparator){
        if(arr == null || arr.length < 2) return;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(comparator);
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = priorityQueue.remove();
        }
    }

}
