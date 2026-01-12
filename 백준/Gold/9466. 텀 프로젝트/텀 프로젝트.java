import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static boolean[] check;
    static int[] list;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            count = 0;
            visited = new boolean[N+1];
            check = new boolean[N+1];
            list = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }
            for(int i = 1; i <= N; i++) {
                dfs(i);
            }
            bw.write((N - count)+"\n");
        }
        bw.flush();
        bw.close();
    }
    public static void dfs(int start){
        if(visited[start]) return;

        visited[start] = true;
        int next = list[start];

        if(!visited[next]) {
            dfs(next);
        } else {
            if(!check[next]) {
                count++;
                for(int i = next; i != start; i = list[i]) {
                    count++;
                }
            }
        }

        check[start] = true;
    }
}
