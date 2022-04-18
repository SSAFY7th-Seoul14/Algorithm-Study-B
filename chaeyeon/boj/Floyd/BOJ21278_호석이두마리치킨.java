package 최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//플로이드 와샬 -> 조합으로 2개 뽑은 후 최단거리 계산
//골드5 https://www.acmicpc.net/problem/21278
public class BOJ21278_호석이두마리치킨 {
	static final int INF = 1000;
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		
		for(int i=1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i!=j)
					map[i][j] = INF;
					
			}
		}
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int b1 = Integer.parseInt(st.nextToken());
			int b2 = Integer.parseInt(st.nextToken());
			
			map[b1][b2] = 1;
			map[b2][b1] = 1;
		}
		
		for(int k=1; k <= N; k++) {
			for(int i=1; i <= N; i++) {
				if(i==k) continue;
				for(int j=1; j <= N; j++) {
					if(i==j || k==j) continue;
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		int chicken1 = -1, chicken2 = -1, min=Integer.MAX_VALUE;
		for(int i=1; i <= N-1; i++) {
			for (int j = i+1; j <= N; j++) {
				int d = 0;
				for(int k=1; k <= N; k++) {
					d += Math.min(map[i][k],map[j][k]);
				}
				if(min > d) {
					chicken1 = i;
					chicken2 = j;
					min = d;
				}
			}
			
		}
		System.out.println(chicken1 +" " + chicken2 +" " +min*2);
	}

}
