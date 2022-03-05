import java.io.*;
import java.util.*;

//BOJ / N과 M(12) / S2 / 
//https://www.acmicpc.net/problem/15666
public class Main_15666 {
	static int N,M;
	static int nums[];
	static int cmp[];
	static int input[];
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[M];
		input = new int[N];
		cmp = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		
		comb(0,0);
		bw.write(sb.toString());
		
		bw.close();
		br.close();
	}
	private static void comb(int idx, int start) {
		if(idx==M) {
			for(int num: nums)
				sb.append(num).append(" ");
			sb.append("\n");
			return;
		}
		for(int i=start;i<N;i++) {
			if(i<N-1 && input[i]==input[i+1]) continue; //중복 제거
			nums[idx] = input[i];
			comb(idx+1,i);
		}
		
	}
		
}
