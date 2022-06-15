import java.util.*;
import java.io.*;

public class BOJ19238_스타트택시 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Customer {
		Point from, to;

		public Customer(Point from, Point to) {
			this.from = from;
			this.to = to;
		}
	}

	static class Car {
		Point curP;
		int fuel;

		public Car(Point position, int fuel) {
			super();
			this.curP = position;
			this.fuel = fuel;
		}
	}

	static int n, m;
	static Customer[] customers;
	static boolean[][] wall;
	static int[][] mapLoc;
	static int fuel;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		fuel = stoi(st.nextToken());
		wall = new boolean[n][n];
		mapLoc = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int tmp = stoi(st.nextToken());
				if (tmp == 1)
					wall[i][j] = true;
			}
		}
		st = new StringTokenizer(br.readLine());
		Point start = new Point(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1);
		customers = new Customer[m + 1];
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			Point from = new Point(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1);
			Point to = new Point(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1);
			customers[i] = new Customer(from, to);
			mapLoc[from.r][from.c] = i;
		}
		for (int i = 1; i <= m; i++) {
			Customer p = findNearest(start);
			if (p == null)
				fuel = -1;
			if (fuel < 0)
				break;
			start = takeTo(p);
			if (start == null)
				fuel = -1;
			if (fuel < 0)
				break;
		}
		bw.write(String.valueOf(fuel));
		bw.flush();
		bw.close();
		br.close();
	}

	private static Point takeTo(Customer p) {
		Queue<Car> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][n];
		Point start = p.from;
		Point to = p.to;
		visited[start.r][start.c] = true;
		q.add(new Car(start, fuel));
		int initFuel = fuel;
		while (!q.isEmpty()) {
			Car cur = q.poll();
			int r = cur.curP.r;
			int c = cur.curP.c;
			int curFuel = cur.fuel;
			if (curFuel < 0) {
				fuel = curFuel;
				break;
			}
			if (r == to.r && c == to.c) {
				fuel = curFuel + 2 * (initFuel - curFuel);
				return to;
			}
			for (int[] d : delta) {
				int nR = r + d[0];
				int nC = c + d[1];
				if (rangeCheck(nR, nC) && !wall[nR][nC] && !visited[nR][nC]) {
					visited[nR][nC] = true;
					q.add(new Car(new Point(nR, nC), curFuel - 1));
				}
			}
		}
		return null;
	}

	static int[][] delta = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	private static Customer findNearest(Point start) {
		Queue<Car> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][n];
		visited[start.r][start.c] = true;
		q.add(new Car(start, fuel));
		int near = -1;
		Point target = null;
		Customer ret = null;
		while (!q.isEmpty()) {
			Car cur = q.poll();
			int r = cur.curP.r;
			int c = cur.curP.c;
			int curFuel = cur.fuel;
			if (curFuel < 0) {
				fuel = curFuel;
				break;
			}
			int no = mapLoc[r][c];
			if (near > curFuel) {
				mapLoc[target.r][target.c] = 0;
				break;
			}
			if (no > 0) {
				fuel = curFuel;
				near = curFuel;
				if (ret == null || ret.from.r > r || (ret.from.r == r && ret.from.c > c)) {
					ret = customers[no];
					target = new Point(r, c);
				}
			}
			for (int[] d : delta) {
				int nR = r + d[0];
				int nC = c + d[1];
				if (rangeCheck(nR, nC) && !wall[nR][nC] && !visited[nR][nC]) {
					visited[nR][nC] = true;
					q.add(new Car(new Point(nR, nC), curFuel - 1));
				}
			}
		}
		return ret;
	}

	private static boolean rangeCheck(int r, int c) {
		return 0 <= r && r < n && 0 <= c && c < n;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
