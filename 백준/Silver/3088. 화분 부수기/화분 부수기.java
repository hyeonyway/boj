import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        boolean[] broken = new boolean[10000001];
        int count = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            boolean isBroken = false;
            for (int j = 0; j < 3; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (broken[num]) {
                    isBroken = true;
                }
                broken[num] = true;
            }
            if (!isBroken) {
                count++;
            }
        }
        bw.write(count + "\n");
        bw.flush();
    }
}
