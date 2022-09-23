package class04;

/**
 * 题目：
 * 逆序对  (两种merge方式,、以及递归或迭代方式实现)
 *
 */

/**
 * 笔记1（重要）
 *  指针不回退，往往与单调性有关。
 *
 *  笔记2（个人结论）
 *  	归并排序的附加问题，思考单调性与题给要求的关系，比如p1和p2在L<=p1<=M<p1<=R上,逆序对即arr[p1]>arr[p2],
 *  	可以这样思考：
 *  	1. 要么merge中插入arr[p1]，并让[M+1,p2]区间的元素统计为逆序对个数。  此时需要反向遍历
 *  	2. 要么Merge中插入arr[p2]，并让[p1,M]区间的元素统计为逆序对个数。  此时需要正向遍历
 */
public class Code03_ReversePair {

	public static int reverPairNumber(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return process(arr, 0, arr.length - 1);
	}

	// arr[L..R]既要排好序，也要求逆序对数量返回
	// 所有merge时，产生的逆序对数量，累加，返回
	// 左 排序 merge并产生逆序对数量
	// 右 排序 merge并产生逆序对数量
	public static int process(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		// l < r
		int mid = l + ((r - l) >> 1);
		return process(arr, l, mid) + process(arr, mid + 1, r) + merge_xj2(arr, l, mid, r);
	}

	// 迭代版
	public static int reverPairNumber_iter(int[] arr){
		if (arr == null || arr.length < 2){
			return 0;
		}
		// [l,m] vs [m+1,r]
		int mergeSize = 1;
		int len = arr.length;
		int l,m,r;
		int ans=0;
		while (mergeSize <= len){
			l = 0;
			while (l < len){
				m = l + mergeSize - 1;
				if (m >= len-1)
					break; // 因为此时右边组没有元素,不需要进行merge，同时也没有逆序对产生。
				r = Math.min(l+2*mergeSize-1,len-1);
				ans += merge_xj(arr,l,m,r);
				l = r+1;
			}
			if (mergeSize > len / 2) break;
			mergeSize <<= 1;
		}
		return ans;
	}

	// Merge中插入arr[p2]，并让[p1,M]区间的元素统计为逆序对个数。   所以此时需要正向遍历
	public static int merge_xj(int[] arr,int L,int M,int R){
		if (arr==null || arr.length < 2) return 0;
		int[] help = new int[R-L+1];
		int i = 0;
		int p1=L,p2=M+1;
		int ans=0;
		while (p1<=M && p2 <= R){
			if (arr[p1]>arr[p2]){
				ans += M-p1+1;
				help[i++] = arr[p2++];
			}else{
				help[i++] = arr[p1++];
			}
		}
		while(p1<=M){
			help[i++] = arr[p1++];
		}
		while(p2<=R){
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[L+i] = help[i];
		}
		return ans;
	}

	// int p1=m,p2=r,i=help.length-1;
	// merge中插入arr[p1]，并让[M+1,p2]区间的元素统计为逆序对个数。   所以此时需要反向遍历
	public static int merge_xj2(int[] arr,int L,int M,int R){
		if (arr==null || arr.length < 2) return 0;
		int[] help = new int[R - L + 1];
		int i=help.length-1;
		int p1=M,p2=R;
		int ans = 0;
		while (p1>=L && p2 >=M+1){
			if (arr[p1]>arr[p2]){
				ans += p2-M;
				help[i--] = arr[p1--];
			}else{
				help[i--] = arr[p2--];
			}
		}
		while (p1>=L){
			help[i--] = arr[p1--];
		}
		while (p2>=M+1){
			help[i--] = arr[p2--];
		}
		//
		int cur = 0;
		while(cur < help.length){
			arr[L+cur] = help[cur];
			cur++;
		}
		return ans;
	}

	public static int merge(int[] arr, int L, int m, int r) {
		int[] help = new int[r - L + 1];
		int i = help.length - 1;
		int p1 = m;
		int p2 = r;
		int res = 0;
		while (p1 >= L && p2 > m) {
			res += arr[p1] > arr[p2] ? (p2 - m) : 0;
			help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
		}
		while (p1 >= L) {
			help[i--] = arr[p1--];
		}
		while (p2 > m) {
			help[i--] = arr[p2--];
		}
		for (i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
		return res;
	}

	// for test
	public static int comparator(int[] arr) {
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					ans++;
				}
			}
		}
		return ans;
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
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (reverPairNumber(arr1) != comparator(arr2)) {
				System.out.println("Oops!");
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
