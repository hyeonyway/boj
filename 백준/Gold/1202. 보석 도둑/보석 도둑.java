import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] bags = new int[K];
        int[][] jewels = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i][0] = Integer.parseInt(st.nextToken()); // 보석의 무게
            jewels[i][1] = Integer.parseInt(st.nextToken()); // 보석의 가격
        }

        for(int i = 0; i < K; i++) {
            int c = Integer.parseInt(br.readLine());
            bags[i] = c; // 가방에 담을 수 있는 무게
        }

        // 가장 작은 보석부터 시작
        Arrays.sort(bags);
        Arrays.sort(jewels, (a, b) -> a[0] - b[0]); // 낮은 무게 순서로 정렬

        long result = 0;
        int idx = 0;
        PriorityQueue<int[]> valuePQ = new PriorityQueue<>((a, b) -> b[1] - a[1]); // 비싼 애 먼저 나오게
        for(int i = 0; i < K; i++) {
            while(idx < N && jewels[idx][0] <= bags[i]) {
                valuePQ.add(jewels[idx++]);
            }

            if(!valuePQ.isEmpty()){
                result += valuePQ.poll()[1];
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
