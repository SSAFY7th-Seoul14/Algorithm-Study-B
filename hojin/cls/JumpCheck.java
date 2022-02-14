// 45분
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JumpCheck {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int T, tc, n, striderCnt, i;
	static boolean grid[][], flag;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			grid = new boolean[n][n];
			striderCnt = Integer.parseInt(st.nextToken());
			flag = false;
			for (i = 1; i <= striderCnt; i++) {
				st = new StringTokenizer(br.readLine());
				runStrider(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), i);
			}
			if (!flag)
				sb.append("0");
			sb.append("\n");
		}
		br.close();
		System.out.println(sb);
	}

	static class Strider {
		int originX, originY, mode, index, delta[][] = { { 1, 0 }, { 0, 1 } };

		public Strider(int originX, int originY, int mode, int index) {
			this.originX = originX;
			this.originY = originY;
			this.mode = mode - 1; // 모드는 1부터 입력 받기 때문
			this.index = index;
		}

		public void jump() {
			int dx = originX;
			int dy = originY;
			for (int j = 3; j >= 1; j--) {
				dy += j * delta[mode][0];
				dx += j * delta[mode][1];
				if (dx >= n || dy >= n)
					return;
				if (grid[dy][dx] && !flag) {
					flag = true;
					sb.append(index);
					return;
				}
				grid[dy][dx] = true;
			}
		}
	}

	// 행 열 입력 엇갈려서 받음
	private static void runStrider(int y, int x, int mode, int index) {
		Strider str = new Strider(x, y, mode, index);
		str.jump();
	}
}