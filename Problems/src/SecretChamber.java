import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class SecretChamber {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		ArrayList<String> letterToTranslate = new ArrayList<>();
		ArrayList<String> translatedLetter = new ArrayList<>();
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		HashMap<Character, LinkedList<Character>> adjacentList = new HashMap<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			char l1 = st.nextToken().charAt(0);
			char l2 = st.nextToken().charAt(0);

			LinkedList<Character> buff = adjacentList.get(l1);
			if (buff == null) {
				buff = new LinkedList<Character>();

			}
			buff.add(l2);
			adjacentList.put(l1, buff);
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			letterToTranslate.add(st.nextToken());
			translatedLetter.add(st.nextToken());
		}

		for (int wordIndex = 0; wordIndex < n; wordIndex++) {
			String firstWord = letterToTranslate.get(wordIndex);
			String secondWord = translatedLetter.get(wordIndex);
//			System.out.println(firstWord + " , " + secondWord);
			if (firstWord.length() != secondWord.length()) {
				System.out.println("no");
				continue;
			}
			boolean answer = true;
			for (int charIndex = 0; charIndex < firstWord.length(); charIndex++) {
				char origin = firstWord.charAt(charIndex);
				char destination = secondWord.charAt(charIndex);

				LinkedList<Character> queue = new LinkedList<>();
				HashMap<Character, Boolean> visited = new HashMap<>();
				queue.addLast(origin);				
				if(BFS(adjacentList, visited, queue, origin, destination))
				{
					answer = answer & true;
				}
				else 
				{
					answer = answer & false;
				}

				// BFS

			}
			if (answer)
			{
				System.out.println("yes");
			}
			else
			{
				System.out.println("no");
			}
		}
	}

	public static boolean BFS(HashMap<Character, LinkedList<Character>> adjacentList, HashMap<Character, Boolean> visited,
			LinkedList<Character> queue, char origin, char destination) {
		while (!queue.isEmpty()) {
			char currentVertex = queue.pop();
			if (currentVertex == destination) {
				return true;
			}
			if (adjacentList.get(currentVertex) != null)
			{
				for (char neigh : adjacentList.get(currentVertex)) {
					if (visited.get(neigh) == null)
					{
						visited.put(neigh, true);
						queue.addLast(neigh);
					}
				}
			}
		}
		return false;
	}

}
