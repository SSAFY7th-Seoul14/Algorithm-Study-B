import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 17471번. 게리맨더링
public class BOJ17471_Gerrymandering {
	static class Edge {
		int from, to;
		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	static int n, result;
	static int[] person;
	static boolean[] isSelected;
	static ArrayList<Edge> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		person = new int[n+1];
		isSelected = new boolean[n+1];
		list = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}
		for (int from = 1; from <= n; from++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int i = 0; i < cnt; i++) {
				int to = Integer.parseInt(st.nextToken());
				list.add(new Edge(from, to));
			}
		}
		
		// 부분집합
		result = Integer.MAX_VALUE;
		subset(1, 0);
		
		// 출력
		if (result == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(result);
	}
	
	public static void subset(int idx, int aCnt) {
		if (idx == n+1) {
			// A그룹이 0명이면 불가능
			if (aCnt == 0 || aCnt == n)
				return;
			
			// 연결가능한지 체크 및 최소값 리턴
			int diff = getGroupDiff(aCnt, n-aCnt);
			if(-1 != diff)
				result = Math.min(result, diff);
			return;
		}
		
		isSelected[idx] = true;
		subset(idx+1, aCnt+1);
		isSelected[idx] = false;
		subset(idx+1, aCnt);
	}
	
	static int[] parent;
	public static int getGroupDiff(int aGroupCnt, int bGroupCnt) {
		parent = new int[n+1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		// 그룹마다 연결해본다
		int aUnionCnt = 0; // 연결된 간선 개수
		int bUnionCnt = 0; // 연결된 간선 개수
		for (Edge e : list) {
			// 같은 그룹이면 union 해본다.
			if (isSelected[e.from] == isSelected[e.to]) {
				if (union(e.from, e.to)) {
					if (isSelected[e.from])
						aUnionCnt++;
					else
						bUnionCnt++;
				}
			}
			
			if (aUnionCnt == aGroupCnt-1 && bUnionCnt == bGroupCnt-1)
				break;
		}
		
		// 가능한 경우면
		if (aUnionCnt == aGroupCnt-1 && bUnionCnt == bGroupCnt-1) {
			int aSum = 0;
			int bSum = 0;
			// a그룹 합
			for (int i = 1; i <= n; i++) {
				if (isSelected[i])
					aSum += person[i];
			}
			// b그룹 합
			for (int i = 1; i <= n; i++) {
				if (!isSelected[i])
					bSum += person[i];
			}
			return Math.abs(aSum - bSum);
		}
		return -1;
	}
	
	public static int find(int num) {
		if (parent[num] == num)
			return num;
		return parent[num] = find(parent[num]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		
		parent[bRoot] = aRoot;
		return true;
	}
}