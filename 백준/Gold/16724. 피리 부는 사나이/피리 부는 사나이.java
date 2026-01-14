import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parents;
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N*M];
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    parents[i * M + j] = i * M + j;
            }
        }
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++){
                int ni = i, nj = j;
                if(map[i][j] == 'U') ni--;
                else if(map[i][j] == 'D') ni++;
                else if(map[i][j] == 'L') nj--;
                else if(map[i][j] == 'R') nj++;
                union(i * M + j, ni * M + nj);
            }
        }

        Set<Integer> visited = new HashSet<>();
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                visited.add(find(i * M + j));
            }
        }
        bw.write(visited.size() + " ");
        bw.flush();
        bw.close();
    }

    public static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if(px < py) parents[py] = px;
        else parents[px] = py;
    }

    public static int find(int x) {
        if(parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }
}