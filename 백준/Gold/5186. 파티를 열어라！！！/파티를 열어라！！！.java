import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= K; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            int[] sober = new int[l];
            int[] drunk = new int[l];

            ArrayList<Integer>[] caps = new ArrayList[l];
            for (int i = 0; i < l; i++)
                caps[i] = new ArrayList<>();

            // 사람 입력: (지역, 상태)
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken()) - 1;
                char ch = st.nextToken().charAt(0);
                // 보통 문제에서 S = 술 안 취함, I = 술 취함(혹은 반대 표기) 중 하나입니다.
                // 아래는 "S가 sober"인 케이스(많이 알려진 풀이 기준)
                if (ch == 'S')
                    sober[r]++;
                else
                    drunk[r]++;
            }

            // 차 입력: (지역, 정원)
            for (int i = 0; i < c; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken()) - 1;
                int cap = Integer.parseInt(st.nextToken());
                caps[r].add(cap);
            }

            long left = 0;
            for (int r = 0; r < l; r++) {
                int people = sober[r] + drunk[r];
                if (people == 0)
                    continue;

                if (sober[r] == 0 || caps[r].isEmpty()) {
                    left += people;
                    continue;
                }

                ArrayList<Integer> list = caps[r];
                list.sort(Collections.reverseOrder());

                int k = Math.min(sober[r], list.size());
                long seats = 0;
                for (int i = 0; i < k; i++)
                    seats += list.get(i);

                long sent = Math.min((long) people, seats);
                left += (people - sent);
            }

            sb.append("Data Set ").append(tc).append(":\n");
            sb.append(left).append("\n");
        }

        System.out.print(sb);
    }
}