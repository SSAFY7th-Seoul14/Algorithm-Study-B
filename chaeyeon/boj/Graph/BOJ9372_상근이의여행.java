package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9372_상근이의여행 {

	public static void main(String[] args)  throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		//노드수-1
		for(int t=0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
			}
			
			System.out.println(n-1);
		}
	}

}
