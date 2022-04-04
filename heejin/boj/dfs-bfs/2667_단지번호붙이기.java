import java.io.*;
import java.util.*;

// BOJ / 단지번호 붙이기 / S1 / 10분
// https://www.acmicpc.net/problem/2667
public class Main_2667 {
	static int N;
	static int map[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static boolean visited[][];
	static int house; //단지 내 집 수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}
		
		int cnt=0; //단지수
		List<Integer> list = new ArrayList<Integer>(); //단지 내의 집 수 저장
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j] && map[i][j]==1) {
					house=0;
					dfs(i,j);
					list.add(house);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		Collections.sort(list);
		for(int li : list) {
			System.out.println(li);
		}
		
		
	}
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		house++;
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y+ dy[i];
			if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) continue;
			if(map[nx][ny]==1)
				dfs(nx,ny);
		}
		
	}
	
}
