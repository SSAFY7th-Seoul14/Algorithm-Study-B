import java.util.*;
import java.io.*;

//BOJ / N과 M(3) / S3 / 8분
//https://www.acmicpc.net/problem/15651
public class Main_15651 {
	static int N,M;
	static int nums[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[M];
		permutation(0);
		bw.write(sb.toString());
		bw.close();
		br.close();
		
	}
	private static void permutation(int idx) {
		if(idx==M) {
			for(int num: nums) 
				sb.append(num).append(" ");
			sb.append("\n");
			return;
		}
		for(int i=1;i<=N;i++) {			
			nums[idx] = i;
			permutation(idx+1);			
		}
		
	}
}
