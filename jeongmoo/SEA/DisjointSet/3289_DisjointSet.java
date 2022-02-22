import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SW Expert Academy 3289번. 서로소 집합
public class SWEA3289_DisjointSet {	
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= t; i++) {
			// 초기화
			sb.setLength(0);
			
			// 입력
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); 		// 숫자 개수
			int m = Integer.parseInt(st.nextToken());		// 데이터 개수
			
			makeSet(n);
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken()); // 연산 종류
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				if (command == 0)
					union(from, to);
				else if (command == 1) {
					if (findSet(from) == findSet(to))
						sb.append("1");
					else
						sb.append("0");
				}
			}
			
			// 출력
			System.out.printf("#%d %s\n", i, sb);
		}
	}
	
	public static void makeSet(int n) {
		parent = new int[n+1];
		for (int i = 0; i < n+1; i++) {
			parent[i] = i;
		}
	}
	
	public static int findSet(int num) {
		if(parent[num] == num) return num;
		return parent[num] = findSet(parent[num]);
	}
	
	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return;
		
		parent[bRoot] = aRoot;
	}
}
