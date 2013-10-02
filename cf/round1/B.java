import java.util.*;
import java.io.*;
import java.util.regex.Pattern;


public class B {

    public static void main(String args[]) throws Exception {
        int T;
        BufferedReader br = 
                new BufferedReader(new InputStreamReader(System.in));
        Pattern p1 = Pattern.compile("^[A-Z][0-9]");
        Pattern p2 = Pattern.compile("^[R][0-9][C][0-9]");
        //Matcher m1 = p1.matcher
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String line = br.readLine();
            if (line.matches("[A-Z]+[0-9]+")) {
                String number = line.split("^[A-Z]+")[1];
                String word = line.split("[0-9]+")[0];
                int l = word.length();
                int col = 0;
                while (l > 0) {
                    col = col*26 + (word.charAt(word.length() - l) - 'A' + 1);
                    l--;
                }
                System.out.println("R" + number + "C" + col);
                
            } else {
                String co = (line.split("^[R][0-9]+")[1]).split("^[C]")[1];
                String ro = (line.split("[C][0-9]")[0]).split("^[R]")[1];
                int col = Integer.parseInt(co);
                String ansCol = "";
                while (col > 0) {
                    if (col % 26 == 0) {
                        ansCol = (char)(64 + 26) +ansCol;
                        col -= 1;
                    } else {
                        ansCol = (char)(64 + col % 26) + ansCol;
                    }
                    col /= 26;
                }
                System.out.println(ansCol + ro);
            }
        }
    }
}