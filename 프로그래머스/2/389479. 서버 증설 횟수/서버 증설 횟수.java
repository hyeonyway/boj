class Solution {
    public int solution(int[] players, int m, int k) {
        // 기본으로 1대가 있음.
        // 서버개수 * m <= 유저수 < (서버개수 + 1) * m
        // 서버는 k시간 후에 내려간다.
        // 그럼 + 하자마자 - 해놓으면 되겠네 n시 + 1 n + k시 - 1
        int answer = 0;
        int size = players.length; // 24
        int[] server = new int[size];
        
        for(int i = 0; i < size; i++) {
            int player = players[i];
            while(player >= (server[i] + 1) * m) {
                answer++;
                for(int j = 0; j < k; j++) {
                    if(i + j >= size) {
                        break;
                    }
                    server[i + j]++;
                }
            }
        }
        return answer;
    }
}