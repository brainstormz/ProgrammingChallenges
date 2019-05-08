import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Terraces{
	public static void main (String [] args) throws IOException
	{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int [][] terrace = new int [x][y];
		int [][] adjMatrix = new int[x][y];
//		HashMap<<Key1, Key2>,>
		for (int yi = 0; yi < y; yi++)
		{
			st = new StringTokenizer(bf.readLine());
			for (int xi = 0; xi < x; xi++)
			{
				terrace[xi][yi] = Integer.parseInt(st.nextToken());
			}
		}	
		
		for (int yi = 0; yi < y; yi++)
		{
			for (int xi = 0; xi < x; xi++)
			{
				// top
				if (yi - 1 > 0)
				{
					
				}
				// right
				if (xi + 1 < x)
				{
					
				}
				// bottom
				if (yi+1 < y)
				{
					
				}
				// left
				if (xi - 1 > 0)
				{
					
				}
			}
		}		
	}
}