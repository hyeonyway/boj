class Solution {
    private final int LENGTH = 3;
    
    public int solution(String[] board) {
        // false 조건
        // 1. O개수 < X개수
        // 2. O가 완성됐는데 X도 완성됐을 때
        // 3. 후공인 X만 놨을 때 >> 1번이랑 겹칠 듯
        
        char[][] map = new char[LENGTH][LENGTH];
        int oCount = 0;
        int xCount = 0;
        
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'O') {
                    oCount++;
                } else if (map[i][j] == 'X') {
                    xCount++;
                }
            }
        }
        
        boolean oWin = isComplete(map, 'O');
        boolean xWin = isComplete(map, 'X');

        if (oCount < xCount) {
            return 0;
        }

        if (oCount > xCount + 1) {
            return 0;
        }

        if (oWin && xWin) {
            return 0;
        }

        if (oWin && oCount != xCount + 1) {
            return 0;
        }

        if (xWin && oCount != xCount) {
            return 0;
        }
        return 1;
    }
    
    public boolean isComplete(char[][] map, char condition) {
        for(int i = 0; i < LENGTH; i++) {
            // 1. 가로
            if (
                map[i][0] == condition && 
                map[i][0] == map[i][1] &&
                map[i][1] == map[i][2]
            ) {
                return true;
            }
            // 2. 세로
            if (
                map[0][i] == condition &&
                map[0][i] == map[1][i] &&
                map[1][i] == map[2][i]
            ) {
                return true;
            }
        }
        if (map[1][1] == condition) {
            // 3. 대각 /
            if(map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
                return true;
            }
            // 4. 대각 \
            if(map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
                return true;
            }
        }
        
        return false;
    }
}