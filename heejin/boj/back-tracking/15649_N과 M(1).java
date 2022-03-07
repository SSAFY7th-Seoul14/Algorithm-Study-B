import java.io.*;
import java.util.*;
// BOJ / N과 M(1) / S3 / 10분
//https://www.acmicpc.net/problem/15649
public class Main_15649 {
	static int N,M;
	static int nums[];
	static boolean isSelected[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[M];
		isSelected = new boolean[N+1];
		
		permutation(0);
	}
	private static void permutation(int idx) {
		if(idx==M) {
			for(int num: nums)
				System.out.print(num+" ");
			System.out.println();
			
			return;
		}
		for(int i=1;i<=N;i++) {
			if(isSelected[i]) continue;
			nums[idx] = i;
			isSelected[i] = true;
			permutation(idx+1);
			isSelected[i]=false;
		}
		
	}
}
