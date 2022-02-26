package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ6497_Àü·Â³­ {

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
		if(parents[a] == -1) return a;
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
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) break;
			
			parents = new int[N];
			Arrays.fill(parents, -1);
			
			Edge[] edgeList = new Edge[M];
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				edgeList[i] = new Edge(from, to, w);
			}
			
			Arrays.sort(edgeList);
			
			int result = 0;
			for(Edge edge: edgeList) {
				if(!union(edge.from, edge.to)) {
					result += edge.w;
				}
				
			}
			System.out.println(result);
			
		}

	}

}
