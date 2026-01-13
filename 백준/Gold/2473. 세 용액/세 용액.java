import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static long min = Long.MAX_VALUE;
    static int[] result = new int[3];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        search();

        for(int i = 0; i < 3; i++){
            bw.write(result[i] + " ");
        }
        bw.flush();
        bw.close();
    }
    public static void search(){
        for(int i = 0; i < N-2; i++) {
            int left = i + 1, right = N - 1;
            while(left < right) {
                long sum = (long) arr[i] + arr[left] + arr[right];
                if(Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];
                }

                if(sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
    }
}
