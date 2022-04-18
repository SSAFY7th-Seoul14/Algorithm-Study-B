package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//실버1 https://www.acmicpc.net/problem/1080
public class BOJ1080_행렬 {
	static int N,M;
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		int[][] change = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) -'0';
			}
		}
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				change[i][j] = line.charAt(j) -'0';
			}
		}
		
		int result = -1;
		if(check(map, change)) result = 0;
		if(N < 3 || M < 3 || result == 0) {
			System.out.println(result);
			return;
		}
		
		int cnt = 0;
		outer : for (int i = 0; i < N-2; i++) {
			for (int j = 0; j < M-2; j++) {
				if(map[i][j] == change[i][j]) continue;
				cnt++;
				for(int k=i; k < i+3; k++) {
					for(int z=j; z < j+3; z++) {
						map[k][z] = map[k][z]^1;
					}
				}
				if(check(map, change)) {
					result = cnt;
					break outer;
				}
				
			}
		}
		
		System.out.println(result);
	}
	private static boolean check(int[][] map, int[][] change) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != change[i][j]) return false;
			}
		}
		return true;
		
	}

}
