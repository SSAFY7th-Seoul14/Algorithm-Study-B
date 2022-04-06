import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 2239번. 스도쿠
public class BOJ2239_Sudoku {
	static char[][] map = new char[9][9];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 계산
		dfs();
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (char c : map[i]) {
				sb.append(c);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static boolean dfs() {
		int[] empty = getPos();
		if (empty == null) {
			return true;
		}
		
		// 1부터 9까지 넣어본다.
		for (int i = 0; i < 9; i++) {
			char num = (char)('1'+i);
			
			if (!check(empty[0], empty[1], num)) // 가로나 세로에 존재하면
				continue;
			
			map[empty[0]][empty[1]] = num;
			if (dfs()) // 재귀 도는데 끝나면 여기도 종료시키자.
				return true;
			map[empty[0]][empty[1]] = '0';
		}
		
		return false;
	}
	
	public static boolean check(int x, int y, char num) {
		// 가로체크
		for (int i = 0; i < 9; i++) {
			if (map[x][i] == num)
				return false;
		}
		
		// 세로체크
		for (int i = 0; i < 9; i++) {
			if (map[i][y] == num)
				return false;
		}
		
		// 3x3체크
		int nx = x/3*3;
		int ny = y/3*3;
		for (int i = nx; i < nx+3; i++) {
			for (int j = ny; j < ny+3; j++) {
				if (map[i][j] == num)
					return false;
			}
		}
		
		return true;
	}
	
	public static int[] getPos() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == '0')
					return new int[] {i, j};
			}
		}
		return null;
	}
}