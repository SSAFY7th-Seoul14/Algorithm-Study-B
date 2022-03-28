import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 백준 17472번. 다리 만들기 2
public class BOJ17472_BuildBridge2 {
	static class Edge implements Comparable<Edge>{
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

	static int n,m; // 세로 n, 가로 m
	static int[][] map;

	static boolean[][] isVisited;
	static int islandCount = 0; 
	
	static ArrayList<Edge> edgeList;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 지역 구분 (dfs)
		isVisited = new boolean[n][m];
		islandCount = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!isVisited[i][j] && map[i][j] != 0) {
					marking(i, j, ++islandCount);
				}
			}
		}
		
		// 간선 찾기
		edgeList = new ArrayList<>();
		findEdge();
		
		// 크루스칼
		Collections.sort(edgeList);
		parent = new int[islandCount+1];
		for (int i = 1; i < islandCount+1; i++) {
			parent[i] = i;
		}
		
		int count = 0;
		int result = 0;
		for (Edge e : edgeList) {
			if (count == islandCount-1)
				break;
			
			if (union(e.from, e.to)) {
				result += e.weight;
				count++;
			}
		}
		
		// 출력
		if (result == 0 || count != islandCount-1)
			System.out.println("-1");
		else
			System.out.println(result);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void marking(int x, int y, int num) {
		map[x][y] = num;
		isVisited[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || n <= nx || m <= ny)
				continue;
			if (isVisited[nx][ny] || map[nx][ny] == 0)
				continue;
			
			marking(nx, ny, num);	
		}
	}
	
	// n,m이 10밖에 안되기에 따로 visited 체크는 하지 않았음.
	public static void findEdge() {
		for (int k = 1; k <= islandCount; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					// k번 섬 탐색
					if(map[i][j] == k) {
						// 4방향 탐색해본다.
						for (int d = 0; d < 4; d++) {
							int nx = i;
							int ny = j;
							int dist = 0;
							while(true) {
								nx += dx[d];
								ny += dy[d];
								
								if (nx < 0 || ny < 0 || n <= nx || m <= ny)
									break;
								
								if (map[nx][ny] == k) {// 같은 섬이면 계속
									dist = 0;
									continue;
								}
								
								dist++;
								if (map[nx][ny] > 0) { // 다른 섬이면
									if (dist <= 2) // 거리가 1인섬은 다리 못만듬 
										break;
									
									edgeList.add(new Edge(k, map[nx][ny], dist-1));
									break;
								}									
							} // 한방향 탐색 끝
						} // 4방탐색 끝
					} // k번 섬을 찾았을 때
				} // j행 끝
			} // i열 끝 
		}
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		
		parent[bRoot] = aRoot;
		return true;
	}
	
	public static int find(int num) {
		if (num == parent[num])
			return num;
		
		return parent[num] = find(parent[num]);
	}
}

// 반례
/*
8 8
0 0 0 1 1 1 1 0
0 1 1 1 1 0 1 0
0 1 0 1 1 1 0 0
0 1 0 0 0 1 0 0
0 0 0 1 0 0 1 0
0 0 0 0 0 1 0 0
0 1 1 1 0 0 0 0
0 1 0 0 0 1 0 0
이렇게 같은 섬 사이에 바다(0번 - 1,5좌표)이 존재하는 경우 dist를 카운트하면 안됨
*/ 
