import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// SW Expert Academy 1251번. 하나로
public class SWEA1251_Hanaro {
	static int n;
	static int[] parent;
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		double weight;
		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			if (this.weight > o.weight)
				return 1;
			else if (this.weight < o.weight)
				return -1;
			return 0;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int tc = 1; tc <= t; tc++) {
            // 입력
            n = Integer.parseInt(br.readLine());
            Point[] vertexList = new Point[n];

            // 정점 리스트 먼저 만들어놓고 정점들을 저장
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
            	vertexList[i] = new Point();
            	vertexList[i].x = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
            	vertexList[i].y = Integer.parseInt(st.nextToken());
            }
            double E = Double.parseDouble(br.readLine());  // 세율
            
            // 전체 간선 개수 = n-1 + n-2 + ... + 1 = n(n-1)/2  -> n이 1000이면 최대 500만
            // 세율 계산 시 거리의 제곱으로 계산하므로 구지 루트를 씌워줄 필요가 없다. 제곱값 그대로 저장
            Edge[] edgeList = new Edge[n*(n-1)/2];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
            	 for (int j = i+1; j < n; j++) {
            		 double dist = Math.pow(vertexList[i].x - vertexList[j].x, 2) + Math.pow(vertexList[i].y - vertexList[j].y, 2);
            		 edgeList[cnt++] = new Edge(i, j, dist);
            	 }
            }
            Arrays.sort(edgeList);
            
            // 처리
            parent = new int[n];
            Arrays.fill(parent, -1);
            
            // 크루스칼
            int count = 0;
            double total = 0;
            for (Edge edge : edgeList) {
            	if (count == n-1)
            		break;
            	
				if (union(edge.from, edge.to)) {
					total += (edge.weight * E);
					count++;
				}
			}            
             
            // 출력
            System.out.printf("#%d %d\n", tc, Math.round(total));
        }
    }
    
    public static int findSet(int n) {
    	if (parent[n] == -1)
    		return n;
    	return parent[n] = findSet(parent[n]);
    }
    
    public static boolean union(int a, int b) {
    	int rootA = findSet(a);
    	int rootB = findSet(b);
    	if (rootA == rootB)
    		return false;
    	
    	parent[rootB] = rootA;
    	return true;
    }
}