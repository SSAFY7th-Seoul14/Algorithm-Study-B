import java.io.*;
import java.util.*;

public class Main_4386 {
	static class Edge implements Comparable<Edge>{
		int a;
		int b;
		double weight;
		public Edge(int a, int b, double weight) {
			super();
			this.a = a;
			this.b = b;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			if(this.weight<o.weight)
				return -1;
			else
				return 1;
		}
		
	}
	
	static int N;
	static double stars[][];
	static List<Edge> list;
	static int parents[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		stars = new double[N][2];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
		list = new ArrayList<Edge>();
		parents = new int[N];
		
		for(int i=0;i<N;i++) {
			for(int j=i+1;j<N;j++) {
				// 좌표 간 거리 계산
				double dx = stars[i][0]-stars[j][0];
				double dy = stars[i][1]-stars[j][1];
				double weight = Math.sqrt(dx*dx+ dy*dy);
				list.add(new Edge(i,j,weight));
				list.add(new Edge(j,i,weight));
			}
		}
		Collections.sort(list);
		
		// 크루스칼 알고리즘 수행
		double res = 0; //신장 트리 비용
		initParents();
		for(int i=0;i<list.size();i++) {
			Edge cur = list.get(i);
			if(find(cur.a)!=find(cur.b)) {
				res += cur.weight;
				union(cur.a, cur.b);
			}
		}
		System.out.println(Math.round(res*100)/100.0);
		
		
	}

	

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot>bRoot)
			parents[aRoot]=bRoot;
		else
			parents[bRoot] = aRoot;
	}



	private static void initParents() {
		for(int i=0;i<N;i++) {
			parents[i] = i;
		}
	}
	private static int find(int x) {
		if(parents[x]==x)
			return x;
		else return parents[x]=find(parents[x]);
	}
	
}
