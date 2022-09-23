package class04;

/**
 * 题目：
 * 小和问题  （递归和非递归的两种实现）
 * [1,3,2,9,9,5]中左边<右边的左数的和，即小和， (0)+(1)+(1)+(1+3+2)+(1+3+2)+(1+3+2) = 20
 *
 */

/**
 * 笔记1：
 * 小和问题，可以转化为左边<右边时的”左数大小*右数个数“的和。
 * [1,3,2,9,9,5] -> (1*5)+(3*3)+(2*3)+(9*0)+(9*0)+(5*0)= 20
 * 这个思路可以在归并排序的排序过程中得以统计实现，来降低复杂度为O(N*logN)
 * 注：merge中相等时不产生小和，但是要先拷贝右边===。
 */
public class Code02_SmallSum {

	// 非递归版
	public  static int smallSum_xj(int[] arr){
		if (arr == null|| arr.length == 0) return 0;
		int L,M,R;
		int N =arr.length,mergeSize=1;
		int ans=0;
		while(mergeSize < N){
			L = 0;
			while(mergeSize < N - L){
				M  = L + mergeSize - 1;
				R = Math.min(M+mergeSize,N-1);
				ans  += merge(arr,L,M,R);
				L = R + 1;
			}
			if (mergeSize > N/2)
				break;
			mergeSize <<= 1;
		}
		return ans;
	}



	public static int smallSum(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return process(arr, 0, arr.length - 1);
	}

	// arr[L..R]既要排好序，也要求小和返回
	// 所有merge时，产生的小和，累加
	// 左 排序   merge
	// 右 排序  merge
	// merge
	public static int process(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		// l < r
		int mid = l + ((r - l) >> 1);
		return 
				process(arr, l, mid) 
				+ 
				process(arr, mid + 1, r) 
				+ 
				merge(arr, l, mid, r);
	}

	public static int merge(int[] arr, int L, int m, int r) {
		int[] help = new int[r - L + 1];
		int i = 0;
		int p1 = L;
		int p2 = m + 1;
		int res = 0;
		while (p1 <= m && p2 <= r) {
			res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
		return res;
	}

	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				res += arr[j] < arr[i] ? arr[j] : 0;
			}
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (smallSum(arr1) != comparator(arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
