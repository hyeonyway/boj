import java.io.*;
import java.util.*;

class Matrix{
    int row;
    int col;
    Matrix(int row, int col){
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static int N;
    static Matrix[] matrix;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        matrix = new Matrix[N+1];
        dp = new int[N+1][N+1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrix[i] = new Matrix(r, c);
        }
        get_min();
        bw.write(dp[1][N] + "\n");
        bw.flush();
        bw.close();
    }
    public static void get_min(){
        for(int i = 2; i <= N; i++){
            for(int j = i-1; j > 0; j--){
                dp[j][i] = Integer.MAX_VALUE;
                for(int k = j; k < i; k++) {
                    int mul = matrix[j].row * matrix[k].col * matrix[i].col;
                    dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k+1][i] + mul);
                }
            }
        }
    }
}
