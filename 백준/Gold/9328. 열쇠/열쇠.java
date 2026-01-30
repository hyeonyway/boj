// 작성일: 2025-01-30
// 작성자: 김현문
// 문제: 백준 9328번 - 열쇠 (https://www.acmicpc.net/problem/9328)
// 난이도: 골드 1
// 풀이: 너비 우선 탐색

import java.io.*;
import java.util.*;

public class Main {
    static int h, w;
    static char[][] map;
    static String keys;
    static Map<Character, Boolean> haveKey;
    static int[] di = { -1, 1, 0, 0 };
    static int[] dj = { 0, 0, -1, 1 };
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h + 2][w + 2];
            count = 0;
            for (int i = 0; i < h + 2; i++) {
                Arrays.fill(map[i], '.');
            }
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i + 1][j + 1] = line.charAt(j);
                }
            }

            keys = br.readLine();
            if (keys.equals("0")) {
                keys = "";
            }

            haveKey = new HashMap<>();
            for (char key : keys.toCharArray()) {
                haveKey.put(key, true);
            }

            bfs(0, 0);

            bw.write(count + "\n");

            // for (int i = 0; i < h + 2; i++) {
            // for (int j = 0; j < w + 2; j++) {
            // bw.write(map[i][j] + " ");
            // }
            // bw.newLine();
            // }
        }
        bw.flush();
        bw.close();
    }

    public static void bfs(int i, int j) {
        // 1) 0, 0 시작
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[h + 2][w + 2];
        ArrayList<int[]> locked = new ArrayList<>();
        q.add(new int[] { i, j });
        visited[i][j] = true;
        // 2) 벽이 아니면 상하좌우 전진
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            for (int k = 0; k < 4; k++) {
                int ni = ci + di[k];
                int nj = cj + dj[k];
                if (ni < 0 || nj < 0 || ni >= h + 2 || nj >= w + 2 || visited[ni][nj] || map[ni][nj] == '*')
                    continue;

                if (map[ni][nj] >= 'A' && map[ni][nj] <= 'Z') { // 문일 때
                    if (!haveKey.getOrDefault(Character.toLowerCase(map[ni][nj]), false)) { // 키가 없으면 locked에 추가
                        locked.add(new int[] { ni, nj });
                        continue; // continue
                    }
                } else if (map[ni][nj] >= 'a' && map[ni][nj] <= 'z') { // 열쇠일 때
                    haveKey.put(map[ni][nj], true);
                    for (int[] lock : locked) {
                        if (Character.toLowerCase(map[lock[0]][lock[1]]) == map[ni][nj]) {
                            q.add(lock);
                        }
                    }
                } else if (map[ni][nj] == '$') {
                    count++;
                }
                q.add(new int[] { ni, nj }); // 키가 없는 경우를 제외하면 모두 다음 위치로 이동
                visited[ni][nj] = true;
            }
        }
    }
}
