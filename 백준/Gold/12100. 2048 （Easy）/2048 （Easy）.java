// 작성일: 2025-01-30
// 작성자: 김현문
// 문제: 백준 12100번 - 2048 (Easy) (https://www.acmicpc.net/problem/12100)
// 난이도: 골드 1
// 풀이: 미정

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, 0);
        System.out.println(max);
    }

    static void dfs(int[][] board, int depth) {
        if (depth == 5) {
            updateMax(board);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int[][] next = copy(board);
            move(next, d);
            dfs(next, depth + 1);
        }
    }

    static void updateMax(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
    }

    static int[][] copy(int[][] board) {
        int[][] n = new int[N][N];
        for (int i = 0; i < N; i++) {
            n[i] = board[i].clone();
        }
        return n;
    }

    // dir: 0=up, 1=down, 2=left, 3=right
    static void move(int[][] board, int dir) {
        if (dir == 0) { // UP
            for (int c = 0; c < N; c++) {
                int[] line = new int[N];
                boolean[] merged = new boolean[N];
                int idx = 0;

                for (int r = 0; r < N; r++) {
                    if (board[r][c] == 0)
                        continue;

                    if (idx > 0 && line[idx - 1] == board[r][c] && !merged[idx - 1]) {
                        line[idx - 1] *= 2;
                        merged[idx - 1] = true;
                    } else {
                        line[idx++] = board[r][c];
                    }
                }

                for (int r = 0; r < N; r++) {
                    board[r][c] = line[r];
                }
            }
        }

        else if (dir == 1) { // DOWN
            for (int c = 0; c < N; c++) {
                int[] line = new int[N];
                boolean[] merged = new boolean[N];
                int idx = 0;

                for (int r = N - 1; r >= 0; r--) {
                    if (board[r][c] == 0)
                        continue;

                    if (idx > 0 && line[idx - 1] == board[r][c] && !merged[idx - 1]) {
                        line[idx - 1] *= 2;
                        merged[idx - 1] = true;
                    } else {
                        line[idx++] = board[r][c];
                    }
                }

                for (int r = N - 1, i = 0; r >= 0; r--, i++) {
                    board[r][c] = line[i];
                }
            }
        }

        else if (dir == 2) { // LEFT
            for (int r = 0; r < N; r++) {
                int[] line = new int[N];
                boolean[] merged = new boolean[N];
                int idx = 0;

                for (int c = 0; c < N; c++) {
                    if (board[r][c] == 0)
                        continue;

                    if (idx > 0 && line[idx - 1] == board[r][c] && !merged[idx - 1]) {
                        line[idx - 1] *= 2;
                        merged[idx - 1] = true;
                    } else {
                        line[idx++] = board[r][c];
                    }
                }

                board[r] = line;
            }
        }

        else { // RIGHT
            for (int r = 0; r < N; r++) {
                int[] line = new int[N];
                boolean[] merged = new boolean[N];
                int idx = 0;

                for (int c = N - 1; c >= 0; c--) {
                    if (board[r][c] == 0)
                        continue;

                    if (idx > 0 && line[idx - 1] == board[r][c] && !merged[idx - 1]) {
                        line[idx - 1] *= 2;
                        merged[idx - 1] = true;
                    } else {
                        line[idx++] = board[r][c];
                    }
                }

                for (int c = N - 1, i = 0; c >= 0; c--, i++) {
                    board[r][c] = line[i];
                }
            }
        }
    }
}