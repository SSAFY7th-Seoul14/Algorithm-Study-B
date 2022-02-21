package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ / 미로 탐색 / S1 / 1시간 +...
// https://www.acmicpc.net/problem/2178
//참고: https://ldgeao99.tistory.com/400
public class Main_2178_2 {
	static int N,M;
	static int[][]map;
	static int dx[] = {-1,1,0,0}; // 4방 탐색 상하좌우
	static int dy[] = {0,0,-1,1};
	
	static boolean[][] visited; // 방문여부 저장
	static int[][] dist; //방문여부 및 시작점부터 현재 지점까지의 거리
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		dist = new int[N][M];
		dist[0][0]=1; //시작점부터 거리 count
		
		//미로 입력받기
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}
		bfs(0,0);
		System.out.println(dist[N-1][M-1]);
		
	}
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		visited[x][y]=true;
		
		while(!q.isEmpty()) {
			int[] cur=q.poll();
			int cx = cur[0];
			int cy = cur[1];
			for(int i=0;i<4;i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<M) {
					if(isAvailable(nx,ny)) {
						q.offer(new int[] {nx,ny});
						visited[nx][ny]=true;
						dist[nx][ny] = dist[cx][cy]+1; //거리 갱신						
					}
				}
			}
		}
	}

	
	private static boolean isAvailable(int x,int y) { //유망 여부
		if(map[x][y]==1 && !visited[x][y]) return true; // 길이 1이고, 아직 방문하지 않았을 경우
		else
			return false;
	}

}
