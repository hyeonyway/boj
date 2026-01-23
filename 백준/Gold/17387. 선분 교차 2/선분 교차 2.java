import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Point p1 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point p2 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        Point p3 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point p4 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        if(solve(p1, p2, p3, p4)) {
            bw.write("1\n");
        } else {
            bw.write("0\n");
        }
        bw.flush();
        bw.close();

    }

    public static int ccw(Point p1, Point p2, Point p3) {
        long c =(long) (p2.x - p1.x) * (p3.y - p1.y) - (long) (p2.y - p1.y) * (p3.x - p1.x);
        if(c > 0) return 1;
        else if(c == 0) return 0;
        else return -1;
    }

    public static boolean solve(Point p1, Point p2, Point p3, Point p4) {
        int ccw1 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
        int ccw2 = ccw(p3, p4, p1) * ccw(p3, p4, p2);

        if(ccw1 <= 0 && ccw2 <= 0) {
            if (ccw1 == 0 && ccw2 == 0) {
                return Math.max(Math.min(p1.x, p2.x), Math.min(p3.x, p4.x)) <=
                        Math.min(Math.max(p1.x, p2.x), Math.max(p3.x, p4.x)) &&
                        Math.max(Math.min(p1.y, p2.y), Math.min(p3.y, p4.y)) <=
                        Math.min(Math.max(p1.y, p2.y), Math.max(p3.y, p4.y));
            }
            return true;
        }
        return false;
    }
}
