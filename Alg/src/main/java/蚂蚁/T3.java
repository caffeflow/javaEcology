package 蚂蚁;

import java.util.Scanner;

/**
 * @author xj
 * @create 2022-09-22 9:47
 **/
public class T3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(subStrNum(str));
    }

    public static int subStrNum(String s){
        if (s == null || s.length() == 0) return 0;
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num += (check(s,i,i) + check(s,i,i+1));
        }
        return num;
    }
    public static int check(String s,int i,int j){
        int countYes = 0;
        int countAll = 0;
        for(;i >= 0 && j < s.length();){
            countAll++;
            i--;j++;
        }
        for(;i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j);){
            countYes++;
            i--;j++;
        }
        return countAll - countYes;
    }
}
