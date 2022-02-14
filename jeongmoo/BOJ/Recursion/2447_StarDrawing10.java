import java.util.Arrays;
import java.util.Scanner;

public class BOJ2447_StarDrawing10 {
	static char map[][];
	public static void main(String[] args) throws Exception {
		// 입력
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		map = new char[n][n];
		
		// 처리
		for (char[] m : map) {
			Arrays.fill(m, ' ');
		}
		recursion(n/3, 0, 0);
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void recursion(int n, int x, int y) {		
		// 마지막이면 다 찍자
		if (n==1) {
			map[x][y] = '*';
			map[x][y+1] = '*';
			map[x][y+2] = '*';
			
			map[x+1][y] = '*';
			map[x+1][y+1] = ' ';
			map[x+1][y+2] = '*';
			
			map[x+2][y] = '*';
			map[x+2][y+1] = '*';
			map[x+2][y+2] = '*';
			return;
		}
		
		// 시작점 계속 재귀
		recursion(n/3, x, y);
		recursion(n/3, x, y+n);
		recursion(n/3, x, y+2*n);
		
		recursion(n/3, x+n, y);
		//recursion(n/3, x+n, y+n); // 가운데는 안찍음
		recursion(n/3, x+n, y+2*n);
		
		recursion(n/3, x+2*n, y);
		recursion(n/3, x+2*n, y+n);
		recursion(n/3, x+2*n, y+2*n);
	}
}
