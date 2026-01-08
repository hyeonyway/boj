import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> list;
    static int[] indegree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        indegree = new int[N+1];
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());
            indegree[back]++;
            list.get(front).add(back);
        }

        topologicalSort();

    }
    public static void topologicalSort() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur + " ");
            for(int i: list.get(cur)) {
                indegree[i]--;
                if(indegree[i] == 0) {
                    q.add(i);
                }
            }
        }
    }
}
