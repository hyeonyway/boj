import java.io.*;
import java.util.*;

public class Main {
    static int N, L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        for (int len = L; len <= 100; len++) {
            int temp = N - (len * (len - 1)) / 2;

            if (temp < 0)
                break;

            if (temp % len == 0) {
                int x = temp / len;

                for (int i = 0; i < len; i++) {
                    System.out.print((x + i) + " ");
                }
                return;
            }
        }

        System.out.println(-1);
    }
}
