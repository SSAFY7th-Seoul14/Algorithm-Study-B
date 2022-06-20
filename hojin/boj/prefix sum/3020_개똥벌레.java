import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3020_개똥벌레 {

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = stoi(st.nextToken());
		int h = stoi(st.nextToken());
		int[] top = new int[h];
		int[] bottom = new int[h];
		int[] heights = new int[h];
		for (int i = 0; i < n / 2; i++) {
			int b = stoi(br.readLine());
			int t = stoi(br.readLine());
			bottom[b - 1]++;
			top[h - t]++;
		}
		for (int i = h - 1; i > 0; i--) {
			bottom[i - 1] += bottom[i];
		}
		for (int i = 1; i < h - 1; i++) {
			top[i + 1] += top[i];
		}
		for (int i = 0; i < h; i++) {
			heights[i] = top[i] + bottom[i];
		}
		Arrays.sort(heights);
		int min = heights[0];
		int cnt = 0;
		for (int i : heights) {
			if (i > min)
				break;
			cnt++;
		}
		System.out.println(min + " " + cnt);
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
