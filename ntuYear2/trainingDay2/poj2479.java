import java.io.BufferedReader;
import java.io.InputStreamReader;

public class poj2479 {
    public static int n;
    public static int[] a,f,g;

    public static int bigger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }
    

    public static void main(String args[]) throws Exception {
        BufferedReader br = 
                new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int i;
        int max;
        String[] data;
        
        while (T-- != 0) {
            br.readLine();
            n = Integer.parseInt(br.readLine());
            a = new int[n];
            f = new int[n];
            g = new int[n];            
            data = br.readLine().split(" ");
            a[0] = Integer.parseInt(data[0]); 
            g[0] = a[0];
            f[0] = a[0];
            
            for (i = 1; i < n; i++) {
                a[i] = Integer.parseInt(data[i]);
                g[i] = a[i];
                f[i] = bigger(a[i], f[i-1] + a[i]);
            }
            for (i = 1; i < n; i++){
                f[i] = bigger(f[i], f[i-1]);
            }
            
            for (i = n - 2; i >=0; i--) {
                g[i] = bigger(g[i], g[i+1] + a[i]);
            }
            max = -2147483648;
            
            for (i = 0; i < n-1; i++) {
                max = bigger(max, f[i] + g[i+1]);
            }
            System.out.println(max);
        }
    }
}