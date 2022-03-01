package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1647_도시분할계획 {
	
	static class Edge implements Comparable<Edge>{
		int from,to,w;

		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			
			return this.w - o.w;
		}
		
		
		
	}

	static int[] parents;
	
	public static int findSet(int a) {
		if(parents[a] == 0) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		
		if(pa == pb) return false;
		parents[pb] = pa;
		return true;
	}
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		Edge[] edgeList = new Edge[M];
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edgeList[i] = new Edge(from,to,w);
		}
		
		Arrays.sort(edgeList);
		
		int result = 0, count = 0;
		for(Edge e : edgeList) {
			if(union(e.from, e.to)) {
				result +=  e.w;
				count++;
			}
			if(count == N-2) break;
		}
		
		System.out.println(result);
		
	}

}
