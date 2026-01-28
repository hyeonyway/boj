// 작성일: 2025-01-28
// 작성자: 김현문
// 문제: 백준 2098 - 외판원 순회 (https://www.acmicpc.net/problem/2098)
// 난이도: 골드 1
// 풀이: TSP, 비트마스킹, 비트DP

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // (2 <= N <= 16)

        W = new int[N][N];

        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[현재 도시][방문한 도시들 비트마스크] = 최소 비용
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        bw.write(tsp(0, 1) + "\n"); // 0번 도시에서 시작, 0번 도시 방문 표시
        bw.flush();
        bw.close();
        br.close();
    }

    public static int tsp(int start, int visited) {
        if (visited == (1 << N) - 1) { // 모든 도시를 방문한 경우
            if (W[start][0] != 0) { // 출발지로 돌아갈 수 있는 경우
                return W[start][0];
            } else {
                return INF; // 출발지로 돌아갈 수 없는 경우
            }
        }

        if (dp[start][visited] != -1) { // 이미 계산된 경우 해당 값 리턴
            return dp[start][visited];
        }

        dp[start][visited] = INF;

        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) == 0 && W[start][next] != 0) { // 아직 방문하지 않은 도시이고, 이동 가능할 때
                int cost = tsp(next, visited | (1 << next)) + W[start][next];
                dp[start][visited] = Math.min(dp[start][visited], cost);
            }
        }

        return dp[start][visited];
    }
}
