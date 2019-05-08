// Arup Guha
// 4/19/2018
// Solution to 2018 WF Problem B: Comma Sprinkler

import java.util.*;

public class CommaSprinkler2 {

	public static String s;
	public static HashMap<String, Integer> lookup;
	public static ArrayList[] before;
	public static ArrayList[] after;

	public static int n;
	public static String[] toks;
	public static boolean[] comma;
	public static boolean[] period;
	public static int[] idlist;

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		s = stdin.nextLine();

		StringTokenizer tok = new StringTokenizer(s);
		n = tok.countTokens();
		int id = 0, i = 0;
		lookup = new HashMap<String,Integer>();

		toks = new String[n];
		comma = new boolean[n];
		period = new boolean[n];
		idlist = new int[n];

		// Read in each token seeing if it ends in , or .
		while (tok.hasMoreTokens()) {

			// Process this string.
			String tmp = tok.nextToken();
			int len = tmp.length();
			System.out.println("current token = " + tmp);
			if (tmp.charAt(len-1) == '.') {
				period[i] = true;
				toks[i] = tmp.substring(0, len-1);
			}
			else if (tmp.charAt(len-1) == ',') {
				comma[i] = true;
				toks[i] = tmp.substring(0, len-1);
			}
			else toks[i] = tmp;

			// Add to my map.
			if (!lookup.containsKey(toks[i])) lookup.put(toks[i], id++);
			

			// Store and go to next item.
			idlist[i] = lookup.get(toks[i]);
			System.out.println("period = " + period[i]);
			System.out.println("toks = " + toks[i]);
			System.out.println("comma = " + comma[i]);
			System.out.println("lookup = " + lookup);
			System.out.println("i = " + i);
//			System.out.
			System.out.println("idlist[i] = " + idlist[i]);
			System.out.println("id = " + (id-1));
			System.out.println();
			i++;
			
			System.out.println("idlist");
			for (int i1 = 0; i1 < idlist.length; i1++)
			{
				System.out.println("at i " + i1 +  " : " + idlist[i1]);
			}
			System.out.println();
		}
//		please sit spot. sit spot, sit. spot here now here.
		// Set these up.
		before = new ArrayList[id];
		for (i=0; i<id; i++)
			before[i] = new ArrayList<Integer>();
		after = new ArrayList[id];
		for (i=0; i<id; i++)
			after[i] = new ArrayList<Integer>();

		// Form our graph.
		for (i=0; i<n-1; i++) {
			if (period[i]) continue;
			after[idlist[i]].add(idlist[i+1]);
			before[idlist[i+1]].add(idlist[i]);
			System.out.println("after : ");
			for (int j = 0 ; j < after.length; j++)
			{
				
				System.out.println("at " + j + " -> " + after[j]);
			}
			System.out.println("before : ");
			for (int j = 0 ; j < before.length; j++)
			{
				System.out.println("at " + j + " -> " + before[j]);
			}
			
		}
		
		

		boolean[] usedA = new boolean[id];
		boolean[] usedB = new boolean[id];

		// Run our DFS.
		for (i=0; i<n; i++) {
			if (comma[i]){
				dfs(usedA, usedB, idlist[i], true);
				dfs(usedA, usedB, idlist[i+1], false);
			}
		}

		// Add commas.
		for (i=0; i<n; i++) {
			if (comma[i] || period[i]) continue;
			if (usedA[idlist[i]]) comma[i] = true;
			if (i<n-1 && usedB[idlist[i+1]]) comma[i] = true;
		}
		
		// Put together.
		StringBuffer sb = new StringBuffer();
		for (i=0; i<n-1; i++) {
			sb.append(toks[i]);
			if (comma[i]) sb.append(",");
			if (period[i]) sb.append(".");
			sb.append(" ");
		}
		sb.append(toks[n-1]+".");

		// Ta da!
		System.out.println(sb);
	}

	public static void dfs(boolean[] usedA, boolean[] usedB, int v, boolean dir) {

		// Mark it
		if (dir) 	usedA[v] = true;
		else		usedB[v] = true;

		// Recursively mark neighbors.
		ArrayList<Integer> next = dir ? (ArrayList<Integer>)after[v] : (ArrayList<Integer>)before[v];
		boolean[] used = dir ? usedB : usedA;
		for (Integer x: next)
			if (!used[x])
				dfs(usedA, usedB, x, !dir);
	}
}