import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] signals) {        
        int[] sumList = new int[signals.length];
        
        for(int i = 0; i < signals.length; i++) {
            int sum = 0;
            for(int num : signals[i]) {
                sum += num;
            }
            sumList[i] = sum;
        }
        
        int signalsLcm = lcm(sumList);
        
        for(int t = 1; t <= signalsLcm; t++) {
            boolean yellow = true;
            
            for(int[] signal : signals) {
                int g = signal[0];
                int y = signal[1];
                int r = signal[2];
                
                int cycle = g + y + r;
                
                int pos = (t - 1) % cycle + 1;
                if(pos <= g || pos > g + y) {
                    yellow = false;
                    break;
                }
            }
            if(yellow == true) {
                return t;
            }
        }
        return -1;
    }
    
    public int gcd(int num1, int num2) {
        if(num1 % num2 == 0) {
            return num2;
        }
        return gcd(num2, num1 % num2);
    }
    
    public int lcm(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        
        int lcm = nums[0] * nums[1] / gcd(nums[0], nums[1]);
        
        for(int i = 2; i < nums.length; i++) {
            lcm = lcm * nums[i] / gcd(nums[i], lcm);
        }
        
        return lcm;
    }
}