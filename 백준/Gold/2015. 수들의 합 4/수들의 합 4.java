import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] sums = new int[N + 1];
        HashMap<Integer, Integer> countMap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sums[i] = sums[i - 1] + Integer.parseInt(st.nextToken());
        }

        long result = 0;
        countMap.put(0, 1); // 초기값 설정

        for (int i = 1; i <= N; i++) {
            int target = sums[i] - K;
            if (countMap.containsKey(target)) {
                result += countMap.get(target);
            }
            countMap.put(sums[i], countMap.getOrDefault(sums[i], 0) + 1);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
