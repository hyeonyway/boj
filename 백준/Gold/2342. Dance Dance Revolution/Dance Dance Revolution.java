import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static int[][][] dp;
    static int[][] weight = {
            {0, 2, 2, 2, 2},
            {0, 1, 3, 4, 3},
            {0, 3, 1, 3, 4},
            {0, 4, 3, 1, 3},
            {0, 3, 4, 3, 1}
    };
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // DP
        int left = 0, right = 0; // 1 위 2 왼 3 아 4 오
        // 제자리 1
        // 0에서 출발 2
        // 인접장소 3
        // 반대편 4
        while(true) {
            int nxt = Integer.parseInt(st.nextToken());
            if(nxt == 0) break;
            list.add(nxt);
        }

        dp = new int[list.size()][5][5];
        int result = recursion(0, 0, 0);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
    static public int recursion(int depth, int left, int right) {
        if(depth == list.size()){
            return 0;
        }

        if(dp[depth][left][right] != 0){
            return dp[depth][left][right];
        }
        
        int nxt = list.get(depth);

        dp[depth][left][right] = Math.min(recursion(depth+1, nxt, right) + weight[left][nxt], recursion(depth+1, left, nxt) + weight[right][nxt]);
        return dp[depth][left][right];
    }
}
