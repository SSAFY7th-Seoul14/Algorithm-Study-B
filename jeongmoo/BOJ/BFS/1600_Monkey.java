import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1600번. 말이 되고픈 원숭이
public class BOJ1600_Monkey {
	static class Monkey {
		int x, y, cnt; // cnt : 남은 점프 카운트

		public Monkey(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	static int k, w, h;
	static int[][] map;
	
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	
	static int hdx[] = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int hdy[] = {-2, -1, 1, 2, -2, -1, 1, 2};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		
		// 입력
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처리
		bfs();
	}
	
	public static void bfs() {
		Queue<Monkey> q = new LinkedList<>();
		boolean[][][] isVisited = new boolean[h][w][k+1];
		
		Monkey start = new Monkey(0, 0, k);
		q.offer(start);
		isVisited[0][0][0] = true;
		
		int time = -1;
		while(!q.isEmpty()) {
			time++;			
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Monkey monkey = q.poll();
				if (monkey.x == h-1 && monkey.y == w-1) {
					System.out.println(time);
					return;
				}
				
				if (monkey.cnt > 0) {
					for (int j = 0; j < 8; j++) {
						int nx = monkey.x + hdx[j];
						int ny = monkey.y + hdy[j];
						
						if(nx<0 || ny<0 || h<=nx || w<=ny || map[nx][ny] == 1)
							continue;
						
						if(!isVisited[nx][ny][monkey.cnt-1]) {
							q.offer(new Monkey(nx, ny, monkey.cnt-1));
							isVisited[nx][ny][monkey.cnt-1] = true;
						}
					}
				}
				
				for (int j = 0; j < 4; j++) {
					int nx = monkey.x + dx[j];
					int ny = monkey.y + dy[j];
					
					if(nx<0 || ny<0 || h<=nx || w<=ny || map[nx][ny] == 1)
						continue;
					
					if(!isVisited[nx][ny][monkey.cnt]) {
						q.offer(new Monkey(nx, ny, monkey.cnt));
						isVisited[nx][ny][monkey.cnt] = true;
					}
				}
			}
		}
		
		System.out.println("-1");
	}
}