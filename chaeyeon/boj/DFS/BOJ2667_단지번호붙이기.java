package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2667_단지번호붙이기 {
	static int[][] map;
	static int[][] d = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		LinkedList<Integer> result = new LinkedList<>();
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					result.add(dfs(i,j));
					total++;
				}
			}
		}
		
		Collections.sort(result);
		
		System.out.println(total);
		for(int r : result) {
			System.out.println(r);
		}

	}

	private static int dfs(int x, int y) {
		int cnt = 0;
		
		map[x][y] = 0;
		cnt++;
		
		for(int i=0; i < 4; i++) {
			int dx = x+d[i][0];
			int dy = y+d[i][1];
			
			if(dx >= 0 && dy >= 0 && dx < N && dy < N && map[dx][dy] == 1) {
				
				cnt += dfs(dx,dy);
			}
		}
		return cnt;
	}
}
