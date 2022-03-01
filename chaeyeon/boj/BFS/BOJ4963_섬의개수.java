package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//한시간
//문제를 제대로 안읽어서 종료조건을 안줌
public class BOJ4963_섬의개수 {
	static int w,h;
	static int[][] map;
	static int[][] d = {{-1,0}, {1,0}, {0,-1}, {0,1}, {1,1}, {1,-1}, {-1,-1}, {-1,1}};
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w == 0 && h == 0) break;
			map = new int[h][w];
			
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = 0;


			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(map[i][j] == 1) {
						map[i][j] = 0;
						bfs(new int[] {i,j});
						result++;
					}
				}
			}
			
			System.out.println(result);
		}
	}
	
	public static void bfs(int[] pos) {
		Queue<int[]> q = new LinkedList<>();
		
		q.add(pos);
		
		while(!q.isEmpty()) {
			int[] land = q.poll();
			for(int i = 0; i < 8; i++) {
				int x = land[0] + d[i][0];
				int y = land[1] + d[i][1];
				
				if(x>=0 && y>=0 && x<h && y<w && map[x][y]==1) {
					map[x][y] = 0;
					q.add(new int[] {x,y});
				}
			}
			
		}
	}

}
