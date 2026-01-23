import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int group_num = 1;
    static int[][] map;
    static int[][] group_map;
    static int[][] result;
    static ArrayList<Integer> group_size = new ArrayList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1) 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        result = new int[N][M];
        group_map = new int[N][M];

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // 2) 벽이 없는 곳 그룹화(dfs)
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 && group_map[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }

        // 3) 벽마다 주변 그룹 찾아서 더해서 result에 입력
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    find_group(i, j);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                bw.write(result[i][j] + "");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static void dfs(int x, int y) {
        ArrayDeque<int[]> pq = new ArrayDeque<>();
        int count = 1;
        pq.offer(new int[]{x, y});
        group_map[x][y] = group_num;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cx = cur[0], cy = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0 && group_map[nx][ny] == 0) {
                    group_map[nx][ny] = group_num;
                    pq.offer(new int[]{nx, ny});
                    count++;
                }
            }
        }
        group_size.add(count);
        group_num++;
    }

    // 벽의 상하좌우 그룹 체크해서 더하기
    public static void find_group(int x, int y) {
        Set<Integer> set = new HashSet<>();
        int sum = 1;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                set.add(group_map[nx][ny]);
            }
        }

        for(int num : set) {
            sum += group_size.get(num-1);
        }
        result[x][y] = sum % 10;
    }
}
