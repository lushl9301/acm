import java.util.*;

public class dna {
    public static String s;
    public static int[] f, g;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        s = sc.next();
        f = new int[n + 1];
        g = new int[n + 1];
        for (int i = 0; i < n; i++) {
            f[i] = 0;
            g[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'A') {
                f[i + 1] = f[i];
                g[i + 1] = min(f[i], g[i]) + 1;
            } else {
                g[i + 1] = g[i];
                f[i + 1] = min(f[i], g[i]) + 1;
            }
        }
        System.out.println(f[n]);
        
    }
    public static int min(int a, int b) {
        if (a > b) {
           return b;
        } else {
            return a;
        }
    }
}
