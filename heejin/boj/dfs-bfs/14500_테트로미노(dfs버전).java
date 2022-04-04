import java.io.*;
import java.util.*;

// BOJ / 테트로미노(DFS버전) / G5 / 1시간
// https://www.acmicpc.net/problem/14500
public class Main_14500_dfs {
	static int res;
	static int map[][];
	static int N,M;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static boolean visited[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//모든 좌표에 대해 테트로미노 5개에 대한 합 구하기
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				dfs(i,j,0,0); //x좌표, y좌표, cnt, sum
				tet5(i,j);
			}
		}
		System.out.println(res);
		
	}
	private static void dfs(int x, int y, int cnt, int sum) {
		if(cnt==4) {
			res = Math.max(res, sum);
			return;
		}
		
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx,ny,cnt+1,sum+map[nx][ny]);
				visited[nx][ny]=false;
			}
			
		}
		
	}
	private static void tet5(int x, int y) {
		//1.
		int sum = 0;
		if(y+1<M && x-1>=0 && x+1<N) {
			sum +=map[x][y];
			for(int i=x-1;i<x+2;i++) 
				sum +=map[i][y+1];
			res = Math.max(res, sum);
		}
		if(x+1<N && y-1>=0 && y+1<M) {
			sum=0;
			sum +=map[x][y];
			for(int i=y-1;i<y+2;i++)
				sum +=map[x+1][i];
			res = Math.max(res, sum);
		}
		if(y-1>=0 && x-1>=0 && x+1<N) {
			sum = 0;
			sum +=map[x][y];
			for(int i=x-1;i<x+2;i++)
				sum +=map[i][y-1];
			res = Math.max(res, sum);
		}
		if(x-1>=0 && y-1>=0 && y+1<M) {
			sum = 0;
			sum +=map[x][y];
			for(int i=y-1;i<y+2;i++)
				sum +=map[x-1][i];
			res = Math.max(res, sum);
		}
		
	}
	
}
