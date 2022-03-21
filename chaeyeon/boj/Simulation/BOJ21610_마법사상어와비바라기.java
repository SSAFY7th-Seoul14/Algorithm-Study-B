import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21610_마법사상어와비바라기 {
	static int[][] d = {{0,0}, {0,-1}, {-1,-1}, {-1,0},{-1,1},{0,1}, {1,1},{1,0},{1,-1}};
	static int N;
	static int[][] map;
	static Queue<int[]> q;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		q = new LinkedList<>();
		for(int i=N-2; i < N; i++) //처음 구름 위치 넣기
			for(int j=0; j < 2; j++)
				q.add(new int[] {i,j});
		
		for(int i=0; i < M; i++) {
			visited = new boolean[N][N];//새 구름 만들기 전 구름이 있던 자리
			st = new StringTokenizer(br.readLine());
			int direction = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			move(direction,distance);
			checkAround();
			addCloud();
		}
		
		//물양 합
		int result = 0;
		for(int i=0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result  += map[i][j];
			}
		}
		
		//System.out.println(Arrays.deepToString(map));
		System.out.println(result);
	}
	
	// 새 구름 추가
	private static void addCloud() {
		for(int i=0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j] >= 2) {
					q.add(new int[] {i,j});
					map[i][j] -= 2;
				}
			}
		}
	}



	//4개방향의 대각선 탐색 후 물이 있으면 1추가
	private static void checkAround() {
		
		while(!q.isEmpty()) {
			int[] cloud = q.poll();
			int x = cloud[0];
			int y = cloud[1];
			
			for(int i=2; i <= 8; i+=2) {
				
				int dx = x + d[i][0];
				int dy = y + d[i][1];
				
				if(dx>=0 && dx < N && dy>=0 && dy < N) {
					if(map[dx][dy] > 0)
						map[x][y] += 1;
				}
			}
		}
		
	}
	
	//구름 이동
	private static void move(int direction, int distance) {
		int cnt = q.size();
		for(int i=0; i < cnt; i++) {
			int[] cloud = q.poll();
			
			int dx = cloud[0];
			int dy = cloud[1];
			
			for(int j = 0; j < distance; j++) {
				dx =  (dx + d[direction][0]) % N;
				if(dx == -1)
					dx = N-1;
				
				dy =  (dy + d[direction][1]) % N;
				if(dy == -1)
					dy = N-1;
			}
			cloud[0] = dx;
			cloud[1] = dy;
			q.offer(cloud);
			map[dx][dy] += 1;
			visited[dx][dy] = true;
		}
	}
	
	

}
