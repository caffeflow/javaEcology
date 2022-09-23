import java.util.Scanner;

/**
 * @author xj
 * @create 2022-03-14 19:40
 **/
public class Ttwo {
    public static void main(String[] args) {

    }
}


class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String input = in.next();
            System.out.println(input);
            int num = (int)Integer.parseInt(input.substring(2),16);
            System.out.println(num);
            int res = 0;
            while (num > 0){
                num = num & (num-1);
                res++;
            }

            System.out.println(res);
        }

    }

}

