import java.io.*;
import java.util.*;

public class Main {
    static int oddMin = Integer.MAX_VALUE;
    static int evenMin = Integer.MAX_VALUE;
    static ArrayList<Integer> oddPoint = new ArrayList<>();
    static ArrayList<Integer> evenPoint = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int point = Integer.parseInt(st.nextToken());
            if (point % 2 == 0) {
                evenPoint.add(point);
            } else {
                oddPoint.add(point);
            }
        }
        Collections.sort(oddPoint);
        Collections.sort(evenPoint);
        // 짝 - 짝 / 홀 - 홀 >> 짝
        // 짝 - 홀 >> 홀
        findOddMin();
        findEvenMin();

        if (evenMin == Integer.MAX_VALUE) {
            evenMin = -1;
        }

        if (oddMin == Integer.MAX_VALUE) {
            oddMin = -1;
        }

        System.out.println(evenMin + " " + oddMin);
    }

    public static void findOddMin() {
        int oddIdx = 0;
        int evenIdx = 0;

        while (oddIdx < oddPoint.size() && evenIdx < evenPoint.size()) {
            int oddValue = oddPoint.get(oddIdx) - evenPoint.get(evenIdx);
            if (oddValue < 0) {
                oddIdx++;
            } else {
                evenIdx++;
            }
            oddMin = Math.min(Math.abs(oddValue), oddMin);
        }
    }

    public static void findEvenMin() {
        for (int i = 0; i < oddPoint.size() - 1; i++) {
            evenMin = Math.min(oddPoint.get(i + 1) - oddPoint.get(i), evenMin);
        }
        for (int i = 0; i < evenPoint.size() - 1; i++) {
            evenMin = Math.min(evenPoint.get(i + 1) - evenPoint.get(i), evenMin);
        }
    }
}
