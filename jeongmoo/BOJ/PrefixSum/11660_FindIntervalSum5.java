import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11659번. 구간 합 구하기 5
public class BOJ11660_FindIntervalSum5 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] data = new int[n+1][n+1];
		int[][] sum = new int[n+1][n+1]; // 행별로 구간합을 저장하여 2차원으로 저장

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
				sum[i][j] = sum[i][j-1] + data[i][j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());

			int result = 0;
			for (int j = startX; j <= endX; j++) {
				result += sum[j][endY] - sum[j][startY-1];
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}
