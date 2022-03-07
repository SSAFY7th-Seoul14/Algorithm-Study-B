
import java.io.*;
import java.util.*;

//BOJ / 단지번호붙이기(BFS버전) / S1 / 10분
//https://www.acmicpc.net/problem/2667
public class Main_2667 {
	static int N;
	static int map[][];
	static boolean visited[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		//map 입력받기
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}
		List<Integer> cnts = new ArrayList<>();
		//단지번호 붙이기 시작
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j] && map[i][j]==1) {
					int cnt = bfs(i,j);
					cnts.add(cnt);
				}
					
			}
		}
		Collections.sort(cnts);
		System.out.println(cnts.size());
		for(int cnt: cnts) {
			System.out.println(cnt);
		}
	}
	private static int bfs(int x, int y) {
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		visited[x][y]=true;
		int cnt=1; //단지 수
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0], cy = cur[1];
			for(int i=0;i<4;i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny]) {
					if(map[nx][ny]==1) {
						q.offer(new int[] {nx,ny});
						visited[nx][ny]=true;
						cnt++;
						
					}
				}
			}
			
		}
		return cnt;
		
	}

}
