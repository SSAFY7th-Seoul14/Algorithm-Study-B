import java.io.*;
import java.util.*;
//BOJ / 안전영역 / S1 / 15분
//https://www.acmicpc.net/problem/2468
public class Main_2468 {
	static int N;
	static int map[][];
	static int res;
	static boolean visited[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int h=0;h<=100;h++) { //강수량이 0~100일때까지의 안전지대 개수 구하기
			int cnt=0;
			visited = new boolean[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j] && map[i][j]>h) {
						dfs(i,j,h);
						cnt++;
					}
				}
			}
			res = Math.max(res,cnt);
		}
		System.out.println(res);
	}
	private static void dfs(int x, int y, int h) {
		visited[x][y] = true;
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) continue;
			if(map[nx][ny]>h)
				dfs(nx,ny,h);
		}
		
	}
}
