import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static char[][] grid;
	static int cnt, r, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		grid = new char[r][];
		for (int i = 0; i < r; i++) {
			grid[i] = br.readLine().toCharArray();
		}
		cnt = 0;
		for (int i = 0; i < r; i++) {
			connect(i, 0);
		}
		System.out.println(cnt);
	}

	static boolean connect(int y, int x) {
		if (x == c - 1) {
			cnt++;
			return true;
		}
		for (int i = -1; i < 2; i++) {
			int ny = y + i;
			int nx = x + 1;
			if (ny >= 0 && nx >= 0 && ny < r && nx < c && grid[ny][nx] == '.') {
				grid[ny][nx] = 'x'; 
				if (connect(ny, nx))
					return true; 
			}
		}
		return false;
	}
}