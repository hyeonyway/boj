import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1_000_000_007;
        int[][] map = new int[n+1][m+1];
        boolean[][] blocked = new boolean[n+1][m+1];
        for(int[] puddle: puddles) {
            int j = puddle[0];
            int i = puddle[1];
            blocked[i][j] = true;
        }
        map[1][1] = 1;
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(i == 1 && j == 1) {
                    continue;
                }
                
                if(blocked[i][j]) {
                    map[i][j] = 0;
                    continue;
                }
                
                map[i][j] = (map[i-1][j] + map[i][j-1]) % MOD;
            }
        }
        
        int answer = map[n][m];
        return answer;
    }
}