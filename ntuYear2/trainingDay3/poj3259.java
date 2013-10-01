import java.io.*;
import java.util.*;
        
class Edge {
    public int e;
    public int l;
    public Edge(int e, int l) {
        this.e = e;
        this.l = l;
    }
}
public class poj3259 {
    public static ArrayList<ArrayList<Edge>> map;
    public static int N;
    public static int[] dist;
    public static int[] time;
    public static boolean[] f;
    
    public static void main(String args[]) throws Exception {
        int T, M, W;
        int s, e, l;
        
        BufferedReader br = 
                //new BufferedReader(new FileReader("poj3259.in"));
                new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        String[] data;
        
        while (T-- > 0) {
            data = br.readLine().split(" ");
            N = Integer.parseInt(data[0]);
            M = Integer.parseInt(data[1]);
            W = Integer.parseInt(data[2]);
            map = new ArrayList<ArrayList<Edge>>();
            for (int i = 0; i < N; i++) {
                ArrayList<Edge> newStart = new ArrayList<Edge>();
                map.add(newStart);
            }
            
            ArrayList<Edge> newStart = new ArrayList<Edge>();
            for (int i = 0; i< N; i++) {
                newStart.add(new Edge(i, 100000));
            }
            map.add(newStart);  
            //set up a virtual point which can
            //array to any point else.
            
            
            for (int i = 0; i < M; i++) {
                data = br.readLine().split(" ");
                s = Integer.parseInt(data[0]);
                e = Integer.parseInt(data[1]);
                l = Integer.parseInt(data[2]);
                Edge edge = new Edge(e - 1, l);
                map.get(s-1).add(edge);
                edge = new Edge(s - 1, l);
                map.get(e-1).add(edge);
            }
            for (int i = 0; i < W; i++) {
                data = br.readLine().split(" ");
                s = Integer.parseInt(data[0]);
                e = Integer.parseInt(data[1]);
                l = Integer.parseInt(data[2]);
                Edge edge = new Edge(e - 1, -l);
                map.get(s-1).add(edge);
            }
            
            spfa();
            //标准spfa的细微变形，爽！
        }
    }
    public static void spfa() {
        int start;
        
        dist = new int[N + 1];
        f = new boolean[N + 1];
        time = new int[N + 1];
        
        for (int i = 0; i < N; i++) {
            dist[i] = 1147483648;
            f[i] = false;
            time[i] = 0;
        }
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(N);
        f[N] = true;
        while (q.peek() != null) {
            start = q.poll();
            for (Edge end : map.get(start)){
                if (dist[start] + end.l < dist[end.e]) {
                    dist[end.e] = dist[start] + end.l;
                    if (!f[end.e]) {
                        q.add(end.e);
                        f[end.e] = true;
                    }
                    time[end.e]++;
                    if (time[end.e] > N) {
                        System.out.println("YES");
                        return;
                    }
                }
            }
            f[start] = false;
        }
        System.out.println("NO");
    }
}