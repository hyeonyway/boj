// 작성일: 2025-02-01
// 작성자: 김현문
// 문제: 백준 16566번 - 카드 게임 (https://www.acmicpc.net/problem/16566)
// 난이도: 골드 1
// 풀이: 이분 탐색, union-find

import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] cards = new int[M];
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        // index 기준 parents 생성
        parents = new int[M + 1];
        for (int i = 0; i <= M; i++) {
            parents[i] = i;
        }

        // 철수가 낼 카드.
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int magicCard = Integer.parseInt(st.nextToken());

            // magicCard의 숫자보다 높은 수 찾기(이분 탐색)
            int idx = higher(cards, magicCard);
            // 해당 숫자의 parent 찾기
            idx = find(idx);

            bw.write(cards[idx] + "\n");

            // 해당 인덱스는 사용했으니 +1한 값을 가리키도록 수정
            parents[idx] = idx + 1;
        }
        bw.flush();
        bw.close();
    }

    public static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    public static int higher(int[] arr, int num) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;

            if (arr[mid] <= num) { // magicCard 보다 mid의 값이 작거나 같으면 left = mid + 1
                l = mid + 1;
            } else { // arr[mid] 가 magicCard 보다 큰 값이면 right를 mid로 땡겨와서 더 작은 값이 있나 찾기
                r = mid;
            }
        }
        return l;
    }
}
