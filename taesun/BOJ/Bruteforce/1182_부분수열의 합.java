import java.io.*;
import java.util.*;

public class Main {
	
	static int arr[] = new int[21];
	static boolean visit[] = new boolean[21];
	static int n, s , ans;
	static void brute(int idx, int select)
	{
		if (idx == n)
		{
			int sum = 0;
			for (int i=0; i<n; i++)
			{
				if (visit[i])
					sum += arr[i];
			}
			// 아예 안고르는 경우는 없다 
			if (sum == s && select > 0)
				ans++;
			return;
		}
		visit[idx] = true;
		brute(idx + 1, select + 1);
		visit[idx] = false;
		brute(idx + 1, select);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		s = scan.nextInt();
		for (int i=0; i<n; i++)
			arr[i] = scan.nextInt();
		Arrays.sort(arr, 0, n-1);
		brute(0,0);
		System.out.println(ans);
		return;
	}	
}
