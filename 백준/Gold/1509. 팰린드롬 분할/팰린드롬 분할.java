// 2026-01-26
// https://www.acmicpc.net/problem/1509
// 팰린드롬 분할
// dp
// 1) manacher's 알고리즘으로 팰린드롬 수 모두 구하기
// 2) dp로 중앙에서부터 팰린드롬 수 중앙 넘어가면 + 1

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        char[] temp = str.toCharArray();
        int len = temp.length;
        char[] chars = new char[len * 2 + 1];
        int[] arr = new int[chars.length];

        for(int i = 0; i < len; i++) {
            chars[2*i] = '#';
            chars[2*i+1] = temp[i];
        }
        chars[chars.length-1] = '#';

        int R = 0;
        int p = 0;
        for(int i = 0; i < arr.length; i++){
            if (i <= R) {
                arr[i] = Math.min(arr[2 * p - i], R - i);
            } else {
                arr[i] = 0;
            }
            while(i - arr[i] - 1 >= 0 && i + arr[i] + 1 < arr.length && chars[i-arr[i]-1] == chars[i+arr[i]+1]) {
                arr[i] += 1;
            }
            if(i + arr[i] > R) {
                R = i+arr[i];
                p = i;
            }
        }

        int[] dp = new int[len+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int c = 0; c < chars.length; c++){
            int r = arr[c];
            for(int d = 0; d <= r; d++) {
                int left = c - d;
                int right = c + d;

                if(left % 2 == 0 && right % 2 == 0) continue;

                int nLeft = left / 2;
                int nRight = right / 2;
                dp[nRight + 1] = Math.min(dp[nRight + 1], dp[nLeft] + 1);
            }
        }
        bw.write(dp[len] + "\n");
        bw.flush();
        bw.close();
    }
}