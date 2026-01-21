import java.io.*;
import java.util.*;

public class Main {
    static int N, len = 0;
    static int[] arr, search;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        search = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < N; i++) {
            if(arr[i] > search[len]) {
                len += 1;
                search[len] = arr[i];
            } else {
                int idx = binary_search(0, len, arr[i]);
                if(idx != 0) {
                    search[idx] = arr[i];
                }
            }
        }
        bw.write(len+"\n");
        bw.flush();
        bw.close();
    }

    public static int binary_search(int left, int right, int val) {
        while(left < right) {
            int mid = (left + right) / 2;
            if(search[mid] < val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
