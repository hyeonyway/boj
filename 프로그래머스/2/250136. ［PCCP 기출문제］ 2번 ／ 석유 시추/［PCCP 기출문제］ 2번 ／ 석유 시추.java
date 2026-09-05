import java.util.*;

class Solution {
    static int[][] oilGroup;
    static int n, m;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public int solution(int[][] land) {
        int answer = 0;
        int groupId = 1;
        n = land.length;
        m = land[0].length;
        oilGroup = new int[n][m];
        Map<Integer, Integer> oilMap = new HashMap<>();
        // 1. bfs로 석유 덩어리 그룹화
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(land[i][j] == 1 && oilGroup[i][j] == 0) {
                    oilMap.put(groupId, bfs(i, j, land, groupId));
                    groupId++;
                }
            }
        }
        
        // 2. 각 열마다 돌면서 set에 그룹 저장
        for(int j = 0; j < m; j++) {
            Set<Integer> groupSet = new HashSet<>();
            for(int i = 0; i < n; i++) {
                if(land[i][j] == 1) {
                    groupSet.add(oilGroup[i][j]);
                }
            }
            // 3. 저장된 그룹의 총합과 최대값 비교
            int sum = 0;
            for(int group: groupSet) {
                sum += oilMap.get(group);
            }
            answer = Math.max(sum, answer);
        }
        return answer;
    }
    
    public int bfs(int i, int j, int[][] land, int groupId) {
        Deque<int[]> dq = new ArrayDeque<>();
        int cnt = 1;
        dq.add(new int[]{i, j});
        oilGroup[i][j] = groupId;
        while(!dq.isEmpty()) {
            int[] cur = dq.pop();
            int ci = cur[0];
            int cj = cur[1];
            for(int k = 0; k < 4; k++) {
                int ni = ci + di[k];
                int nj = cj + dj[k];
                if(ni < 0 || ni >= n || nj < 0 || nj >= m) {
                    continue;
                }
                if(oilGroup[ni][nj] != 0 || land[ni][nj] == 0) {
                    continue;
                }
                oilGroup[ni][nj] = groupId;
                dq.add(new int[]{ni, nj});
                cnt++;
            }
            
        }
        return cnt;
    }
}