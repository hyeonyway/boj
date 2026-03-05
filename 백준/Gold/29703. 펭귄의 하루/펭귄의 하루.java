import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int count = 0;
    static char[][] town;
    static int[] feed = new int[2];
    static int[] di = new int[] { -1, 1, 0, 0 };
    static int[] dj = new int[] { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] start = new int[2];
        int[] home = new int[2];
        town = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                town[i][j] = input.charAt(j);
                if (town[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
                if (town[i][j] == 'H') {
                    home[0] = i;
                    home[1] = j;
                }
            }
        }

        bw.write(bfs(start, home) + "\n");
        bw.flush();
        bw.close();
    }

    public static int bfs(int[] s, int[] h) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[][] dist1 = new int[N][M];
        int[][] dist2 = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist1[i], -1);
            Arrays.fill(dist2[i], -1);
        }
        int si = s[0];
        int sj = s[1];
        q.add(new int[] { si, sj, 0 }); // ci, cj, state
        dist1[si][sj] = 0;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int ci = point[0];
            int cj = point[1];
            int state = point[2];
            int cur = (state == 0) ? dist1[ci][cj] : dist2[ci][cj];
            for (int i = 0; i < 4; i++) {
                int ni = ci + di[i];
                int nj = cj + dj[i];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M || town[ni][nj] == 'D') {
                    continue;
                }

                int nextState = state;
                if (town[ni][nj] == 'F')
                    nextState = 1;

                if (nextState == 0) {
                    if (dist1[ni][nj] != -1)
                        continue;
                    dist1[ni][nj] = cur + 1;
                    q.add(new int[] { ni, nj, nextState });
                } else {
                    if (dist2[ni][nj] != -1)
                        continue;
                    dist2[ni][nj] = cur + 1;
                    q.add(new int[] { ni, nj, nextState });
                }
            }
        }

        return dist2[h[0]][h[1]];
    }
}
