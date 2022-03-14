import java.io.*;
import java.util.*;

//BOJ / 스티커 / S1 / 25분
//https://www.acmicpc.net/problem/9465
public class Main_9465 {
	static int input[][],dp[][];
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			dp = new int[2][N+1];
			input = new int[2][N+1];
			
			for(int i=0;i<2;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=1;j<=N;j++)
					input[i][j] = Integer.parseInt(st.nextToken());
			}
			
			dp[0][1] = input[0][1]; 
			dp[1][1] = input[1][1];
			for(int c=2;c<=N;c++) {
				for(int r=0;r<2;r++) {
					if(r==0) { //위쪽 스티커
						dp[r][c] = Math.max(input[r][c]+dp[r+1][c-1], Math.max(input[r][c]+ dp[r][c-2], input[r][c]+dp[r+1][c-2]));
					}
					else if(r==1) { //아래쪽 스티커
						dp[r][c] = Math.max(input[r][c]+dp[r-1][c-1], Math.max(input[r][c]+ dp[r][c-2], input[r][c]+dp[r-1][c-2]));
					}
				}
			}
			System.out.println(Math.max(dp[0][N], dp[1][N]));
			
		}
	}
}
