import java.io.*;
import java.util.*;


public class Main {
	
	static long n, k, ans;
	static int arr[] = new int[100001];
	
	public static long paramestic(long mid)
	{
		// mid 보다 작거나 같은 i * j의 개수를 구해야함
		// i * j <= mid 라면  j <= mid / i 도 가능   
		// 이걸 만족하는 j의 개수를 구하자 
		long ret = 0;
		for (long i = 1; i<= n; i++)
			ret += Math.min(mid / i , n);  //min 안해주면 나중에 n을 넘어가는 경우가 생김 
		return ret;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		k = scan.nextInt();
		
		
		// 임의의 수 a를 지정했을때 파라메트릭 서치를 해서 개수를 센다 
		long left = 1;
		long right = n * n;
		long mid, cnt;
		while (left <= right)
		{
			mid = (left + right) / 2;
			cnt = paramestic(mid);
			// 중복되는 수가 많아지면  cnt == k로 나눠떨어지지 않을 수가 있다 
			// 그래서 점점 줄여나가는 방향으로 가야함 
			if (cnt >= k)
			{
				ans = mid;
				right = mid - 1;
			}
			else
				left  = mid + 1;
		}
		System.out.println(ans);
	}
}
