package Test.meituan;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author xj
 * @create 2022-03-19 9:27
 **/
public class Main {
//     int rightOne = eor & (-eor); // 提取出最右的1   // eor & ((~eor) + 1) = eor & (-eor)  取反加一等于相反数。
    public static void main(String[] args) {
        int eor = 3;
        System.out.println(eor & (~eor + 1));
        System.out.println(eor & (-eor));
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String s = sc.next();
    double t = sc.nextDouble();
    String s2 = sc.nextLine();

    }




}


class Main1{
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()){
                // line 1
                int n = sc.nextInt();
                // line 2
                int[] sales = new int[n];
                int i=0;
                while (i < n) {
                    sales[i++] = sc.nextInt();
                }
                // line 3
                int[] zks = new int[n];
                i = 0;
                while (i < n){
                    zks[i++] = sc.nextInt();
                }
                // line 4
                int m = sc.nextInt();
                // line 5
                i =0 ;
                // 满c减d
                int[] mjcs = new int[m];
                while (i < m){
                    mjcs[i++] = sc.nextInt();
                }
                // line 6
                i =0 ;
                int[] mjds = new int[m];
                while (i < m){
                    mjds[i++] = sc.nextInt();
                }

                if (n < 1){
                    System.out.println("");
                    return;
                }

                int sum_sale = 0;
                int sum_zk = 0;
                int idx_mj = 0;
                String res = "";
                for (i=0; i < n; i++){
                    sum_sale += sales[i];
                    sum_zk += zks[i];
                    while (idx_mj < m-1 && mjcs[idx_mj] < sum_sale && mjcs[idx_mj+1] <= sum_sale){
                        idx_mj++;
                    }

                    if (sum_sale - mjds[idx_mj] < sum_zk){
                        res += "M";
                    }else if (sum_sale - mjds[idx_mj] > sum_zk){
                        res += "Z";
                    }else {
                        res += "B";
                    }
                }
                System.out.println(res);
            }
    }
}



class Main2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            // line 1
            int n = 0,t = 0;
            n = sc.nextInt();
            t = sc.nextInt();
            // line 2
            String s = sc.next();
            if (t == 1){
                jia(s);
            }else{
                jie(s);
            }
        }

    }

    public static void jia(String s){
        if (s==null || s.length()==0){
            System.out.println("");
            return;
        }
        String t= "";
        LinkedList<Character> arr = new LinkedList<Character>();
        for (char c : s.toCharArray()){
            arr.add(c);
        }
        while (!arr.isEmpty()){
            t += arr.remove((int)Math.ceil(arr.size()/2));
        }
        System.out.println(t);
    }

    public static void jie(String s){
        if (s==null || s.length()==0){
            System.out.println("");
            return;
        }
        String t ="";
        char[] tmp = new char[s.length()];
        int size = 0;
        char[] arrS = s.toCharArray();
        for (int i = arrS.length-1;i>=0;i--){
            tmp[(int)Math.ceil(size/2)] = arrS[i];
            size++;
        }
        for (char c : tmp){
            t +=c;
        }
        System.out.println(t);
    }
}



class Main4{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // line 1
            int n = 0, k1 = 0, k2 = 0;
            n = sc.nextInt();
            k1 = sc.nextInt();
            k2 = sc.nextInt();
            // line 2
            int[] arr = new int[n];
            int i = 0;
            while (i < n) {
                arr[i++] = sc.nextInt();
            }
            //
            int count =0;
            int max = 0;
            for (i=0;i<arr.length;i++){
                int sum =0;
                for (int j=i;j<arr.length;j++){
                    sum+=arr[j];
                    if (sum % k1 == 0 && sum % k2 !=0){
                        count++;
                        max = max >= sum ? max : sum;
                        count %= 998244353;
                    }
                }
            }

            System.out.println(max + " " +count);

        }
    }
}


class Main3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){

            System.out.println(4);
        }
    }
}


class Main5{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n=0,k=0;
            n = sc.nextInt();
            k = sc.nextInt();

            LinkedList<Integer> arr = new LinkedList<>();
            int i=0;
            while (i < n){
                int size = arr.size();
                int[] L = new int[size];
                int[] R = new int[size];
                for (int j =0;j < size;j++){
                    L[j] = 1 + arr.get(j);
                    R[j] = 2 + arr.get(j);
                }
                for (int j =size-1; j >=0;j--){
                    arr.addFirst(L[j]);
                }
                for (int j=0; j < size;j++){
                    arr.addLast(R[j]);
                }
                i++;
            }

            System.out.println(arr.get(k));
        }
    }
}