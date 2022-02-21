import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2580번. 스도쿠
public class BOJ2580_Sudoku {	
	static int[][] map = new int[9][9];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
			}
		}
		
		// 처리
		dfs();
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static boolean dfs() {
		Point p = findNext();
		// 다음 빈칸이 없다는 것은 스도쿠를 완성했다는 것.
		if (p.x == -1 && p.y == -1)
			return true;
			
		for (int i = 1; i <= 9; i++) {
			// 1부터 9까지 해당 자리에 하나씩 넣어보면서 백트래킹한다. (여기가 핵심)
			if (!find(p.x, p.y, i)) {
				map[p.x][p.y] = i;
				
				// 성공하면 스도쿠 완성했으니 바로 리턴
				boolean isFind = dfs();
				if (isFind)
					return true;
				
				// 중간에서 완성 실패하면 배열을 초기화하고 다음 경우의수로 간다.
				map[p.x][p.y] = 0;
			}
		}
		
		// 중간에 모든 경우의 수가 안되면 false를 리턴한다.
		return false;
	}
	
	// 다음 빈칸 받아오는 함수
	public static Point findNext() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(map[i][j] == 0) {
					return new Point(i, j);
				}
			}
		}
		return new Point(-1, -1);
	}
	
	public static boolean find(int x, int y, int findNum) {
		// 가로, 세로
		for (int i = 0; i < 9; i++) {			
			if (findNum == map[x][i] || findNum == map[i][y])
				return true;
		}
		
		// 대각선 체크
		int startX = x/3*3;
		int startY = y/3*3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (findNum == map[startX+i][startY+j])
					return true;
			}
		}
		
		return false;
	}
}
