import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//최소비용으로 모든 컴퓨터를 연결해야한다 -> MST -> 간선형태로 주어졌기 때문에 크루스칼
public class BOJ1922_네트워크연결 {
	
	static class Edge implements Comparable<Edge> {
		int from, to, w;

		public Edge(int from, int to, int w) {
			super();
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
		if(parents[a] == 0) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		
		if(pa == pb) return false;
		
		parents[pb] = pa;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		
		Edge[] edgeList = new Edge[M];
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edgeList[i] = new Edge(from, to, w);
		}
		
		Arrays.sort(edgeList);
		
		int result = 0, cnt=0;;
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				result += edge.w;
				if(++cnt == N-1) break;
			}
		}
		
		System.out.println(result);
	}

}
