package 蚂蚁;

import java.util.Scanner;

/**
 * @author xj
 * @create 2022-09-22 9:47
 **/
public class T2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[][] arr = new int[T][2];
        for (int i = 0; i < T; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

    }

}
