import java.io.*;
import java.util.*;

// 벽 부수고 이동하기 / G4 /
// https://www.acmicpc.net/problem/2206

class Loc{
	int x;
	int y;
	int distance; //이동거리
	int wall;		//벽 부순 횟수 (0 or 1)
	
	public Loc(int x, int y, int distance, int wall) {
		this.x = x;
		this.y = y;
		this.distance = distance;
		this.wall = wall;
	}
	
	
}
public class Main_2206 {
	static int N,M;
	static int map[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static boolean visited[][][]; //벽 부신 횟수 저장
	static int res; //최단거리(정답)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2]; //[N][M][0]: 벽 부신적 X, [N][M][1]: 벽 1번 부심

		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}

		System.out.println(bfs(0,0));
		
	}
	private static int bfs(int x, int y) {
		Queue<Loc> q = new LinkedList<>();
		q.offer(new Loc(x,y,1,0)); //x좌표, y좌표, 벽 부순 횟수
		visited[x][y][0]=true;
		visited[x][y][1]=true;
		
		while(!q.isEmpty()) {
			Loc cur = q.poll();

			if(cur.x==N-1 && cur.y==M-1) //(N,M) 도달
				return cur.distance;
			
			for(int i=0;i<4;i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				
				if(map[nx][ny]==0) { //벽 아닌 경우
					if(!visited[nx][ny][cur.wall]) { //현재까지 온 방법(벽 부순 여부)에서 미방문이면
						q.add(new Loc(nx,ny,cur.distance+1,cur.wall));
						visited[nx][ny][cur.wall]=true;
					}
				}else {		//벽인 경우
					if(cur.wall==0 && !visited[nx][ny][1]) { //아직 벽을 부순적 없고, 벽 부숴서 방문한 적 없으면						q.add(new Loc(nx,ny, cur.distance+1, cur.wall+1));
						q.add(new Loc(nx,ny,cur.distance+1,cur.wall+1));
						visited[nx][ny][1]=true;
					}
				}
			}
				
		}
		return -1; //(N,M) 도달 못 하면 -1 반환
		
	}
	
	

}
