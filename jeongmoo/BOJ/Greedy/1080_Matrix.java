import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

// 백준 1080번. 행렬
public class BOJ1080_Matrix {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 행
		int m = Integer.parseInt(st.nextToken()); // 열
		
		int[][] origin = new int[n][m];
		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				origin[i][j] = line[j];
			}
		}
		
		int[][] target = new int[n][m];
		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				target[i][j] = line[j];
			}
		}
		
		// 좌상부터 뒤집으면서 간다.
		int result = 0;
		for (int i = 0; i <= n-3; i++) {
			for (int j = 0; j <= m-3; j++) {
				// 다르면 뒤집기
				if (origin[i][j] != target[i][j]) {
					change(origin, i, j);
					result++;
				}
				
				// 열이 끝에 도달하면 뒤를 체크한다.
				if (j == m-3) {
					if (origin[i][m-2] != target[i][m-2] || origin[i][m-1] != target[i][m-1]) {
						System.out.println("-1");
						return;
					}
				}
				
				// 행이 끝이 도달하면 아래도 체크한다.
				if (i == n-3) {
					if (origin[n-2][j] != target[n-2][j] || origin[n-1][j] != target[n-1][j]) {
						System.out.println("-1");
						return;
					}
				}
				
				// 행, 열이 둘다 마지막이면 체크 안되는 뒤에 4칸도 체크한다.
				if (i == n-3 && j == m-3) {
					if (origin[n-2][m-2] != target[n-2][m-2] || origin[n-2][m-1] != target[n-2][m-1] 
						|| origin[n-1][m-2] != target[n-1][m-2] || origin[n-1][m-1] != target[n-1][m-1]) {
						System.out.println("-1");
						return;
					}
				}
			}
		}
		
		// n, m이 3 이하여서 뒤집는게 불가능한 경우 같은지 체크한다.
		if (result == 0) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (origin[i][j] != target[i][j]) {
						System.out.println("-1");
						return;
					}
				}
			}
		}
		
		System.out.println(result);
	}
	
	public static void change(int[][] map, int x, int y) {
		for (int i = x+0; i < x+3; i++) {
			for (int j = y+0; j < y+3; j++) {
				if (map[i][j] == '0')
					map[i][j] = '1';
				else
					map[i][j] = '0';
			}
		}
	}
}
