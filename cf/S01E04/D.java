import java.io.*;
import java.util.*;

public class D {
    public static int[] lEdge, rEdge,edge;
    public static int[] father;
    public static boolean[] fEdge, fNode;
    
    public static int totalCost;
    public static int n, m, c;
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = 
                new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] data;
        
        while (T-- > 0) {
            data = br.readLine().split(" ");
            n = Integer.parseInt(data[0]);
            m = Integer.parseInt(data[1]);
            c = Integer.parseInt(data[2]);
            fNode = new boolean[n];
            fEdge = new boolean[m];
            rEdge = new int[m];
            lEdge = new int[m];
            edge = new int[m];
            
            int numConnectedNode = 0;
                        
            for (int i = 0; i < n; i++) {
                fNode[i] = false;
            }
            for (int i = 0; i < m; i++) {
                fEdge[i] = true;
            }
            
            for (int i = 0; i < m; i++) {
                data = br.readLine().split(" ");
                int l = Integer.parseInt(data[0]) - 1;
                int r = Integer.parseInt(data[1]) - 1;
                int len = Integer.parseInt(data[2]);
                lEdge[i] = l;
                rEdge[i] = r;
                edge[i] = len;
                if (!fNode[l]) {
                    numConnectedNode++;
                    fNode[l] = true;
                }
                if (!fNode[r]) {
                    numConnectedNode++;
                    fNode[r] = true;
                }
            }
            if (m > 1) {
                sort(0, m-1);
            }
            
            if (numConnectedNode == 0) {
                numConnectedNode = 1;
            }
            
            int needLine = kruskal() - 1;
            totalCost = 0;
            int changeLine = 0;
            if (needLine > 0) {
                for (int i = m - 1; i >= 0; i--) {
                    if (edge[i] > c) {
                        break;
                    }
                    
                    if (fEdge[i]) {
                        needLine--;
                        changeLine++;
                        fEdge[i] = false;
                        totalCost += edge[i];
                        if (needLine <= 0) {
                            break;
                        }
                    }
                }
            }
            
            if (needLine > 0){
                totalCost += needLine * c;
            }
            System.out.println(needLine + " " + changeLine + " " + totalCost);
        }
    }
    public static void sort(int l, int r) {
        int i = l;
        int j = r;
        int x = edge[(l + r) / 2];
        int tmp;
        while (true) {
            while (edge[i] > x) i++;
            while (edge[j] < x) j--;
            if (i <= j) {
                tmp = edge[i];
                edge[i] = edge[j];
                edge[j] = tmp;
                tmp = lEdge[i];
                lEdge[i] = lEdge[j];
                lEdge[j] = tmp;
                tmp = rEdge[i];
                rEdge[i] = rEdge[j];
                rEdge[j] = tmp;
                i++;
                j--;
            }
            if (i > j)
                break;
        }
        if (j > l) sort(l, j);
        if (i < r) sort(i, r);
    }
    
    public static int getFather(int x) {
        if (father[x] == x) {
            return x;
        }
        int baba = getFather(father[x]);
        father[x] = baba;
        return baba;
    }
    
    public static int kruskal() {
        father = new int[n];
        int q, p;
        
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        for (int i = 0; i < m; i++) {
            p = getFather(lEdge[i]);
            q = getFather(rEdge[i]);
            if (p != q) {
                fEdge[i] = false;
                father[p] = q;
            }
        }
        HashSet<Integer> hash = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            hash.add(getFather(father[i]));
        }
        
        int counter = 0;
        for (Integer x : hash) {
            counter++;
        }
        return counter;
    }
}
