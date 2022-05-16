import java.io.*;
import java.util.*;

public class BOJ14719_빗물 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = stoi(st.nextToken());
		int w = stoi(st.nextToken());
		int[] tray = new int[w];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < w; ++i) {
			tray[i] = stoi(st.nextToken());
		}
		int answer = 0;
		int lIndex = 0;
		int rIndex = 0;
		for (int i = 1; i < w; ++i) {
			int lMax = 0;
			int rMax = 0;
			for (int j = i; j >= rIndex; --j) {
				if (lMax <= tray[j]) {
					lMax = tray[j];
					lIndex = j;
				}
			}
			for (int j = i; j < w; ++j) {
				if (rMax <= tray[j]) {
					rMax = tray[j];
					rIndex = j;
				}
				if (lMax <= rMax)
					break;
			}
			int wall = Math.min(lMax, rMax);
			for (int j = lIndex; j <= rIndex; ++j) {
				int diff = wall - tray[j];
				answer += diff > 0 ? diff : 0;
			}
			i = rIndex;
		}
		System.out.println(answer);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
