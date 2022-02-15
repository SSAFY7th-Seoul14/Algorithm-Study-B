import java.io.*;
import java.util.*;

public class Main {
	
	static int n, ans = Integer.MAX_VALUE, limit;
	static int s[][] = new int[21][21];
	static boolean select[] = new boolean[21];
	static void backtracking(int idx, int cnt)
	{
		
		if (idx == n)
		{
			int start = 0, end = 0;
			ArrayList<Integer> arr1 = new ArrayList<Integer>();
			ArrayList<Integer> arr2 = new ArrayList<Integer>();
			for (int i=0; i<n; i++)
			{
				if (select[i])
					arr1.add(i);
				else
					arr2.add(i);
			}
			if (arr1.size() != arr2.size())
				return;
			for (int i=0; i< n/2; i++)
			{
				for (int j=0; j<n/2; j++)
				{
					start += s[arr1.get(i)][arr1.get(j)];
					end += s[arr2.get(i)][arr2.get(j)];
				}
			}
			ans = Math.min(ans, Math.abs(start - end));
			return;
		}
		select[idx] = true;
		backtracking(idx + 1, cnt + 1);
		select[idx] = false;
		backtracking(idx + 1, cnt);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
				s[i][j] = scan.nextInt();
		}
		limit = n/2;
		backtracking(0, 0);
		System.out.println(ans);
		return;
	}	
}
