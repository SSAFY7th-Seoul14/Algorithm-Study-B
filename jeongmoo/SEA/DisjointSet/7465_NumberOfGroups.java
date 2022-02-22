import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// SW Expert Academy 7465번. 창용 마을 무리의 개수
public class SWEA7465_NumberOfGroups {	
	static int[] parent;
	static int[] representative;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 1; i <= t; i++) {			
			// 입력
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); 		// 사람 수
			int m = Integer.parseInt(st.nextToken());		// 데이터 개수
			
			makeSet(n);
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				union(from, to);
			}
			
			// 계산
			int answer = 0;
			for (int j = 1; j < n+1; j++) {
				int rep = findSet(j);
				if (representative[rep]++ == 0)
					answer++;
			}
						
			// 출력
			System.out.printf("#%d %s\n", i, answer);
		}
	}
	
	public static void makeSet(int n) {
		parent = new int[n+1];
		representative = new int[n+1];
		Arrays.fill(parent, -1);
	}
	
	public static int findSet(int num) {
		if(parent[num] == -1) return num;
		return parent[num] = findSet(parent[num]);
	}
	
	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return;
		
		parent[bRoot] = aRoot;
	}
}
