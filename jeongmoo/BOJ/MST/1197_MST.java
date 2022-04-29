import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1197번. 최소 스패닝 트리
public class BOJ1197_MST {
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		Edge[] edgeList = new Edge[e];
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList);
		
		parent = new int[v+1];
		for (int i = 0; i < v+1; i++) {
			parent[i] = i;
		}
		
		int unionCnt = 0;
		int result = 0;
		for (Edge edge : edgeList) {
			if (unionCnt == v-1)
				break;
			
			if (union(edge.from, edge.to)) {
				unionCnt++;
				result += edge.weight;
			}
		}
		
		System.out.println(result);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		
		parent[bRoot] = aRoot;
		return true;
	}
	
	public static int find(int num) {
		if (num == parent[num])
			return num;
		
		return parent[num] = find(parent[num]);
	}
	
}
