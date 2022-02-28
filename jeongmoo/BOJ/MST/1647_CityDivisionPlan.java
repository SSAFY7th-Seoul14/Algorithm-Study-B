import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1647번. 도시 분할 계획
public class BOJ1647_CityDivisionPlan {
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight-o.weight;
		}
	}
	
	static int parent[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 정점
		int m = Integer.parseInt(st.nextToken()); // 간선
		
		parent = new int[n+1];
		Arrays.fill(parent, -1);
		Edge[] edgeList = new Edge[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		Arrays.sort(edgeList);
		
		int count = 0;
		int answer = 0;
		for (int i = 0; i < m; i++) {
			Edge edge = edgeList[i];
			
			// 최소신장트리에서 하나를 끊으면 2개의 마을이므로 간선 n-2개까지
			if (count == n-2)
				break;
			
			if(union(edge.from, edge.to)) {
				count++;
				answer += edge.weight;
			}
		}
		
		System.out.println(answer);
	}
	
	public static int find(int n) {
		if (parent[n] == -1)
			return n;
		return parent[n] = find(parent[n]);
	}
	
	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return false;
		parent[rootB] = rootA;
		return true;
	}
	
	
}