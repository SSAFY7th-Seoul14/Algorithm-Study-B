package bfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//골드5 https://www.acmicpc.net/problem/16197
//방문체크를 어떻게 해줘야할지 고민함.
public class BOJ16197_두동전 {
	static class Point {
		int x1, y1, x2, y2;

		public Point() {};
		public Point(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		
		
	}
	
	static int N,M;
	static int[][] dxy = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static char[][] map;
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Point coin = new Point();
		int idx = 0;
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'o') {
					if(idx == 0) {
						coin.x1 = i;
						coin.y1 = j;
						idx++;
					}
					else {
						coin.x2 = i;
						coin.y2 = j;
					}
				}
			}
		}
		
		System.out.println(bfs(coin));
	}
	
	private static int bfs(Point coin) {
		Queue<Point> q = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		
		q.add(coin);
		visited[coin.x1][coin.y2][coin.x2][coin.y2] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			
			int size = q.size();
			while(size > 0) {
				coin = q.poll();
				int x1 = coin.x1;
				int y1 = coin.y1;
				int x2 = coin.x2;
				int y2 = coin.y2;
				
				for(int i = 0; i < 4; i++) {
					int nx1 = x1 + dxy[i][0];
					int ny1 = y1 + dxy[i][1];
					int nx2 = x2 + dxy[i][0];
					int ny2 = y2 + dxy[i][1];
					
					int flag = 0;//범위체크
					int block = 0;//벽체크
			
					if(check(nx1, ny1)) {
						flag++;
						if(map[nx1][ny1] == '#') {
							nx1 = x1;
							ny1 = y1;
							block++;
						}
					}
					if(check(nx2,ny2)) {
						flag++;
						if(map[nx2][ny2] == '#') {
							nx2 = x2;
							ny2 = y2;
							block++;
						}
					}
					
					if(flag == 2 && block != 2 && !visited[nx1][ny1][nx2][ny2]) {//block!=2: 둘다 벽이면 이동안하니까
						if(nx1 != nx2 || ny1 != ny2) {//두동전이 겹쳐지면 의미 없음
							q.add(new Point(nx1,ny1, nx2,ny2));
							visited[nx1][ny1][nx2][ny2] = true;
						}
						
					} 
					else if(flag == 1) {//한쪽만 범위안에 있다는 것은 하나는 나갔다는 것임.
						return cnt;
					}
				}
				
				
				size--;
			}
			if(cnt == 10) return -1;
			cnt++;
			
			
		}
		return -1;
	}
	private static boolean check(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= M) return false;
		return true;
		
	}
	
	

}
