// 작성일: 2025-02-01
// 작성자: 김현문
// 문제: 백준 17143번 - 낚시왕 (https://www.acmicpc.net/problem/17143)
// 난이도: 골드 1
// 풀이: 미정

import java.io.*;
import java.util.*;

class Shark {
    int r, c, s, d, z;

    public Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 상어의 수

        int[][] sharkIdx = new int[R][C]; // 각 칸에 있는 상어의 인덱스
        List<Shark> sharks = new ArrayList<>(); // 상어 정보 리스트
        // 1) 상어 정보 입력 받기
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks.add(new Shark(r, c, s, d, z));
            sharkIdx[r][c] = m + 1; // 상어 인덱스는 1부터 시작
        }
        // 2) 낚시왕 이동하면서 상어 잡기
        int totalSize = 0;
        for (int fisherCol = 0; fisherCol < C; fisherCol++) {
            // 2-1) 낚시왕이 있는 열에서 가장 가까운 상어 잡기
            for (int row = 0; row < R; row++) {
                int idx = sharkIdx[row][fisherCol];
                if (idx > 0) { // 상어가 있으면
                    Shark caughtShark = sharks.get(idx - 1);
                    totalSize += caughtShark.z; // 상어 크기 더하기
                    sharks.set(idx - 1, null); // 상어 제거
                    sharkIdx[row][fisherCol] = 0; // 상어 인덱스 초기화
                    break;
                }
            }

            // 3) 상어 이동
            for (Shark shark : sharks) {
                if (shark == null)
                    continue; // 이미 잡힌 상어는 건너뛰기
                // 상어 이동 로직 구현 필요
                moveShark(shark, R, C);
            }
            // 4) 같은 칸에 있는 상어들 중 가장 큰 상어 제외 모두 제거
            int[][] newSharkIdx = new int[R][C];
            for (int i = 0; i < sharks.size(); i++) {
                Shark shark = sharks.get(i);
                if (shark == null)
                    continue; // 이미 잡힌 상어는 건너뛰기
                int r = shark.r;
                int c = shark.c;
                int idx = newSharkIdx[r][c];
                if (idx == 0) {
                    newSharkIdx[r][c] = i + 1; // 상어 인덱스는 1부터 시작
                } else {
                    Shark existingShark = sharks.get(idx - 1);
                    if (existingShark.z < shark.z) {
                        sharks.set(idx - 1, null); // 기존 상어 제거
                        newSharkIdx[r][c] = i + 1; // 현재 상어로 교체
                    } else {
                        sharks.set(i, null); // 현재 상어 제거
                    }
                }
            }
            sharkIdx = newSharkIdx; // 상어 인덱스 배열 갱신
        }
        bw.write(totalSize + "\n");
        bw.flush();
        bw.close();
    }

    public static void moveShark(Shark shark, int R, int C) {
        int r = shark.r;
        int c = shark.c;
        int s = shark.s;
        int d = shark.d;

        if (d == 1 || d == 2) { // 상하
            int cycle = (R - 1) * 2;
            s %= cycle;

            for (int i = 0; i < s; i++) {
                if (d == 1) { // 위
                    if (r == 0) {
                        d = 2;
                        r++;
                    } else
                        r--;
                } else { // 아래
                    if (r == R - 1) {
                        d = 1;
                        r--;
                    } else
                        r++;
                }
            }
        } else { // 좌우
            int cycle = (C - 1) * 2;
            s %= cycle;

            for (int i = 0; i < s; i++) {
                if (d == 4) { // 왼쪽
                    if (c == 0) {
                        d = 3;
                        c++;
                    } else
                        c--;
                } else { // 오른쪽
                    if (c == C - 1) {
                        d = 4;
                        c--;
                    } else
                        c++;
                }
            }
        }

        shark.r = r;
        shark.c = c;
        shark.d = d;
    }
}