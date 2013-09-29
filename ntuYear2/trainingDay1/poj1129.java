import java.util.*;

public class poj1129 {

    public static int n;
    public static boolean map[][];
    public static int color[];
    public static int remain;
    public static int maxCol;
    
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        while (n != 0) {
            init();
            color = new int[n];
            for (int i = 0; i < n; i++) {
                color[i] = 0;
            }
            for (int i = 0; i < n; i++) {
                if (color[i] == 0) {
                    for (int col = 1; ; col++) {
                        boolean flag = true;
                        for (int j = 0; j < n; j++) {
                            if (map[i][j] && (color[j] == col)) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            color[i] = col;
                            if (col > maxCol) {
                                maxCol = col;
                            }
                            break;
                        }
                    }
                }
            }
            if (maxCol == 1) {
                System.out.println("1 channel needed.");
            } else {
                System.out.println(maxCol + " channels needed.");
            }
            
            n = sc.nextInt();
        }
    }
    public static void init() {
        Scanner scc = new Scanner(System.in);
        map = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = false;
            }
        }
               
        maxCol = 1;
        for (int i = 0; i < n; i++) {
            String st = scc.nextLine();
            System.out.println("asdf"+st);
            
            int len = st.length();
            int now = st.charAt(0) - 'A';
            for (int j = 2; j < len; j++) {
                map[now][st.charAt(j) - 'A'] = true;
            }
        }
    }
}