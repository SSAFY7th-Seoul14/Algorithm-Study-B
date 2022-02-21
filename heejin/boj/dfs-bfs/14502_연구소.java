package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ / 연구소 / G5 / 100분
// https://www.acmicpc.net/problem/14502
public class Main_14502 {
	
	static int[][] map; // 지도
	static int[][] temp; // 벽 세우고 난 뒤 바이러스 퍼뜨릴 때 사용
	static int cnt; //벽 개수
	static int N,M;
	static int[] dx = {-1,1,0,0}; //상하좌우 x 값
	static int[] dy= {0,0,-1,1}; //상하좌우 y값
	static int res=Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		temp = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//벽 3개 세우기
		wall(0);
		System.out.println(res);
		

	}
	private static void wall(int cnt) {
		if(cnt==3) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					temp[i][j] = map[i][j]; //배열 복사
				}
			}
			//바이러스 퍼뜨리기
			virus();
			
			 //안전 구역 계산
			int safe=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(temp[i][j]==0)
						safe++;
				}
			}
			res= Math.max(res, safe);
			return;
		}
	
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0) {
					map[i][j]=1;
					cnt+=1;
					wall(cnt);
					map[i][j]=0;
					cnt-=1;
				}
			}
		}
	}
	private static void virus() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(temp[i][j]==2) {
					//바이러스 퍼뜨리기 시작
					bfs(i,j);
				}
			}
		}
		
	}
	
	
	 
	 //virus 퍼뜨리기 bfs 버전
	private static void bfs(int x, int y) {
		Queue<int[]> q= new LinkedList<>();
		q.offer(new int[]{x,y});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int i=0;i<4;i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if(nx >=0 && nx < N && ny>=0 && ny<M) {
					if(temp[nx][ny]==0){
						q.offer(new int[] {nx,ny} );
						temp[nx][ny]=2;
					}
				}
			}
		}
		
	}
	
	// virus 퍼뜨리기 dfs 버전
	 public static void virus(int x, int y) {
	        for (int i = 0; i < 4; i++) {
	            int nx = x + dx[i];
	            int ny = y + dy[i];
	            // 상, 하, 좌, 우 중에서 바이러스가 퍼질 수 있는 경우
	            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
	                if (temp[nx][ny] == 0) {
	                    // 해당 위치에 바이러스 배치하고, 다시 재귀적으로 수행
	                    temp[nx][ny] = 2;
	                    virus(nx, ny);
	                }
	            }
	        }
	     
	    }
}
