import java.util.*;

public class poj2299 {
    public static int a[];
    public static int tmpa[];
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n != 0) {
            a = new int[n];
            tmpa = new int[n];
            
            for (int i = 0; i<n; i++) {
                a[i] = sc.nextInt();
            }
            System.out.println(mergeSort(n, 0, n - 1));
            n = sc.nextInt();
        }
    }
    public static long mergeSort(int n, int l, int r)
    {
        if (r - l == 1) {
            if (a[l] > a[r]) {
                int tmp = a[l];
                a[l] = a[r];
                a[r] = tmp;
                return 1;
            }
            return 0;
        }
        if (l >= r) {
            return 0;
        }
        
        int mid = (l + r) / 2;
        long tmpAns = mergeSort(n, l, mid) + mergeSort(n, mid + 1, r);
        int i = l;
        int j = mid + 1;
        int k = l;
        
        for (; k<=r; k++) {
            if ((j > r) || (i <= mid && a[i] <= a[j])) {
                tmpa[k] = a[i];
                i++;
            } else {
                tmpa[k] = a[j];
                tmpAns += j - k;
                j++;
            }
        }
        for (k = l; k <= r; k++){
            a[k] = tmpa[k];
        }
        return tmpAns;
    }
}
