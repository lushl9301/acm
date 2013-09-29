import java.util.Scanner;

public class P1354 {
	public static void main(String arg[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		VeryLongint out[] = new int[20];
		out[0] = 1;
		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 20; j++) {
				if (out[j] >= 5) {
					out[j] = 2 * (out[j] - 5);
					out[j+1] += 1;
				}
			}
		}
		System.out.println(out);
	}
}

public class VeryLongInt{
	private int value[];
	VeryLongint (int n) {
		value = new int[n];
	}

}
