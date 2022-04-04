import java.io.*;
import java.util.*;


public class Main {
	
	static int n, m, k, ans;
	static int cache[] = new int[100001];
	static int coin[] = new int[100001];
	static int inf = 210000000;
	static int call = 0;
	
	static ArrayList<Integer> arr = new ArrayList<Integer>();
	// 점화식  dp[k] = dp[k - 동전 비용] 중에서 제일 작은 거 + 1
  	// 시간 초과 이유 : 내가 ret을 초기화 한 값이 cache의 초기 상태값이랑 같아서 똑같은 재귀 구문이 반복되는 케이스가 발생
  	// 틀린 이유 : ans가 -1 출력하는 조건 잘못 설정함
  	// 항상 top down dp에선 정답을 1번만 갱신하고 넘어가야한다는 걸 기억하자 


	static int dp(int sum)
	{
		if (cache[sum] != inf)
			return cache[sum];
		int ret = inf-1;
		for (int i=0; i<arr.size(); i++)
		{
			if (sum - arr.get(i) >= 0)
				ret = Math.min(ret, dp(sum  - arr.get(i)) + 1);
		}
		return cache[sum] = ret;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		k = scan.nextInt();	
		
		for (int i=1; i<=100000; i++)
			cache[i] = inf;
		
		int temp;
		for (int i=0; i<n; i++)
		{
			temp = scan.nextInt();
			cache[temp] = 1;
			coin[temp] = 1;
		}
		// 중복 제거해서 arr 배열에 동전 값 넣음 
		for (int i=1; i<=100000; i++)
		{
			if (coin[i] != 0)
				arr.add(i);
		}
		ans = dp(k);
		if (ans > k)
			ans = -1;
		System.out.println(ans);
	}
}
