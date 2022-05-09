package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//크루스칼
public class BOJ4386_별자리만들기 {
	static class Edge implements Comparable<Edge> {
		int from, to; 
		double w;
		
		public Edge(int from, int to, double w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
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

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		parents = new int[N];
		Arrays.fill(parents, -1);
		
		double[][] map = new double[N][2];
		for(int i=0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			map[i][0] = Double.parseDouble(st.nextToken());
			map[i][1] = Double.parseDouble(st.nextToken());
		}
		
		Edge[] edgeList = new Edge[N*(N-1)/2];
		int index = 0;
		for(int i=0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				double cost = Math.sqrt(Math.pow(map[i][0]-map[j][0],2) + Math.pow(map[i][1]-map[j][1],2));
				edgeList[index++] = new Edge(i,j,cost);
			}
		}
		
		Arrays.sort(edgeList);
		
		double total = 0;
		int cnt = 0;
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				total += edge.w;
				if(cnt++ == N-1)
					break;
			}
		}
		
		System.out.printf("%.2f", total);
		br.close();
	}

}
