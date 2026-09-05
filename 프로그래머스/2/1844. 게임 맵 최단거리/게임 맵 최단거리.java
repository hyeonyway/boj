import java.util.*;

class Solution {
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public int solution(int[][] maps) {
        int answer = bfs(maps);
        
        return answer;
    }
    
    public int bfs(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        while(!dq.isEmpty()) {
            int[] cur = dq.pop();
            int ci = cur[0];
            int cj = cur[1];
            int cnt = cur[2];
            
            if(ci == n-1 && cj == m-1) {
                return cnt;
            }
            
            for(int i = 0; i < 4; i++) {
                int ni = ci + di[i];
                int nj = cj + dj[i];
                if(ni < 0 || ni >= n || nj < 0 || nj >= m || visited[ni][nj] || maps[ni][nj] == 0) {
                    continue;
                }
                visited[ni][nj] = true;
                dq.add(new int[]{ni, nj, cnt + 1});
            }
        }
        return -1;
    }
}