import java.io.*;
import java.util.*;

public class Main {
    static int[][] dir = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[101][101];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            ArrayList<int[]> points = new ArrayList<>();
            points.add(new int[] { y, x });
            points.add(new int[] { y + dir[d][0], x + dir[d][1] });

            // curX, curY, endX, endY
            // nextX = endX - (curY - endY)
            // nextY = endY + (curX - endX)
            for (int j = 0; j < g; j++) {
                int size = points.size();
                int[] endPoint = points.get(size - 1);
                for (int k = size - 2; k >= 0; k--) {
                    int[] curPoint = points.get(k);
                    int nextX = endPoint[1] - (curPoint[0] - endPoint[0]);
                    int nextY = endPoint[0] + (curPoint[1] - endPoint[1]);
                    points.add(new int[] { nextY, nextX });
                }
            }

            for (int[] point : points) {
                map[point[0]][point[1]] = 1;
            }
        }

        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j] == 1 && map[i + 1][j + 1] == 1) {
                    result++;
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();

    }
}
