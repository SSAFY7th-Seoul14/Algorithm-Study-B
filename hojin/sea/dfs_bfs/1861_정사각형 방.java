import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA1861 {

	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }, location, room;
	static int n, cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			// 각 방의 숫자를 저장할 room
			room = new int[n][n];
			// 특정 숫자의 위치를 저장할 location
			location = new int[n * n + 1][];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int num = Integer.parseInt(st.nextToken());
					room[i][j] = num;
					location[num] = new int[] { i, j };
				}
			}
			int max = Integer.MIN_VALUE;
			int roomNo = 0;
			// 1 위치부터 4방 탐색해서 최대한 많이 가고, cnt++
			for (int i = 1; i <= n * n; i++) {
				cnt = 1;
				dfs(i);
				if (cnt > max) {
					max = cnt;
					roomNo = i;
				}
				// 이동한만큼은 기억해서 dfs 시작점 이동
				i += cnt - 1;
			}
			// 기억해놓은 위치부터 다시 사방 탐색
			bw.write(String.format("#%d %d %d%n", tc, roomNo, max));
		}
		br.close();
		bw.flush();
		bw.close();
	}

	private static void dfs(int start) {
		int R = location[start][0];
		int C = location[start][1];
		for (int i = 0; i < 4; i++) {
			int nR = R + delta[i][0];
			int nC = C + delta[i][1];
			if (0 <= nR && nR < n && 0 <= nC && nC < n) {
				int roomNum = room[nR][nC];
				if (roomNum == start + 1) {
					++cnt;
					dfs(roomNum);
				}
			}
		}
	}

}