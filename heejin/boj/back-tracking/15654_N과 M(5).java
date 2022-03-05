import java.io.*;
import java.util.*;

// BOJ / N과 M(5) / S3 / 5분
// https://www.acmicpc.net/problem/15654
public class Main_15654 {
	static int N,M;
	static int nums[];
	static int input[];
	static boolean isSelected[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[M];
		input = new int[N];
		isSelected = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		
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
		for(int i=0;i<N;i++) {
			if(isSelected[i]) continue;
			nums[idx] = input[i];
			isSelected[i]=true;
			permutation(idx+1);
			isSelected[i]=false;
		}
		
	}

}
