package Algorithms;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class DFSIterative{
	private static ArrayList<Integer>[] adjacencyList;

//	private ListNode[] edges;
	public static void main(String[] args) {
		// this undirected meaning edge between i and j is equal to j and i
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			adjacencyList = new ArrayList[m];
			ArrayList<Integer> answer =  new ArrayList<Integer>();
			boolean[] visited = new boolean[m];

			for (int i = 0; i < m; i++) {
				adjacencyList[i] = new ArrayList<Integer>();
				visited[i] = false;
			}
			for (int index = 0; index < n; index++) {
				int i = sc.nextInt();
				int j = sc.nextInt();
				adjacencyList[i].add(j);
				adjacencyList[j].add(i);
			}
			Stack<Integer> s = new Stack<>();
			int max = 0;
			for (int i = 0; i < adjacencyList.length; i++)
			{
				if (adjacencyList[i].size() > 0)
				{
					ArrayList<Integer> result = dfs(s, visited, i);
					if (result.size() > max)
					{
						max = result.size();
						answer.clear();
						answer.addAll(result);
					}
				}
				
			}
			System.out.println(answer);
		}
	}
	public static ArrayList<Integer> dfs(Stack<Integer> s, boolean [] visited, int origin)
	{
		
		ArrayList<Integer> answer = new ArrayList<>();
		s.push(origin);
		while (!s.isEmpty()) {
			int v = s.pop();
		
			if (!visited[v]) {
				visited[v] = true;
				answer.add(v);
				for (int neigh : adjacencyList[v])
				{
					s.push(neigh);
				}
			}
		}
		return answer;
	}
}
