import java.util.*;
import java.io.*;

public class C {
    public static void main(String args[]) throws Exception {
        BufferedReader br = 
                new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] data;
        while (T-- > 0) {
            data = br.readLine().split(" ");
            int n = Integer.parseInt(data[0]);
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += Integer.parseInt(data[i]);
            }
            if (sum % n == 0) {
                System.out.println("Sepehr");
            } else {
                System.out.println("Sohrab");
            }
        }
    }
}