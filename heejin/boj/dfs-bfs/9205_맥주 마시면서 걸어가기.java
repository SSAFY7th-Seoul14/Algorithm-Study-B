
import java.io.*;
import java.util.*;

// BOJ / 맥주 마시면서 걸어가기 / S1 / 30분..+
// https://www.acmicpc.net/problem/9205
public class Main_9205 {
	static class Conv {
		int num;
		int x;
		int y;

		public Conv(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}

	static int N, sx, sy, ex, ey;
	static List<Conv> list;
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 편의점 개수
			visited = new boolean[N];
			list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list.add(new Conv(i, x, y));
			}
			st = new StringTokenizer(br.readLine());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			if (bfs(sx, sy)) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}
	}

	private static boolean bfs(int sx, int sy) {
		Queue<Conv> q = new LinkedList<>();
		q.offer(new Conv(-1, sx, sy)); // 시작점
		
		while(!q.isEmpty()) {
			Conv cur = q.poll();
			//현재 위치에서 도착지까지 가능?
			if(Math.abs(cur.x-ex)+Math.abs(cur.y-ey)<=1000) {
				return true;
			}
			for(int i=0;i<N;i++) {
				Conv next = list.get(i);
				if(!visited[next.num] && 
						Math.abs(next.x-cur.x)+Math.abs(next.y-cur.y)<=1000) {
					q.offer(next);
					visited[next.num]=true;
				}
			}
		}
		return false;
	}


}
