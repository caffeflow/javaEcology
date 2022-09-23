package other.acmInput;

/**
 * @author xj
 * @create 2022-09-13 21:36
 **/

import java.util.Scanner;

/**
 *
 * 输入例子:
 * 7 15
 * 15 5 3 7 9 14 0
 *
 *
 * 输出例子:
 * 2.50
 */
public class ArrayInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            long l = scanner.nextLong();
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextLong();
            }
            // 处理逻辑
            System.out.println("处理逻辑");
        }
    }
}
