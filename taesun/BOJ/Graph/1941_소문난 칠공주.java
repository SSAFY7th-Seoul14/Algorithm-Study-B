import java.io.*;
import java.util.*;

class pair{
	int x, y;
	pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}
/*
푸는 방법 : 25c7의 조합을 모두 뽑아보면서 다솜파가 4이상인 경우만 bfs를 돌림
bfs를 돌려서 7개의 점에 도달 가능하다면 모두 인접하다는 뜻이므로 정답 갱신
처음에 25c7을 먼저 계산해보고 접근했으면 금방했을텐데 계속 벽 부수고 이동하기 처럼 풀려고 한게 문제
 */
public class Main {
	
	static int n,m, ans, num;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int arr[][] = new int[6][6];
	static boolean select[] = new boolean[26];
	static void bfs(int sx, int sy)
	{
		boolean visit[][] = new boolean[5][5];
		for (int i=0; i<5; i++)
		{
			for (int j=0; j<5; j++)
				visit[i][j] = true;
		}
		for (int i=0; i<25; i++)
		{
			if (select[i])
				visit[i / 5][i % 5] = false;
		}
		int cnt = 1;
		Queue<pair> q = new LinkedList<pair>();
		visit[sx][sy] = true;
		q.add(new pair(sx, sy));
	
		int x,y,nx, ny;
		while (!q.isEmpty())
		{
			x = q.peek().x;
			y = q.peek().y;
			q.poll();
			for (int i=0; i<4; i++)
			{
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
					continue;
				if (visit[nx][ny])
					continue;
				visit[nx][ny] = true;
				cnt++;
				q.add(new pair(nx, ny));
			}
		}
		if (cnt == 7)
			ans++;
		
	}
	// 뽑은 사람 수 , 뽑은 다솜파 수 
	static void backtracking(int start, int cnt)
	{
		// 모든 좌표를 뽑은 경우 bfs를 이용해 인접한지 체크해준다
		if (cnt == 0)
		{
			int x,y, c1 = 0, c2 = 0, sx = 0, sy = 0;
			for (int i=0; i<25; i++)
			{
				x = i / 5;
				y = i % 5;
				if (!select[i])
					continue;
				sx = x;
				sy = y;
				if (arr[x][y] == 0)
					c1++;
				else
					c2++;
			}
			if (c1 > c2)
				return;
			bfs(sx, sy);
			return;
		}
		// 2차원을 돌면서 모든 경우를 뽑는다 
		// 총 25 c 7
		for (int i = start; i<25; i++)
		{
			select[i] = true;
			backtracking(i + 1, cnt - 1);
			select[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		for (int i=0; i<5; i++)
		{
			String s = scan.next();
			for (int j=0; j<5; j++)
			{
				if (s.charAt(j) == 'S')
					arr[i][j] = 1;
			}
		}
		backtracking(0,7);
		System.out.println(ans);
	}
}