import java.io.*;
import java.util.*;

public class BOJ1520_내리막길 {
	static int[][] mountains;
	static int m, n;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	// dp로 이용해줄 visited 배열
	static int[][] visited;

	public static void main(String[] args) throws Exception {
		// 입력 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 산의 세로
		m = sToi(st.nextToken());
		// 가로
		n = sToi(st.nextToken());
		// 산의 높이들 입력 받기
		mountains = new int[m][n];
		visited = new int[m][n];
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; ++j) {
				mountains[i][j] = sToi(st.nextToken());
			}
		}
		// 방문하지 않음은 -1로 처리
		for (int[] is : visited) {
			Arrays.fill(is, -1);
		}
		// 도달하는 경우의 수 세어줄 answer
		visited[0][0] = dfs(0, 0);
		System.out.println(visited[0][0]);
		br.close();
	}

	private static int dfs(int r, int c) {
		// 목표점에 도달한다면
		if (r == m - 1 && c == n - 1) {
			return 1;
		}
		// 방문시 0으로 처리
		visited[r][c] = 0;
		// 4방 탐색해서
		for (int i = 0; i < 4; ++i) {
			int nR = r + dr[i];
			int nC = c + dc[i];
			// 경계값 체크
			if (rangeCheck(nR, nC)) {
				// 현재 높이가 다음 높이보다 클 때만 즉 내리막일 경우만
				if (mountains[r][c] > mountains[nR][nC]) {
					// 방문한 곳은 해당 값만큼만 더하고 0이어도 0만 더하는 건 괜찮다.
					if (visited[nR][nC] >= 0) {
						visited[r][c] += visited[nR][nC];
						continue;
					}
					// 방문하지 않았을 경우만 dfs로 탐색 이동
					visited[r][c] += dfs(nR, nC);
				}
			}
		}
		// 도착지까지 다녀온 값이 기억된 값 반환
		return visited[r][c];
	}

	// 경계값 체크
	private static boolean rangeCheck(int nR, int nC) {
		if (0 <= nR && nR < m && 0 <= nC && nC < n)
			return true;
		return false;
	}

	private static int sToi(String str) {
		return Integer.parseInt(str);
	}

}
