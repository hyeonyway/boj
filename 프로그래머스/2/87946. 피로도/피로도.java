class Solution {
    static int answer = -1;
    static int length;
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        length = dungeons.length;
        visited = new boolean[length];
        
        search(k, dungeons, 0);
        
        return answer;
    }
    
    public void search(int k, int[][] dungeons, int depth) {
        for(int i = 0; i < length; i++) {
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                search(k - dungeons[i][1], dungeons, depth + 1);
                visited[i] = false;
            }
        }
        
        answer = Math.max(depth, answer);
    }
}