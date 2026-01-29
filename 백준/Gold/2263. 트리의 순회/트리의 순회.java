// 작성일: 2026-01-29
// 작성자: 김현문
// 문제: 백준 2263 - 트리의 순회 (https://www.acmicpc.net/problem/2263)
// 난이도: 골드 1
// 풀이: 트리, 재귀, 분할 정복

import java.io.*;
import java.util.*;

public class Main {
    static int[] inOrder, postOrder, preOrder;
    static int idx = 0;
    static Map<Integer, Integer> pos = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        inOrder = new int[N];
        postOrder = new int[N];
        preOrder = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inOrder[i] = Integer.parseInt(st1.nextToken());
            postOrder[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 0; i < N; i++) {
            pos.put(inOrder[i], i);
        }

        getPreOrder(0, N - 1, 0, N - 1);

        for (int i = 0; i < N; i++) {
            bw.write(preOrder[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    // postOrder의 끝에서 root를 찾고 inOrder에서 root 위치를 찾아 그 왼쪽은 left tree, 오른쪽은 right tree로 구분
    // preOrder를 구하는 것이기 때문에 root를 기록하고 left -> right 트리를 계속해서 쪼개감.
    public static void getPreOrder(int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft > inRight)
            return;
        
        // root = postOrder의 마지막 인덱스
        int root = postOrder[postRight];
        preOrder[idx++] = root;

        int rootIdx = pos.get(root);
        // 왼쪽 트리 크기
        int leftSize = rootIdx - inLeft;
        
        // preOrder => root -> left -> right 이므로 왼쪽부터 탐색
        // 현재 root 기준 왼쪽 트리
        getPreOrder(inLeft, rootIdx - 1, postLeft, postLeft + leftSize - 1);
        // 현재 root 기준 오른쪽 트리
        getPreOrder(rootIdx + 1, inRight, postLeft + leftSize, postRight - 1);
    }
}
