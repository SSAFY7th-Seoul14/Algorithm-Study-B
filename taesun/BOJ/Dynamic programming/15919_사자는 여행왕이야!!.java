import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
	int x,y;
	pair (int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	// 시작 날짜가 작은 순, 그리고 같다면 끝날짜가 작은순으로 소팅
	public int compareTo(pair o) {
		if (this.x - o.x != 0)
			return this.x - o.x;
		else
			return this.y - o.y;
	}
}
public class Main {
	
	static int n, m, k, ans, d;
	static ArrayList<pair> arr = new ArrayList<pair>();
	static int INF = 1000000000;
	static int cache[] = new int[1005];
	static int dist[][] = new int[1005][1005];
	
	static int dp(int idx)
	{
		if (cache[idx] != INF)
			return cache[idx];
		int ret = INF - 1;
		
		// 점화식 
		// dp[n] = n번째 여행을 마지막으로 하는 여행간격의 최솟값 
		// max(dp[i], dist[i][n]) =>
		// i까지 각 루트별 여행간격의 최댓값중 제일 작은거, i번째 여행에서 나까지 간격 
		// 둘중  최대를 고르면 n까지 오는 루트중 가장 긴 여행 간격을 찾은 것 
		// 그리고 루트가 여러개니까 이중에서 최소를 찾아서 dp[n]에 갱신 
		for (int i = 0; i < idx; i++)
			ret = Math.min(ret, Math.max(dp(i) , dist[i][idx]));
		return cache[idx] = ret;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		int a,b;
		
		// 여행시작일과 종료일을 묶어서 저장 
		for (int i=0; i<m; i++)
		{
			a = scan.nextInt();
			b = scan.nextInt();
			arr.add(new pair(a, b));
		}
		// 여행을 1번 가는 경우도 고려해 임계값을 넣어준다
		arr.add(new pair(0,0));
		arr.add(new pair(n + 1, n + 1));
		Collections.sort(arr);
		
		// 각 여행마다 종료일 - 시작일간 차이값을 구해서 dist 배열에 저장한다 
		// 여행일이 겹치면 갈 수 없으므로 매우 큰 값을 넣어둔다 
		int x1, y1, x2, y2;
		for (int i = 0; i < m + 2; i++)
		{
			for (int j = i; j < m + 2; j++)
			{
				if (i == j)
					dist[i][j] = INF - 1;
				x1 = arr.get(i).x;
				y1 = arr.get(i).y;
				x2 = arr.get(j).x;
				y2 = arr.get(j).y;
				// 동시 선택 불가능하면 거리차를 INF - 1라고 하자
				if (y1 < x2)
					dist[i][j] = x2 - y1 - 1;
				else if (x1 <= x2 && x2 <= y1)
					dist[i][j] = INF-1;
			}
		}
	
		// dp table 초기화 
		for (int i = 0; i < m + 2; i++)
			cache[i] = INF;
		cache[0] = 0;
		
		ans = dp(m + 1);
		System.out.println(ans);
	}
}
