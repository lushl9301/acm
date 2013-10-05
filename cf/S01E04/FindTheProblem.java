import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class FindTheProblem {

	public static int noOfCases;
	public static int noOfRows;

	public static String first[];
	public static String second[];
	public static String third[];

	public static TreeSet<String> firstTS;
	public static TreeSet<String> secondTS;
	public static int totalC;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		noOfCases = Integer.parseInt(br.readLine());

		for (int z = 0; z < noOfCases; z++) {	// for each case
			noOfRows = Integer.parseInt(br.readLine());
			first = new String[noOfRows];
			second = new String[noOfRows];
			third = new String[noOfRows];
			
			String[] data;

			for (int i = 0; i < noOfRows; i++) {	// for each line
				//br.readLine();
				data = br.readLine().split(" ");

				if (data.length == 2) {
					first[i] = data[0];					
					second[i] = data[1];
					third[i] = "   ";
				}
				else {
					first[i] = data[0];					
					second[i] = data[1];
					third[i] = data[2];
				}

				
				if (third[i].length() == 1) {
					third[i] += "  ";
				}
				else if (third[i].length() == 2) {
					third[i] += " ";
				}

				if (first[i].length() == 1) {
					first[i] += "  ";
				}
				else if (first[i].length() == 2) {
					first[i] += " ";
				}

				if (second[i].length() == 1) {
					second[i] += "  ";
				}
				else if (second[i].length() == 2) {
					second[i] += " ";
				}
			}

			for (int i = 1; i < noOfRows; i++) {	// sort
				for (int j = i; j > 0; j--) {
					if (first[j].compareTo(first[j-1]) < 0) {
						String tempFirst = first[j];
						first[j] = first[j-1];
						first[j-1] = tempFirst;

						String tempSecond = second[j];
						second[j] = second[j-1];
						second[j-1] = tempSecond;

						String tempThird = third[j];
						third[j] = third[j-1];
						third[j-1] = tempThird;
					} else if (first[j].compareTo(first[j-1]) == 0) {
						if (second[j].compareTo(second[j-1]) < 0) {
								String tempFirst = first[j];
								first[j] = first[j-1];
								first[j-1] = tempFirst;

								String tempSecond = second[j];
								second[j] = second[j-1];
								second[j-1] = tempSecond;

								String tempThird = third[j];
								third[j] = third[j-1];
								third[j-1] = tempThird;
							}
					}
				}
			}

			
			firstTS = new TreeSet<String>();
			secondTS = new TreeSet<String>();

			for (int i = 0; i < noOfRows; i++) {
				firstTS.add(first[i]);
				secondTS.add(second[i]);
			}

			totalC = secondTS.size();


			printiii();
			System.out.print("|   |");
			

			Iterator<String> firstI = firstTS.iterator();
			Iterator<String> secondI = secondTS.iterator();
			
			while (secondI.hasNext()) {
				System.out.print(secondI.next() + "|");
			}
			System.out.println();
			secondI = secondTS.iterator();
			

			String nowR = new String();
			String nowC = new String();
			int count = 0;
			while (firstI.hasNext()) {

				printiii();

				nowR = (String) firstI.next();
				System.out.print("|" + nowR + "|");
				while (secondI.hasNext()) {
					nowC = (String) secondI.next();
					if (count < noOfRows && nowC.compareTo(second[count]) == 0) {
						System.out.print(third[count++] + "|");
					}
					else {
						System.out.print("   |");
					}
				}
				secondI = secondTS.iterator();
				System.out.println();
			}
			printiii();
		}
	}




	public static void printiii() {
		System.out.print("+---+");

		for (int i = 0; i < totalC; i++) {
			System.out.print("---+");
		}
		System.out.println();
	}
}
