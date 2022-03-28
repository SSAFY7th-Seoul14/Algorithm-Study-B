import java.io.*;
import java.util.*;

// BOJ / 구간 합 구하기 5 / S1 / 1시간
// https://www.acmicpc.net/problem/11660
public class Main_11660 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int arr[][] = new int[N+1][N+1];
		int dp[][] = new int[N+1][N+1]; //누적합
		//arr 입력받기
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//dp[i][j]: (1,1)~(i,j)까지의 누적합
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
			}
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			System.out.println(dp[x2][y2]-dp[x1-1][y2]-dp[x2][y1-1]+dp[x1-1][y1-1]);
		}
		
		
	}
}
