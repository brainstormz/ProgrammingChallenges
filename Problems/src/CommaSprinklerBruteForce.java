import java.util.*;

public class CommaSprinklerBruteForce {
	public static String filteredToken(String token)
	{
		String t = null;
		if (token.charAt(token.length()-1) == ',' || 
				token.charAt(token.length()-1) == '.')
		{
			t = token.substring(0, token.length()-1);
		}
		else 
		{
			t = token;
		}
		return t;
	}	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> tokens = new ArrayList<>();
		HashSet<String> total = new HashSet<String>();
		ArrayList<String> before = new ArrayList<>();	
		ArrayList<String> after = new ArrayList<>();

		while (sc.hasNext()) {

			String input = sc.nextLine();
			StringTokenizer tokenizer = new StringTokenizer(input);
			while (tokenizer.hasMoreTokens()) {
				tokens.add(tokenizer.nextToken());
			}
			while (true) {
				for (int i = 0; i < tokens.size(); i++) {
					String token = tokens.get(i);
					if (token.charAt(token.length() - 1) == ',') {
						if (!total.contains(token)) {
							total.add(token);
							after.add(token);
						}
						if (i + 1 < tokens.size()) {
							String nextToken = tokens.get(i+1);
							if (!total.contains(nextToken)) {
								total.add(nextToken);
								before.add(nextToken);
							}
						}
					}
				}
			
		
				if (before.size() == 0 && after.size() == 0) {
					break;
				}
				for (int i = 0; i < before.size(); i++) {
					for (int j = 0; j < tokens.size(); j++) {
						
						String filteredToken = filteredToken(tokens.get(j));
						String filteredBefore = filteredToken(before.get(i));
						if (filteredToken.equals(filteredBefore)) {
							if (j - 1 >= 0) {
								String t = tokens.get(j-1);
								
								if (t.charAt(t.length() - 1) != ',' && t.charAt(t.length() - 1) != '.') {
									t = t + ",";
									tokens.set(j-1, t);
									
								}
							}
						}
					}
				}
			
				for (int i = 0; i < after.size(); i++)
				{
					for (int j = 0; j < tokens.size(); j++){
						
						String filteredToken = filteredToken(tokens.get(j));
						String filteredAfter = filteredToken(after.get(i));
						if (filteredToken.equals(filteredAfter))
						{
							String t = tokens.get(j);
							if (t.charAt(t.length() -1) != ',' && t.charAt(t.length() - 1) != '.')
							{
								tokens.set(j,t+",");

							}

							
						}
					}
				}
				before.clear();
				after.clear();
			}
			for (int i = 0; i < tokens.size(); i++) {
				System.out.print(tokens.get(i) + " ");
			}
			

		}
	}
}
