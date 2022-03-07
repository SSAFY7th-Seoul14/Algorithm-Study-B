import java.io.*;
import java.util.*;

public class Main {
	static int n, ans;
	static int wine[] = new int[10001];
	static int cache[] = new int[10001];
	// 2차원으로 cache 마다 연속으로 몇개 마셨는지 상태 나타내면서 푸는 방법도 있음
	// 계단 오르기 문제와 다른점 : 마지막 잔을 반드시 마실 필요가 없음, 따라서 마지막을 안마시는 
	// cache[i] = max(cache[i-1] , cache[i]); 경우를 비교해줘야됨
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		for (int i = 0; i < n; i++)
			wine[i] = scan.nextInt();
		// 미리 초기화 
		cache[0] = wine[0];
		cache[1] = Math.max(wine[0], wine[0] + wine[1]);
		int a = wine[0] + wine[1];
		int b = wine[1] + wine[2];
		int c = wine[0] + wine[2];
		cache[2] = Math.max(Math.max(a, b), c);
		for (int i = 3; i < n; i++)
		{
			cache[i] = Math.max(cache[i-2]+ wine[i], cache[i-3]+ wine[i-1]+ wine[i]);
	        cache[i] = Math.max(cache[i-1] , cache[i]);
		}
		for(int i=0;i<n;i++)
	    {
	        if(cache[i]>= ans)
	            ans= cache[i];
	    }
		System.out.println(ans);
	}
}
