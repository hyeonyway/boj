import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] singer;
    static ArrayList<Integer>[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        singer = new int[N+1];
        arr = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int front = Integer.parseInt(st.nextToken());
            for(int j = 0; j < size - 1; j++) {
                int back = Integer.parseInt(st.nextToken());
                arr[front].add(back);
                singer[back]++;
                front = back;
            }
        }

        topologicalSort();
    }
    public static void topologicalSort(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = 1; i <= N; i++){
            if(singer[i] == 0) {
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur).append("\n");
            count++;
            for(int i : arr[cur]){
                singer[i]--;
                if(singer[i] == 0){
                    q.add(i);
                }
            }
        }

        if(count == N){
            System.out.println(sb.toString());
        } else {
            System.out.println(0);
        }
    }
}
