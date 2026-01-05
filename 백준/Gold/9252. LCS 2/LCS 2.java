import java.io.*;
import java.util.*;

public class Main {
    public static int[][] map;
    public static char[] word1, word2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        word1 = br.readLine().toCharArray();
        word2 = br.readLine().toCharArray();

        map = new int[word2.length+1][word1.length+1];

        for(int i = 1; i <= word2.length; i++) {
            for(int j = 1; j <= word1.length; j++) {
                if(word1[j-1] == word2[i-1]) {
                    map[i][j] = map[i-1][j-1] + 1;
                } else {
                    map[i][j] = Math.max(map[i-1][j], map[i][j-1]);
                }
            }
        }
        int len = map[word2.length][word1.length];

        if(len > 0) {
            bw.write(len + "\n");
            bw.write(getSubString());
        } else {
            bw.write("0\n");
        }
        bw.flush();
        bw.close();
    }

    public static String getSubString() {
        StringBuilder sb = new StringBuilder();
        int row = word2.length;
        int col = word1.length;
        while(row > 0 && col > 0) {
            if(word1[col-1] == word2[row-1]) {
                sb.append(word1[col-1]);
                row--;
                col--;
            } else if(map[row-1][col] > map[row][col-1]) {
                row--;
            } else {
                col--;
            }
        }

        return sb.reverse().toString();
    }
}
