import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 6497번. 전력난
public class BOJ6497_PowerShortage {
	static int[] parent;
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
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken()); // 집의 수 (정점)
			int n = Integer.parseInt(st.nextToken()); // 길의 수 (간선)
			
			if(m==0 && n==0)
				break;
			
			// 입력
			int total = 0;
			Edge[] edgeList = new Edge[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
				total += weight;
			}
			Arrays.sort(edgeList);
			
			// 처리
			parent = new int[m];
			Arrays.fill(parent, -1);
			
			int sum = 0;
			int count = 0;
			for (Edge edge : edgeList) {
				if (count == m-1)
					break;
				
				if (union(edge.from, edge.to)) {
					sum += edge.weight;
					count++;
				}
			}
			
			// 출력
			System.out.println(total-sum);
		}
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