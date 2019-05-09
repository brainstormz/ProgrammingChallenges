import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Terraces {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int[][] terrace2D = new int[x][y];
		int [] terrace1D = new int[x*y];
		ArrayList<Integer>[] adjList = new ArrayList[x * y];
		
		int j = 0;
		for (int yi = 0; yi < y; yi++) {
			st = new StringTokenizer(bf.readLine());
			for (int xi = 0; xi < x; xi++) {
				terrace2D[xi][yi] = Integer.parseInt(st.nextToken());
				terrace1D[j] = terrace2D[xi][yi];
				j++;
			}
		}
		int i = 0;
		for (int yi = 0; yi < y; yi++) {
			for (int xi = 0; xi < x; xi++) {
				if (adjList[i] == null)
					adjList[i] = new ArrayList<Integer>();
				// top
				if (yi - 1 >= 0 && terrace2D[xi][yi] >= terrace2D[xi][yi - 1]) {
					adjList[i].add(i - x);
					
				}
				// right
				if (xi + 1 < x && terrace2D[xi][yi] >= terrace2D[xi + 1][yi]) {
					adjList[i].add(i + 1);
				}
				// bottom
				if (yi + 1 < y && terrace2D[xi][yi] >= terrace2D[xi][yi + 1]) {
					adjList[i].add(i + x);
				}
				// left
				if (xi - 1 >= 0 && terrace2D[xi][yi] >= terrace2D[xi - 1][yi]) {
					adjList[i].add(i - 1);
				}
				i++;
			}
		}
//		System.out.println(Arrays.deepToString(adjList));
		HashSet<Integer> reachableNode = new HashSet<Integer>();
		for (int src = 0; src < x * y; src++) {
			boolean[] visited = new boolean[x * y];
			Stack <Integer> stack = new Stack<Integer>();
			LinkedList<Integer> queue = new LinkedList<Integer> ();
//			stack.push(src);
			queue.push(src);
			visited[src] = true;
			
			reachableNode.addAll(DFS(adjList, queue, visited, src, terrace1D));
			
		}
		System.out.println(reachableNode.size());
		

	}

	public static HashSet<Integer> DFS(ArrayList<Integer>[] adjList, LinkedList<Integer> queue, boolean[] visited, int source, int [] terrace1D) {
		HashSet<Integer> result = new HashSet<Integer>();
		while(!queue.isEmpty())
		{
			int v = queue.pop();
//			System.out.println(v);
			
			if (terrace1D[source] != terrace1D[v])
			{
				result.clear();
//				System.out.println("break");
				break;
			}
			result.add(v);
			
//			int v = stack.pop();			
//			if (!visited[v])
//			{
//				visited[v] = true;
//				if (terrace1D[source] != terrace1D[v])
//				{
//					result.clear();
//					break;
//				}
//				result.add(v);
//				for (int neigh : adjList[v])
//				{
//					stack.push(neigh);
//				}
//			}
			for (int neigh : adjList[v])
			{
				if (!visited[neigh])
				{
					visited[neigh] = true;
					queue.addLast(neigh);	
				}
						
			}
		}
//		System.out.println();
		return result;
	}
}