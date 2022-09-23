package class05;

import class37.Compare;
import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.*;

/**
 * @author xj
 * @create 2022-03-08 20:12
 **/
public class Test {
    // 对数组 按照某目标值t，划分为<t  =t  >t三部分数组
    public static void main(String[] args) {
        System.out.println("输入第一行：列表长度、L、R 输入第二行：数组元素,如下 \n5 0 4 \n11 3 9 2 4" );
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int L = sc.nextInt();
            int R = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = sc.nextInt();
            }

            int[] ints = netherlandsFlag(arr, L, R);
            print(arr,L,R,ints[0],ints[1]);
        }


    }


    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
//  <arr[R]  ==arr[R]  > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1; // < 区 右边界
        int more = R;     // > 区 左边界
        int index = L;
        while (index < more) {
            // 维护 [L,less]小于区间 和 [more,R-1]大于区间
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) { //arr[index]交换为小于区间的右边界
                swap(arr, index++, ++less);
            } else { // >    arr[index]交换为大于区间的左边界
                swap(arr, index, --more);
            }
        }
        // L...less   less+1...more-1    more...R-1        R
        // L...less   less+1.............more  more+1...   R
        swap(arr, more, R);  // 交换目标数和大于区间的左闭边界
        return new int[] { less + 1, more };  // 返回 等于区间的左右闭区间
    }




    public static void print(int[] arr,int L,int R,int l,int r){
        for (int i = L; i < l; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for (int i = l; i <= r; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for (int i = r+1; i <= R; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
