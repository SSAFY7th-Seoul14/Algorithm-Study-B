import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9663 {
	static int n, cnt;
	// 각 row에 해당하는 index, 해당 row, index에 놓일 queen의 col 값을 저장할 cols
	static int[] cols;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		cols = new int[n];
		nQueens(0);
		System.out.println(cnt);
	}

	private static void nQueens(int r) {
		// 기저 조건 끝까지 놓이고 넘어 왔다면 n-queen 만족
		if (r == n) {
			cnt++;
			return;
		}
		// 0~n까지 Queen을 놓는 시도
		for (int i = 0; i < n; i++) {
			// r row, i col에 queen을 놓아보기
			cols[r] = i;
			// 놓아도 된다면
			if (isAvailable(r)) {
				// 다음 nQueens로 넘어가기
				nQueens(r + 1);
			}
		}
	}

	// r을 놓을 수 있을지 판단하기 위한 코드
	private static boolean isAvailable(int r) {
		// 0부터 r까지 놓아도 되는지 확인하기
		for (int i = 0; i < r; i++) {
			// 놓여있는 col값들(i)과 놓으려는 queen(r)의 col값이 같거나
			// 행(row)의 차이갑과 열(col)의 차이값이 같으면 같은 대각선상에 있는 것이므로
			if (cols[r] == cols[i] || r - i == Math.abs(cols[r] - cols[i]))
				// 놓을 수 없는 위치이기에 false 반환
				return false;
		}
		// 겹치는 범위가 없었으므로 놓아도 되는 위치
		return true;
	}

}