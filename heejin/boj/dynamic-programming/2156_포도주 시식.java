
import java.io.*;
import java.util.*;
// BOJ / 포도주 시식 / S1 / 25분
//https://www.acmicpc.net/problem/2156
public class Main_2156 {
	static int N;
	static int arr[]; //포도주 양
	static int dp[]; // i번째 index까지 마신 최대 포도주양
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr= new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[N];
		if(N>=1)
			dp[0]=arr[0]; 
		if(N>=2)
			dp[1] = dp[0]+arr[1];
		if(N>=3)
			dp[2] = Math.max(dp[1], Math.max(dp[0] + arr[2], arr[1] + arr[2]));
		
		for(int i=3;i<N;i++) { //xoo, oxo, oox 경우 중 최대값 선택
			dp[i] = Math.max(Math.max(dp[i-2]+arr[i], dp[i-3]+arr[i-1]+arr[i]) , dp[i-1]);
		}
		System.out.println(dp[N-1]);
	}

}
