
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Brexit1 {
	private static LinkedList<Integer>[] adjacencyList;

//	private ListNode[] edges;
	public static void main(String[] args) throws IOException {
		// this undirected meaning edge between i and j is equal to j and i
//		Scanner sc = new Scanner(System.in);

		//
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		//

			int C = Integer.parseInt(st.nextToken()) + 1;
			int P = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			if (X == L)
			{
				System.out.println("leave");
				return;
			}
			adjacencyList = new LinkedList[C];
			ArrayList<Integer> answer = new ArrayList<Integer>();
			boolean[] visited = new boolean[C];
			boolean[] deleted = new boolean[C];

//			for (int i = 0; i < C; i++) {
//				adjacencyList[i] = new LinkedList<Integer>();
//				visited[i] = false;
//				deleted[i] = false;
//			}
			for (int index = 0; index < P; index++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				if (adjacencyList[i] == null) {
					adjacencyList[i] = new LinkedList<Integer>();
				}
				if (adjacencyList[j] == null) {
					adjacencyList[j] = new LinkedList<Integer>();
				}
				adjacencyList[i].add(j);
				adjacencyList[j].add(i);
			}
			LinkedList<Integer> list = new LinkedList<>();

			int max = 0;
			deleted[L] = true;
//			br.reset();
			boolean[] d = bfs(list, deleted, visited, L);
//			System.out.println("hello");
//			System.out.println(Arrays.toString(d));
			if (d[X]) {
				System.out.print("leave");
			} else {
				System.out.print("stay");
			}

		}
	

	public static boolean[] bfs(LinkedList<Integer> list, boolean[] deleted, boolean[] visited, int origin) {
//		ArrayList<Integer> answer = new ArrayList<>();

		list.push(origin);
		visited[origin] = true;
		while (!list.isEmpty()) {
			int v = list.pop();
//			System.out.print(v + " ");
			int deletedNeigh = 0;
//			answer.add(v);
			for (int neigh : adjacencyList[v]) {

				if (!visited[neigh]) {
					visited[neigh] = true;
					list.addLast(neigh);
				}
				if (deleted[neigh]) {
					deletedNeigh++;
				}
			}

			if (deletedNeigh * 2 >= adjacencyList[v].size() && deleted[v] == false) {
				deleted[v] = true; // it's deleted
				boolean[] visitedR = new boolean[visited.length];
				LinkedList<Integer> listR = new LinkedList<>();
				for (int n : adjacencyList[v]) {
					list.addLast(n);
				}
			}
		}
		
		return deleted;
	}
}
