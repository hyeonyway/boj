import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 55;
    static long[] sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        sum = new long[MAX];
        sum[0] = 1;
        for(int i = 1; i < MAX; i++) {
            sum[i] = (sum[i - 1] << 1) + (1L << i);
        }

        long result = getOne(B) - getOne(A-1);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
    public static long getOne(long num) {
        long count = num & 1;
        int size = (int) (Math.log(num) / Math.log(2));
        for(int i = size; i > 0; i--) {
            if((num & (1L<<i)) != 0L) {
                count += sum[i-1] + num - (1L << i) + 1;
                num -= (1L << i);
            }
        }
        return count;
    }
}

/*
1 : 1
--- 1 -> 1 d[0]
2 : 10
3 : 11
--- 1 + 3 = 4 d[1]
4 : 100
5 : 101
6 : 110
7 : 111
--- 4 + 8 = 12 d[2]
8 : 1000
9 : 1001
10 : 1010
11 : 1011
12 : 1100
13 : 1101
14 : 1110
15 : 1111
--- 12 + 20 = 32 d[3]
*/

// d[n] = d[n-1] * 2 + 2^n