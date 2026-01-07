import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int[] list1, list2;
    static long[] sum1, sum2;
    static long count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine()); // 목표값

        int n = Integer.parseInt(br.readLine());
        list1 = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list1[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        list2 = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            list2[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            list1[i] += list1[i - 1];
        }

        for (int i = 1; i < m; i++) {
            list2[i] += list2[i - 1];
        }

        sum1 = new long[n * (n + 1) / 2];
        sum2 = new long[m * (m + 1) / 2];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = list1[j];
                if (i > 0) {
                    temp -= list1[i - 1];
                }
                sum1[idx++] = temp;
            }
        }

        idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int temp = list2[j];
                if (i > 0) {
                    temp -= list2[i - 1];
                }
                sum2[idx++] = temp;
            }
        }

        Arrays.sort(sum1);
        Arrays.sort(sum2);

        int left = 0, right = sum2.length - 1;
        while (left < sum1.length && right >= 0) {
            long lsum = sum1[left];
            long rsum = sum2[right];
            long sum = lsum + rsum;

            if (sum == T) {
                long lcount = 0, rcount = 0;
                while (left < sum1.length && lsum == sum1[left]) {
                    lcount++;
                    left++;
                }
                while (right >= 0 && rsum == sum2[right]) {
                    rcount++;
                    right--;
                }
                count += lcount * rcount;
            }
            if (sum > T) {
                right--;
            } else if (sum < T) {
                left++;
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}
