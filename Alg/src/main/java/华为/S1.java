package 华为;

import java.util.*;

/**
 * @author xj
 * @create 2022-09-14 19:04
 **/
public class S1 {
    /**
     * 超级玛丽过桥--桥长N,每次走1或2步,陷阱K个,血量M,一个陷阱一点血量,问多少种办法过桥。
     * 输入案例
     * 2 2 1
     * 2
     * 输出
     * 4
     * @param args
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt(); // 血量
        int N = scanner.nextInt(); // 桥长
        int K = scanner.nextInt(); // 陷阱数
        int[] L = new int[N+10]; // 陷阱分布
//        String[] split = scanner.nextLine().split(" ");
        for (int i = 0; i < K; i++) {
            L[scanner.nextInt()] = 1;
        }
        int ans = 0;
        int[][] dp = new int[N+10][N+10];
        dp[0][M] = 1;
        for (int i = 1; i <= N+1; i++) {
            for (int j = 1; j <= M; j++) {
                if (L[i] != 1){
                    if(i >= 3)
                        dp[i][j]+= dp[i-3][j];
                    if (i >=2)
                        dp[i][j] += dp[i-2][j];
                    if (i >= 1)
                        dp[i][j] += dp[i-1][j];
                }else{
                    if(j == 1) continue;
                    if(i >= 3)
                        dp[i][j-1]+= dp[i-3][j];
                    if (i >=2)
                        dp[i][j-1] += dp[i-2][j];
                    if (i >= 1)
                        dp[i][j-1] += dp[i-1][j];
                }
            }
            if (i == N + 1){
                for (int j = 1;  j<= M; j++) {
                    ans += dp[i][j];
                }
            }
        }
        System.out.println(ans);
    }
}
