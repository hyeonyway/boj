import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static ArrayList<ArrayList<Integer>> list;
    static int[] dist;
    static int[] indegree;
    static int[] result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 건물의 개수 (1~N)
            K = Integer.parseInt(st.nextToken()); // 건설 순서 규칙의 총 개수
            list = new ArrayList<>();
            indegree = new int[N + 1];
            dist = new int[N + 1];
            result = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                dist[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i <= N; i++) {
                list.add(new ArrayList<>());
            }


            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                indegree[Y]++;
                list.get(X).add(Y);
            }

            int W = Integer.parseInt(br.readLine());

            topologicalSort();
            bw.write(result[W] + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void topologicalSort() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            result[i] = dist[i];
            if(indegree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int i: list.get(cur)) {
                result[i] = Math.max(result[i], result[cur] + dist[i]);
                indegree[i]--;
                if(indegree[i] == 0) {
                    q.add(i);
                }
            }
        }
    }
}
