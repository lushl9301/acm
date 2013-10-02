import java.io.*;
import java.util.*;

public class A {
    public static void main(String args[]) throws Exception{
        int n, m, a;
        BufferedReader br = 
                new BufferedReader(new InputStreamReader(System.in));
        String[] data;
        data = br.readLine().split(" ");
        n = Integer.parseInt(data[0]);
        m = Integer.parseInt(data[1]);
        a = Integer.parseInt(data[2]);
        if (n % a != 0) {
            n = n / a + 1;
        } else {
            n = n / a;
        }
        if (m % a != 0) {
            m = m / a + 1;
        } else {
            m = m / a;
        }
        System.out.println(((long)n)*m);
    }
}
