import java.io.*;
import java.util.*;
//BOJ / N과 M(11) / S2 / 13분
//https://www.acmicpc.net/problem/15665
public class Main_15665 {
	static int N,M;
	static int input[], result[];
	static boolean isSelected[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		isSelected = new boolean[N];
		result = new int[M];
		
		
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
			for(int i=0;i<M;i++)
				sb.append(result[i]).append(" ");
			sb.append("\n");
			return;
		}
		for(int i=0;i<N;i++) {
			if(i>0 && input[i-1]==input[i]) continue;
			result[idx]=input[i];
			permutation(idx+1);
		}
		
	}

}
