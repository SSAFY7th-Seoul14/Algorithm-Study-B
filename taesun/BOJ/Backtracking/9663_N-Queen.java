import java.io.*;
import java.util.*;


public class Main {
	
	static int arr[] = new int[20];
	static int n, ans;
	static boolean isPossible(int index)
	{
		for (int i=0; i<index; i++)
		{
			if (Math.abs(arr[i] - arr[index]) == Math.abs(i - index))
				return false;
			if (arr[i] == arr[index])
				return false;
		}
		return true;
	}
	static void backtracking(int idx)
	{
		if (idx == n - 1)
		{
			ans++;
			return;
		}
		for (int i=0; i < n; i++)
		{
			arr[idx + 1] = i;
			if (isPossible(idx + 1))
				backtracking(idx + 1);
			else
				arr[idx + 1] = -1;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		for (int i=0; i<n; i++)
		{
			arr[0] = i;
			backtracking(0);
		}
		System.out.println(ans);
		return;
	}	
}