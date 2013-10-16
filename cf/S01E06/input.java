import java.io.*;
import java.util.*;

public class input {
    public static int[] xl, yl, xh, yh;
    public static int length, width, m, totalArea,
        xLeftMost, xRightMost, yLeftMost, yRightMost;

    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }
    
    public static boolean inside(int x, int y, int i) {
        return ((x > xl[i]) && (y > yl[i]) && (x < xh[i]) && (y < yh[i]));
    }
    
    public static boolean overlapping(int x, int y) {
        if (inside(xl[x], yl[x], y) ||
            inside(xl[x], yh[x], y) ||
            inside(xh[x], yl[x], y) ||
            inside(xh[x], yh[x], y)) {
            return true;
        }
        if (xl[x] == xl[y] && xh[x] == xh[y] && yl[x] == yl[y] && yh[x] == yh[y]) {
            return true;
        }
        
        return false;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = 
                new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] data;
        int m;
        xl = new int[1001];
        yl = new int[1001];
        xh = new int[1001];
        yh = new int[1001];
        for (int t = 0; t < n; t++) {
            xLeftMost = 2147483647;
            yLeftMost = 2147483647;
            xRightMost = -2147483648;
            yRightMost = -2147483648;
            int totalArea = 0;
            data = br.readLine().split(" ");
            length = Integer.parseInt(data[0]);
            width = Integer.parseInt(data[1]);
            m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                data = br.readLine().split(" ");
                xl[i] = Integer.parseInt(data[0]);
                yl[i] = Integer.parseInt(data[1]);
                xh[i] = Integer.parseInt(data[2]);
                yh[i] = Integer.parseInt(data[3]);
                totalArea += (xh[i] - xl[i]) * (yh[i] - yl[i]);
                xLeftMost = min(xLeftMost, xl[i]);
                yLeftMost = min(yLeftMost, yl[i]);
                xRightMost = max(xRightMost, xh[i]);
                yRightMost = max(yRightMost, yh[i]);
            }
            boolean overlappingFlag= false;
            
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < i; j++) {
                    if (overlapping(i, j) || overlapping(j, i)) {
                            overlappingFlag = true;
                            break;
                        }
                }
            }
            if (overlappingFlag) {
                System.out.println("NONDISJOINT");
            } else {
                if (xRightMost - xLeftMost > length || yRightMost - yLeftMost > width) {
                    System.out.println("NONCONTAINED");
                } else {
                    if (totalArea < length * width) {
                        System.out.println("NONCOVERING");
                    } else {
                        System.out.println("OK");
                    }
                }
            }
        }
    }
}
