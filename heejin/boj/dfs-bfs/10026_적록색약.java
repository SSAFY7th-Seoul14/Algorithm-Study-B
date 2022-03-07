import java.io.*;
import java.util.*;

//BOJ / G5 / 적록색약 / 12분
//https://www.acmicpc.net/problem/10026
public class Main_10026 {
	static int N;
	static char map[][];
	static boolean visited[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		visited = new boolean[N][N];
		
		//map 입력받기
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j]=input.charAt(j);
			}
		}
		
		//적록색약 x인 사람
		int res1=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					bfs(i,j,map[i][j],false);
					res1++;
				}
			}
		}
		visited = new boolean[N][N];
		//적록색약인 사람
		int res2=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					bfs(i,j,map[i][j],true);
					res2++;
				}
			}
		}
		
		System.out.println(res1);
		System.out.println(res2);
	}
	private static void bfs(int x, int y, char ch, boolean color) {
		Queue<int[]> q = new LinkedList<>();
		visited[x][y]=true;
		q.offer(new int[] {x,y});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0], cy = cur[1];
			for(int i=0;i<4;i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<N) {

					if(!visited[nx][ny] && map[nx][ny]==ch) {
						q.offer(new int[] {nx,ny});
						visited[nx][ny]=true;
					}

					else if(color && !visited[nx][ny] && (ch=='R'||ch=='G') && (map[nx][ny]=='R'||map[nx][ny]=='G')) {
						q.offer(new int[] {nx,ny});
						visited[nx][ny]=true;
					}
					
				}
			}
		}
		
	}

}
