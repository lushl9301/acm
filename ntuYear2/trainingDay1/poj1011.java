import java.util.*;

public class poj1011 {
    public static int a[];
    public static boolean f[];
    public static int n;
    public static int tot;
    public static int availMin;
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        while (n != 0) {
            a = new int[n];
            f = new boolean[n];
            tot = 0;
            
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                tot += a[i];
                f[i] = true;
            }
            sort(a);
            availMin = a[0];
            for (; availMin <= tot; availMin++) {
                if (tot % availMin == 0) {
                    if (search(0, n - 1, tot / availMin, n)) {
                        System.out.println(availMin);
                        break;
                    }
                }
            }
            n = sc.nextInt();
        }
    }
    public static void sort(int[] a) {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }        
    }
    public static boolean search(int sum, int k, int numFullStick, 
                                 int remainStick) {
        if (sum == availMin) {
            numFullStick--;
            if (numFullStick == 0) {          //if find all sticks then return true
                if (remainStick == 0) {
                    return true;
                } else {
                    return false;
                }
            }
            
            sum = 0;
            k = n - 1;
            while (!f[k]) k--;               //find the biggest one which is not used
            f[k] = false;
            if (search(a[k], k - 1, numFullStick, remainStick - 1)) { 
                return true;  // if the biggest one cannot be used then there is no solution
            } else {
                f[k] = true;
                return false;
            }
        }
        
        int left = availMin - sum;
        int lastUsed = 0;
        for (int i = k; i >= 0; i--) {
            if ((a[i] != lastUsed) && f[i] && (a[i] <= left)) {
                f[i] = false;
                if (search(sum + a[i], i - 1, numFullStick, remainStick - 1)) {
                    return true;
                }
                f[i] = true;
                lastUsed = a[i];
            }
        }
        return false;
    }
}
