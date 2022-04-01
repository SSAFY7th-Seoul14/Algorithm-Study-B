package dp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2차원 누적합 구하는 방법 찾아봤음.
//https://exponential-e.tistory.com/59
public class BOJ11660_구간합구하기5 {
	static int[][] map, sum;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		sum = new int[N+1][N+1];
		
		for(int i=1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//누적합 구하기
		for(int i=1; i <= N; i++) {
			for(int j=1; j<=N; j++) {
				sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + map[i][j];
			}
		}
		
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			
			//부분 누적합 구하기
			int subsum = sum[ex][ey] - sum[sx-1][ey] - sum[ex][sy-1] + sum[sx-1][sy-1];
			sb.append(subsum).append("\n");
		}
		
		System.out.println(sb);
	}

}
