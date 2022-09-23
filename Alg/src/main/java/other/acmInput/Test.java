package other.acmInput;

import java.util.Scanner;

/**
 * @author xj
 * @create 2022-09-13 21:45
 **/
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.next();
//            String s = scanner.nextLine();
            System.out.print(s + ":" +s.length() + " ");
        }
    }
}
