import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 백준 4386번. 별자리 만들기
public class BOJ4386_MakeConstellation {
	static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;
				
		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return (int)(this.weight - o.weight);
		}
	}
	static int n;
	static int[] parents;
	static double[][] stars;
	static ArrayList<Edge> edgeList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		stars = new double[n][2];
		edgeList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				double weight = Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));
				edgeList.add(new Edge(i, j, weight));
			}
		}
		Collections.sort(edgeList);
		
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
		
		int cnt = 0;
		double weight = 0;
		for (Edge e : edgeList) {
			if (union(e.from, e.to)) {
				weight += e.weight;
				cnt++;
			}
			if (cnt == n-1)
				break;
		}
		
		System.out.println(weight);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		
		parents[aRoot] = bRoot;
		return true;
	}
	
	public static int find(int num) {
		if (parents[num] == num)
			return num;
		return parents[num] = find(parents[num]);
	}
}
