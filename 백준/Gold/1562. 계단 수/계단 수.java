import java.io.*;
import java.util.*;

public class Main {
    static int MOD = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        // dp의 i, j, k
        // i : 문자열의 길이
        // j : 마지막으로 밟는 숫자 ex j == 4 면 1012345678987654 처럼 4로 끝나는 숫자인 것
        // k : 이전까지 방문한 집합 -> i-1까지 방문한 숫자들 >> 얘가 1023이면 모든 숫자를 사용했다는 뜻
        long[][][] dp = new long[N + 1][11][1<<10];


        // 맨 앞자리 수 지정
        // 길이가 1이므로 하나만 있어도 계단수로 생각
        // i < 10 즉 최대값이 9 이므로 512까지의 경우의 수만 보기 때문에 길이가 10 이하여도 1023에선 0개로 나온다.
        // 0은 시작할 수 없으니까 시작점으로 포함하지 않고 시작
        for(int i = 1; i < 10; i++) {
            dp[1][i][1<<i] = 1;
        }

        for(int i = 2; i <= N; i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k < 1<<10; k++) {
                    int bit = k | 1 << j; // bit(k)에 지금 밟는 숫자 j 추가
                    // i - 1 은 이전 길이 기준으로 켜진 비트 확인용
                    if(j == 0) {
                        // j == 0 이면 계단 수는 1 밖에 없으므로 0에서만 누적
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k]) % MOD;
                    } else if (j == 9) {
                        // j == 9 면 계단 수는 8 밖에 없으므로 8에서만 누적
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j - 1][k]) % MOD;
                    } else
                        // 1 ~ 8 은 j - 1, j + 1 (앞뒤) 확인해서 누적
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % MOD;
                }
            }
        }

        long sum = 0;
        for(int i = 0; i < 10; i++) {
            sum = (sum + dp[N][i][1023]) % MOD;
        }
        bw.write(sum+"\n");
        bw.flush();
        bw.close();
    }
}
