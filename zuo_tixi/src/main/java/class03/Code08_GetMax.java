package class03;

/**
 * 题目1
 * 求数组arr[L..R]中的最大值，怎么用递归方法实现。
 * 1）将[L..R]范围分成左右两半。左∶[L.Mid] 右[Mid+1..R]
 * 2）左部分求最大值，右部分求最大值
 * 3) [L..R]范围上的最大值，是max{左部分最大值，右部分最大值}
 * 注意：2）是个递归过程，当范围上只有一个数，就可以不用再递归了
 *
 *
 */

/**
 * ppt1
 * 递归？这东西是什么啊？
 * 怎么从思想上理解递归
 * 怎么从实际实现的角度出发理解递归
 *
 * ppt2
 * 递归的脑图和实际实现
 * 对于新手来说，把调用的过程画出结构图是必须的，这有利于分析递归
 * 递归并不是玄学，递归底层是利用系统栈来实现的
 * 任何递归函数都一定可以改成非递归
 *
 * ppt3
 * Master公式(可分析递归的时间复杂度)
 * 形如
 * T(N) = a * T(N/b) + O(N^d)(其中的a、b、d都是常数)
 * 的递归函数，可以直接通过Master公式来确定时间复杂度
 * 如果 log(b,a) < d,复杂度为O(N^d)
 * 如果 log(b,a) > d，复杂度为O(N^log(b,a))
 * 如果log(b,a)==d,复杂度为O(N^d * logN)   注:logN默认为log(2,N)
 */


/**
 * 笔记1：
 * 任何递归一定可以改为非递归。
 *
 * 笔记2:
 * 1)递归首先要确定合适结束，即base case,不需要再划分下去了。
 *
 * 笔记3：
 * 递归在系统中是入栈和出栈的行为过程，该过程可以被改写为迭代实现。
 *
 *
 */
public class Code08_GetMax {


	// 求arr中的最大值
	public static int getMax(int[] arr) {
		if (arr == null || arr.length == 0)
			throw new RuntimeException("arr为空");
		return process(arr, 0, arr.length - 1);
	}

	// arr[L..R]范围上求最大值  L ... R   N
	public static int process(int[] arr, int L, int R) {
		// arr[L..R]范围上只有一个数，直接返回，base case
		if (L == R) { 
			return arr[L];
		}
		// L...R 不只一个数
		// mid = (L + R) / 2
		int mid = L + ((R - L) >> 1); // 中点   	1
		int leftMax = process(arr, L, mid);
		int rightMax = process(arr, mid + 1, R);
		return Math.max(leftMax, rightMax);
	}

}
