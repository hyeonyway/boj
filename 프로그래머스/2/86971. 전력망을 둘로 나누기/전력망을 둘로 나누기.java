import java.util.*;

class Solution {
    static List<Integer>[] graph;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        graph = new ArrayList[n + 1];
        
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] wire: wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        for(int[] wire: wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            
            boolean[] visited = new boolean[n + 1];
            
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));
            
            int cnt1 = dfs(v1, visited);
            int cnt2 = n - cnt1;
            
            int diff = Math.abs(cnt1 - cnt2);
            
            answer = Math.min(answer, diff);
            
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        return answer;
    }
    static int dfs(int cur, boolean[] visited) {
        visited[cur] = true;
        
        int cnt = 1;
        
        for(int next: graph[cur]) {
            if(!visited[next]) {
                cnt += dfs(next, visited);
            }
        }
        
        return cnt;
    }
}