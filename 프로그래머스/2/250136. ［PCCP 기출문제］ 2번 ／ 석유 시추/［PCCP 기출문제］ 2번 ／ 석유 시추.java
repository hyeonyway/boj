import java.util.*;

class Solution {
    public static int[] arr;
    public static boolean[][] visited;
    public static int max = 0;
    public static int[] di = new int[]{1, -1, 0, 0};
    public static int[] dj = new int[]{0, 0, 1, -1};
    public static int xLen, yLen;
    public int solution(int[][] land) {
        xLen = land[0].length;
        yLen = land.length;
        arr = new int[xLen];
        visited = new boolean[yLen][xLen];
        // return xLen;
        for(int i = 0; i < yLen; i++) {
            for(int j = 0; j < xLen; j++) {
                if(land[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j, land);
                }
            }
        }
        
        for(int i = 1; i < xLen; i++) {
            arr[i] += arr[i-1];
            max = Math.max(max, arr[i]);
        }
        
        return max;
    }
    
    public void dfs(int x, int y, int[][] land) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int cnt = 1;
        int xMin = xLen;
        int xMax = 0;
        
        q.add(new int[]{x, y});
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            xMin = Math.min(xMin, cj);
            xMax = Math.max(xMax, cj);
            for(int i = 0; i < 4; i++) {
                int ni = ci + di[i];
                int nj = cj + dj[i];
                
                if(ni < 0 || ni >= yLen || nj < 0 || nj >= xLen) {
                    continue;
                }
                if(visited[ni][nj] || land[ni][nj] == 0) {
                    continue;
                }
                q.add(new int[]{ni, nj});
                visited[ni][nj] = true;
                cnt++;
            }
        }
        arr[xMin] += cnt;
        if(xMax + 1 < xLen) {
            arr[xMax + 1] -= cnt;
        }
    }
}