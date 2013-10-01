package poj1033;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int size, num, used, move, minMove;
	static int[] file, cluster, location;
	static List<Integer> path, minPath;
	
	static void solve() {
		// capture
		int currentMove = move;
		
		// move all possible one way move.
		int i = 1;
		while (i < used) {
			if (cluster[i] == 0) {
				int source = location[i];
				cluster[source] = 0;
				cluster[i] = i;
				location[i] = i;
				path.add(source);
				path.add(i);
				move++;
				if (source < i) {
					i = source;
					continue;
				}
			}
			i++;
		}
		
		// check whether have done
		boolean done = true;
		for (i = 1; i < used; i++) {
			if (cluster[i] != i) {
				done = false;
				break;
			}
		}
		
		if (done) {
			if (move < minMove) {
				minMove = move;
				minPath = new ArrayList<Integer>(path);
			}
		} else {
			// do search uncertain nodes
			for (i = 1; i < used; i++) {
				if (location[i] != i) { // cluster[i] should be something.
					int target = size;
					while (cluster[target] != 0) {
						target--;
					}
					int originalCluster = cluster[i];
					location[originalCluster] = target;
					cluster[target] = i;
					cluster[i] = 0;
					path.add(i);
					path.add(target);
					move++;
					solve();
					move--;
					path.remove(2*move+1);
					path.remove(2*move);
					location[originalCluster] = i;
					cluster[target] = 0;
					cluster[i] = originalCluster;
				}
			}
		}
		
		// return
		while (move > currentMove) {
			move--;
			int target = path.get(2*move+1);
			int source = path.get(2*move);
			cluster[target] = 0;
			cluster[source] = target;
			location[target] = source;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		size = scanner.nextInt();
		num = scanner.nextInt();
		
		file = new int[num];
		cluster = new int[size+1];
		location = new int[size+1];
		move = 0;
		
		used = 1;
		int tempLocation;
		for (int i = 0; i < num; i++) {
			file[i] = scanner.nextInt();
			for (int j = 0; j < file[i]; ++j) {
				tempLocation = scanner.nextInt();
				cluster[tempLocation] = used + j;
				location[used + j] = tempLocation;
			}
			used += file[i];
		}
		minMove = used * used;
		path = new ArrayList<Integer>();
		minPath = new ArrayList<Integer>();
		solve();
		if (minMove == 0) {
			System.out.println("No optimization needed.");
		} else {
			for (int i = 0; i < minMove; i++) {
				System.out.println("" + minPath.get(2*i) + " " + minPath.get(2*i+1));
			}
		}
		scanner.close();
	}
}