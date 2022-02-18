import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2740번. 행렬 곱셈
public class BOJ2740_MatrixMultiplication {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arrA = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arrA[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int m2 = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] arrB = new int[m2][k];
		for (int i = 0; i < m2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < k; j++) {
				arrB[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처리
		int[][] result = new int[n][k];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				for (int a = 0; a < m; a++) {					
					result[i][j] += arrA[i][a] * arrB[a][j];
				}
			}
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				sb.append(result[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
