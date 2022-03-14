import java.io.*;
import java.util.*;

public class Main {
	static int ans, n, m;
	static boolean visit[] = new boolean[501];
	static boolean input[] = new boolean[501];
	static ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
	
	static boolean dfs(int i, int p)
	{
		if (!visit[i])  // 처음 방문
			visit[i] = true;
		else           // 재방문 -> 사이클이거나 경로가 2개 
			return false;
		if (arr.get(i).size() == 0)
			return true;
		boolean flag = true;
		int temp;
		for (int j = 0; j < arr.get(i).size(); j++)
		{
			temp = arr.get(i).get(j);
			if (temp != p)
			{
				if (dfs(temp, i) == false)
				{
					flag = false;
					return flag;
				}
			}
		}
		return flag;
	}



	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		int a , b, t = 0;
		for (int i=0; i<501; i++)
			arr.add(new ArrayList<Integer>());
		while (true)
		{
			ans = 0;
			t++;
			n = scan.nextInt();
			m = scan.nextInt();
			if (n == 0 && m == 0)
				return;
			for (int i = 0; i < m; i++)
			{
				a = scan.nextInt();
				b = scan.nextInt();
				if (a == 0 && b == 0)
					return;
				arr.get(a).add(b);
				arr.get(b).add(a);
			}
			for (int i = 1; i <= n; i++)
			{
				if (!visit[i])
				{
					if (dfs(i, 0) == true)
						ans++;
				}
			}
			System.out.print("Case " + t + ": ");
			if (ans > 1)
				System.out.print("A forest of " + ans + " trees.\n");
			else if (ans == 1)
				System.out.print("There is one tree.\n");
			else
				System.out.print("No trees.\n");
			for (int i = 0; i < 501; i++)
			{
				arr.get(i).clear();
				visit[i] = input[i] = false;
			}
		}
	}
}