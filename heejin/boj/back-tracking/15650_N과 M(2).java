import java.io.*;
import java.util.*;

// BOJ / N과 M(2) / S3 / 5분
//https://www.acmicpc.net/problem/15650
public class Main_15650 {
	static int N,M;
	static int nums[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[M];
		
		comb(0,1);
		
		
	}
	private static void comb(int idx, int start) {
		if(idx==M) {
			for(int num: nums)
				System.out.print(num+" ");
			System.out.println();
			return;
		}
		for(int i=start;i<=N;i++) {
			nums[idx]=i;
			comb(idx+1,i+1);
		}
		
	}
}
