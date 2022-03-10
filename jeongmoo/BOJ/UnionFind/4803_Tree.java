import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 4803번. 트리
public class BOJ4803_Tree {
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int caseNum = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if (n==0 && m==0)
				break;
			
			parent = new int [n+1];
			for (int i = 0; i < n+1; i++) {
				parent[i] = i;
			}
			
			// 입력 및 처리
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				union(from, to);
			}
			
			int treeCount = 0;
			for (int i = 1; i < n+1; i++) {
				if (find(i) == i) {	// 대표자의 개수를 센다.
					treeCount++;
				}
			}
			
			if (treeCount == 0)
				result.append("No trees.");
			else if (treeCount == 1)
				result.append("There is one tree.");
			else					
				result.append("A forest of ").append(treeCount).append(" trees.");				
			
			// 출력
			System.out.printf("Case %d: %s\n", caseNum++, result);
		}
	}
	
	public static void union(int a, int b) {		
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) {
			parent[aRoot] = 0; // 대표자가 같으면 = 사이클이 발생하면
			return;
		}
		
		// 대표자가 0으로 초기화되는 경우가 있기 때문에 작은값에 큰값을 넣으면 안된다.
		// 무조건 큰값을 루트로 두고 작은값을 넣어야 함.
		if (aRoot > bRoot)
			parent[aRoot] = bRoot;
		else
			parent[bRoot] = aRoot;
	}
	
	public static int find(int num) {
		if (parent[num] == num)
			return num;
		return parent[num] = find(parent[num]);
	}
}
