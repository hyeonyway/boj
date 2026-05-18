class Solution {
    static boolean[] visited;
    static int dungeonLength;
    static int answer = 0;
    public int solution(int k, int[][] dungeons) {
        dungeonLength = dungeons.length;
        visited = new boolean[dungeonLength];
        
        dfs(k, 0, dungeons);
        
        return answer;
    }
    public void dfs(int k, int cnt, int[][] dungeons) {
        answer = Math.max(cnt, answer);
        
        for(int i = 0; i < dungeonLength; i++) {
            if(k >= dungeons[i][0] && !visited[i]) {
                visited[i] = true;
                dfs(k - dungeons[i][1], cnt + 1, dungeons);
                visited[i] = false;
            }
        }
    }
}