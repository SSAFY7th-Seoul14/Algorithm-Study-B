import java.io.*;
import java.util.*;


public class Main {
	
	static int n,m, ans, num;
	static int arr[] = new int[10001];
	static int paramestic(int v)
	{
		int sum = 0;
		for (int i=0; i<n; i++)
		{
			if (arr[i] > v)
				sum += v;
			else
				sum += arr[i];
		}
		return sum;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		for (int i=0; i<=10000; i++)
			arr[i] = Integer.MAX_VALUE;
		for (int i=0; i<n; i++)
			arr[i] = scan.nextInt();
		m = scan.nextInt();
		Arrays.sort(arr);
		
		// 구간 설정은 예산의 최솟값, 예산의 최댓값으로 설정 
		int left = 1;
		int right = arr[n-1];
		
		// 이분 탐색으로 진행 
		while (left <= right)
		{
			int mid = (left + right) / 2;
			int temp = paramestic(mid);
			//System.out.println(mid + " : " + temp);
			if (temp > m)
				right = mid - 1;
			else
			{
				// 예산 중 최대값 찾기 
				for (int i=0; i<n; i++)
				{
					if (arr[i] > mid)
						ans = Math.max(ans, mid);
					else
						ans = Math.max(ans, arr[i]);
				}
				left = mid + 1;
			}
		}
		System.out.println(ans);
	}
}