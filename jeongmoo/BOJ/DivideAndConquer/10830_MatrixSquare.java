import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 10830번. 행렬 제곱
public class BOJ10830_MatrixSquare {
	static int n;
	static int[][] matrix;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 계산
		int[][] result = square(b);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(result[i][j]%1000 + " ");
			}
			sb.append("\n");
		}
		// 2 1
		// 1000 1000
		// 1000 1000
		// 위 처럼 1제곱인 경우 %1000 연산이 안되므로 출력시에도 한번 더해준다.
		
		System.out.println(sb);
	}
	
	public static int[][] square(long b) {
		if (b == 1)
			return matrix;

		int[][] temp = square(b/2); // 같은수를 제곱하므로, 한번만 계산하면 된다.
		if (b%2 == 0)	// 짝수면
			return multiply(temp, temp);
		else			// 홀수면
			return multiply(multiply(temp, temp), matrix);
	}
	
	// 행렬 제곱 함수
	public static int[][] multiply(int[][] mat1, int[][] mat2) {
		int[][] squared = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					squared[i][j] += mat1[i][k]*mat2[k][j];
				}
				squared[i][j] %= 1000; // 1000이 넘어가면 나머지로
			}
		}
		return squared;
	}
}
