package other.acmInput;

/**
 * @author xj
 * @create 2022-09-13 21:18
 **/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

/**
 * 输入：
 * 5 10 9
 * 0 5
 * 9 1
 * 8 1
 * 0 1
 * 9 100
 * 输出：
 * 43
 *
 */
public class MultilineInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            int full = scanner.nextInt();
            int avg = scanner.nextInt();
            int[][] nums = new int[n][2];
            for (int i = 0; i < n; i++) {
                nums[i][0] = scanner.nextInt();
                nums[i][1] = scanner.nextInt();
            }
            // 处理逻辑
            System.out.println("处理逻辑");
        }
    }
}
