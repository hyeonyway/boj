import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> primes;
    static int result = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        get_prime(N);

        get_sum(N);

        bw.write(result+"\n");
        bw.flush();
        bw.close();
    }
    public static void get_prime(int N) {
        boolean[] nums = new boolean[N+1];
        for(int i = 2; i <= N; i++) {
            if(!nums[i]) {
                for(int j = i * 2; j <= N; j += i) {
                    nums[j] = true;
                }
            }
        }

        primes = new ArrayList<>();
        for(int i = 2; i <= N; i++) {
            if(!nums[i]) {
                primes.add(i);
            }
        }
    }

    public static void get_sum(int N) {
        int left = 0, right = 0;
        int sum = 0;
        while(left <= right) {
            if(sum < N) {
                if(right >= primes.size()) break;
                sum += primes.get(right++);
            } else if(sum > N) {
                sum -= primes.get(left++);
            } else {
                result++;
                sum -= primes.get(left++);
            }
        }
    }
}