import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] students;
    static int[][] seats;
    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int size = N * N;
        students = new int[size + 1][4];
        seats = new int[N][N];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                students[cur][j] = Integer.parseInt(st.nextToken());
            }
            // 1. 학생의 자리 지정
            int[] point = bfsSeat(cur, students[cur]);
            seats[point[0]][point[1]] = cur;
        }
        // 2. 만족도 구하기
        // 2-1. 인접한 칸에 앉은 좋아하는 학생의 수 >> 0 - 0, 1 - 1, 2 - 10, 3 - 100, 4 - 1000
        int satisfaction = getSatisfaction();

        // 3. 만족도의 총 합 출력
        System.out.println(satisfaction);
    }

    public static int[] bfsSeat(int cur, int[] friends) {
        int[] result = new int[2];
        int maxFriend = 0;
        int[][] friendCount = new int[N][N];
        // 1-1. 좋아하는 학생이 가장 많이 인접한 칸
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (seats[i][j] != 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if (ni < 0 || nj < 0 || ni >= N || nj >= N) {
                        continue;
                    }
                    for (int friend : friends) {
                        if (friend == seats[ni][nj]) {
                            friendCount[i][j]++;
                        }
                    }
                }

                maxFriend = Math.max(friendCount[i][j], maxFriend);
            }
        }
        ArrayList<int[]> candidateSeat = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (seats[i][j] == 0 && friendCount[i][j] == maxFriend) {
                    candidateSeat.add(new int[] { i, j });
                }
            }
        }

        if (candidateSeat.size() == 1) {
            result = candidateSeat.get(0);
            return result;
        }

        // 1-2. 인접한 칸 중 비어있는 칸이 가장 많은 칸
        // 1-3. 행의 번호 열의 번호가 가장 작은 칸. (왼쪽 위부터 보기)
        int max = -1;
        for (int[] s : candidateSeat) {
            int i = s[0];
            int j = s[1];
            int blankCount = 0;
            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];
                if (ni < 0 || nj < 0 || ni >= N || nj >= N) {
                    continue;
                }
                if (seats[ni][nj] == 0) {
                    blankCount++;
                }
            }

            if (blankCount > max) {
                result[0] = i;
                result[1] = j;
                max = blankCount;
            }
        }
        return result;
    }

    public static int getSatisfaction() {
        int satisfaction = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cur = seats[i][j];
                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if (ni < 0 || nj < 0 || ni >= N || nj >= N) {
                        continue;
                    }
                    for (int friend : students[cur]) {
                        if (friend == seats[ni][nj]) {
                            count++;
                        }
                    }
                }

                if (count == 1) {
                    satisfaction += 1;
                } else if (count == 2) {
                    satisfaction += 10;
                } else if (count == 3) {
                    satisfaction += 100;
                } else if (count == 4) {
                    satisfaction += 1000;
                }
            }
        }
        return satisfaction;
    }
}
