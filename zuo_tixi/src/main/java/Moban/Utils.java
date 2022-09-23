package Moban;

/**
 * @author xj
 * @create 2022-03-29 12:48
 **/
public class Utils {
    public static void swap(int[] arr,int i,int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
