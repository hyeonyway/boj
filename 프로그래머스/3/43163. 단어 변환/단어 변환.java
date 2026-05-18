import java.util.*;

class Solution {
    static int answer = 1_000_000_000;
    static boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        if(!Arrays.asList(words).contains(target)) {
            return 0;
        }
        
        visited = new boolean[words.length];
        dfs(begin, target, words, 0);
        return answer;
    }
    
    public void dfs(String word, String target, String[] words, int depth) {
        if(word.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }
        if(answer <= depth) return;
        for(int i = 0; i < words.length; i++) {
            if(visited[i]) continue;
            
            if(diffCheck(word, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, depth + 1);
                visited[i] = false;
            }
        }
    }
    
    public boolean diffCheck(String w1, String w2) {
        int len = w1.length();
        int cnt = 0;
        for(int i = 0; i < len; i++) {
            if(w1.charAt(i) != w2.charAt(i)) cnt++;
            
            if(cnt > 1) return false;
        }
        return true;
    }
}