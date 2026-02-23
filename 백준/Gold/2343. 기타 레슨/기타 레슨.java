import java.io.*;
import java.util.*;

// 작성일: 2026-02-23
// 작성자: 김현문
// 문제: BOJ 2343 기타 레슨 - (https://www.acmicpc.net/problem/2343)
// 난이도: 골드 5
// 풀이: 미정

public class Main {
    static int N, M;
    static int[] len;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        len = new int[N];
        st = new StringTokenizer(br.readLine());
        int left = 0, right = 0;
        for (int i = 0; i < N; i++) {
            len[i] = Integer.parseInt(st.nextToken());
            right += len[i];
            left = Math.max(left, len[i]);
        }

        bw.write(binSearch(left, right) + "\n");
        bw.flush();
        bw.close();
    }

    // left => 가장 큰 수
    // right => 레슨들의 합
    // mid => 블루레이 하나의 크기
    // ex
    // left = 9 right = 45
    // cnt = 1
    //
    public static int binSearch(int left, int right) {
        while (left <= right) {
            int sum = 0;
            int mid = (left + right) / 2; // 블루레이 하나의 크기
            int cnt = 1; // 블루레이 갯수
            for (int i = 0; i < N; i++) {
                sum += len[i];
                if (sum > mid) { // 블루레이 하나의 크기보다 커지면
                    sum = len[i];
                    cnt++; // 블루레이 갯수 추가
                }
            }
            if (cnt <= M) { // 나눠진 블루레이 갯수보다 M이 크거나 작으면
                right = mid - 1; // 블루레이 크기 줄이기
            } else { // 나눠진 블루레이 갯수가 M보다 크면
                left = mid + 1; // 블루레이 크기 키우기
            }
        }
        return left;
    }
}
