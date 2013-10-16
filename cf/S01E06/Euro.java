import java.io.*;
import java.util.*;

public class Euro {
    public static int[] dist;
    public static int[] a;
    public static boolean[] f;
    
    public static void main(String args[]) throws Exception {
        int T;
        
        BufferedReader br = 
                new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        String[] data;
        a = new int[6];
        while (T-- > 0) {
            data = br.readLine().split(" ");
            a[0] = Integer.parseInt(data[0]);
            a[1] = Integer.parseInt(data[1]);
            a[2] = Integer.parseInt(data[2]);
            a[3] = Integer.parseInt(data[3]);
            a[4] = Integer.parseInt(data[4]);
            a[5] = Integer.parseInt(data[5]);
            spfa();
        }
                
    }

    public static void spfa() {
        int start, now;
        
        dist = new int[1000];
        f = new boolean[1000];
        
        for (int i = 0; i < 1000; i++) {
            dist[i] = 1147483648;
            f[i] = false;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        q.add(0);
        f[0] = true;
        dist[0] = 0;
        int s;
        
        while (q.peek() != null) {
            //System.out.println("asdf");
            s = q.poll();
            start  = refuc(s);
            for (int i = 0; i < 6; i++) {
                now = fuc(start + a[i]);
                //System.out.println(now);
                if (now < 200) {
                    if (dist[s] + 1 < dist[now]) {
                        dist[now] = dist[s] + 1;
                        if (!f[now]) {
                            q.add(now);
                            f[now] = true;
                        }
                    }
                }
                
                now  = fuc(start - a[i]);
                if (now > 500 - 200) {
                    if (dist[s] + 1 < dist[now]) {
                        dist[now] = dist[s] + 1;
                        if (!f[now]) {
                            q.add(now);
                            f[now] = true;
                        }
                    }
                }
            }
            f[s] = false;
        }
        double average = 0;
        int maxLen = 0;
        for (int i = 1; i <= 100; i++) {
            if (maxLen < dist[i]) {
                maxLen = dist[i];
            }
            average += dist[i];
            //System.out.println(i + " : " + dist[i]);
        }
        System.out.printf("%.2f %d\n", average / 100, maxLen);
    }
    public static int refuc(int x) {
        if (x > 200) {
            return x - 500;
        }
        return x;
    }
    
    public static int fuc(int x) {
        if (x < 0) {
            return x + 500;
        }
        if (x >= 500) {
            return x - 500;
        }
        return x;
    }
}
