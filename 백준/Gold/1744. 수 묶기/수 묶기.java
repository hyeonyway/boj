import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }
        Collections.sort(positive);
        Collections.sort(negative);
        int result = 0;
        for (int i = positive.size() - 1; i >= 0; i--) {
            if (i >= 1) {
                result += Math.max(positive.get(i) + positive.get(i - 1), positive.get(i) * positive.get(i - 1));
                i--;
            } else {
                result += positive.get(i);
            }
        }

        for (int i = 0; i < negative.size(); i++) {
            if (i < negative.size() - 1) {
                result += Math.max(negative.get(i) + negative.get(i + 1), negative.get(i) * negative.get(i + 1));
                i++;
            } else {
                result += negative.get(i);
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
