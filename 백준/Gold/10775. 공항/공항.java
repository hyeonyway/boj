import java.io.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int count = 0;
        parents = new int[G+1];
        for(int i = 1; i <= G; i++) {
            parents[i] = i;
        }
        for(int i = 0; i < P; i++) {
            int gate = Integer.parseInt(br.readLine());
            int empty = find(gate);
            if(empty == 0) {
                break;
            }
            count++;
            union(empty, empty - 1);
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
    public static int find(int x) {
        if(parents[x] == x) { // 비어있으면
            return x;
        }
        return parents[x] = find(parents[x]);
    }
    public static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if(ra != rb) {
            parents[ra] = rb;
        }
    }
}
