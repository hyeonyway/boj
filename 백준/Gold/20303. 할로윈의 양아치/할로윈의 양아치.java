import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] parent;
    static int[] children;
    static ArrayList<Integer>[] friends;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // K를 넘지 않게 울려야 함.
        st = new StringTokenizer(br.readLine());

        children = new int[N+1];
        parent = new int[N+1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for(int i = 1; i <= N; i++) children[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        // 1) 부모 노드 전부 담고 인원수, 캔디수 기록
        Set<Integer> parentSet = new HashSet<>();
        int[] numOfFriends = new int[N+1];
        int[] sumOfCandy = new int[N+1];
        for(int i = 1; i <= N; i++) {
            int parent = find(i);
            parentSet.add(parent);
            numOfFriends[parent]++;
            sumOfCandy[parent] += children[i];
        }

        // 2) dp로 캔디 구하기
        ArrayList<Integer> parentList = new ArrayList<>(parentSet);
        long[][] dp = new long[parentSet.size() + 1][K];
        long max = 0;
        for(int i = 1; i <= parentSet.size(); i++) {
            int curFriend = numOfFriends[parentList.get(i - 1)];
            int curCandy = sumOfCandy[parentList.get(i - 1)];
            for (int j = K - 1; j >= 0; j--) {
                if (j >= curFriend) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curFriend] + curCandy);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        System.out.println(max);
    }
    public static int find(int x) {
        if(parent[x] == x) return x;
        return find(parent[x]);
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa < pb) {
            parent[pb] = pa;
        } else {
            parent[pa] = pb;
        }
    }
}
