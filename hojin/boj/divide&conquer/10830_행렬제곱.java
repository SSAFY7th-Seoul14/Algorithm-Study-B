import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10830 {

	static int n, DIV = 1000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		long sqrNo = Long.parseLong(st.nextToken());

		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}
		br.close();
		int[][] rst = new int[n][n];
		// 결과 값을 온전히 받아내기 위해서 우선 E matrix로 초기화하고 계속 그곳에 곱해준다는 아이디어가 keypoint 같습니다.
		for (int i = 0; i < n; i++) {
			rst[i][i] = 1;
		}
		// sqrNo를 이진화 했을 때,
		while (sqrNo > 0) {
			// 1로 나타나는 부분 즉, 2로 나눴을 때 1이 될 때 해당하는 제곱수가 결과에 곱해져야 한다.
			if (sqrNo % 2 == 1) {
				rst = matMul(rst, a);
			}
			// 각 횟수마다 a의 제곱을 늘려가면 된다.
			a = matMul(a, a);
			sqrNo >>= 1;
		}
		for (int[] row : rst) {
			for (int el : row) {
				sb.append(el).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int[][] matMul(int[][] aMat, int[][] bMat) {
		int[][] matSqrd = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					matSqrd[i][j] += aMat[i][k] * bMat[k][j];
				}
				matSqrd[i][j] %= DIV;
			}
		}
		return matSqrd;
	}

}