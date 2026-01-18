import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        long[][] arr = new long[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
            }
        }

        long AB[] = new long[N * N];
        long CD[] = new long[N * N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = arr[i][0] + arr[j][1];
                CD[idx] = arr[i][2] + arr[j][3];
                idx++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);
        long result = 0;
        int left = 0;
        int right = CD.length - 1;
        while (left < AB.length && right >= 0) {
            long sum = AB[left] + CD[right];
            if (sum == 0) {
                long countA = 0;
                long countB = 0;
                long valA = AB[left];
                long valB = CD[right];

                while (left < AB.length && AB[left] == valA) {
                    countA++;
                    left++;
                }
                while (right >= 0 && CD[right] == valB) {
                    countB++;
                    right--;
                }
                result += countA * countB;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
