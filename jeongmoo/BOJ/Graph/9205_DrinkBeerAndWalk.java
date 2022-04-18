import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 9205번. 맥주 마시면서 걸어가기
public class BOJ9205_DrinkBeerAndWalk {
	static int n;
	static Point[] pos;
	static int[][] adj;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine()); // 편의점 개수
			pos = new Point[n+2];
			adj = new int[n+2][n+2];
			
			// 데이터 입력
			st = new StringTokenizer(br.readLine());
			pos[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				pos[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			pos[n+1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			// 인접 행렬 만든다. 거리 기준으로
			for (int i = 0; i < n+2; i++) {
				for (int j = i+1; j < n+2; j++) {
					if (getDist(pos[i], pos[j]) <= 1000)
						adj[i][j] = adj[j][i] = 1;						
				}
			}
			
			String result = isHappy();
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	public static String isHappy() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] isVisited = new boolean[n+2];
		q.offer(0);
		isVisited[0] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur == n+1)
				return "happy";
			
			for (int i = 0; i < n+2; i++) {
				if (!isVisited[i] && adj[cur][i] != 0) {
					q.offer(i);
					isVisited[i] = true;
				}
			}
		}
		return "sad";
	}
	
	public static int getDist(Point s, Point e) {
		return Math.abs(s.x-e.x) + Math.abs(s.y-e.y);
	}
}