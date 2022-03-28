import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920_수찾기 {

	static int n;
	static int[] A;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 1<= <=100,000
		n = Integer.parseInt(br.readLine());
		A = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		// 1<= <= 100,000
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; ++i) {
			bw.write(String.valueOf(binarySearch(Integer.parseInt(st.nextToken()))));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static int binarySearch(int target) {
		int left = 0;
		int right = n - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (A[mid] == target) return 1;
			else if (A[mid] < target) left = mid + 1;
			else right = mid - 1;
		}
		return 0;
	}

}