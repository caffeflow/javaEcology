import java.util.ArrayList;

/**
 * @author xj
 * @create 2022-03-12 16:46
 **/
public class meituan {

    public static void main(String[] args) {


    }


    public boolean run1(int num){
        if (num % 11 == 0){
            int count = 0;
            while (num>0){
                count += num % 10 == 1 ? 1 : 0;
                num = num / 10;
            }
            return count >= 2 ? true : false;
        }
        return false;
    }



}


class T2{
    public int subArray(int[] nums) {
        // 思路：动态规划。
        if (nums.length == 1 && nums[0] < 0){ return 0;}
        int[] dp = new int[nums.length];
        int[] state = new int[nums.length];
        dp[0] = 1;
        state[0] = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i-1]){
                state[i] = state[i-1] * nums[i];
                dp[i] = dp[i-1] + (state[i] > 0 ? 1 : 0);
            }else{
                state[i] = nums[i];
            }

        }
        return dp[dp.length-1];
    }

}


class T3{

}

