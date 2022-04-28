import java.util.*;
import java.io.*;

public class BOJ2096_내려가기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = stoi(br.readLine());
		int[][] dpMax = new int[2][3];
		int[][] dpMin = new int[2][3];
		st = new StringTokenizer(br.readLine());
		dpMax[0] = new int[] { stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()) };
		dpMin[0][0] = dpMax[0][0];
		dpMin[0][1] = dpMax[0][1];
		dpMin[0][2] = dpMax[0][2];
		for (int i = 1; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int cur0 = stoi(st.nextToken());
			int cur1 = stoi(st.nextToken());
			int cur2 = stoi(st.nextToken());
			int cur = i % 2;
			int prev = (i - 1) % 2;
			int prevMax0 = Math.max(dpMax[prev][0], dpMax[prev][1]);
			int prevMax1 = Math.max(prevMax0, dpMax[prev][2]);
			int prevMax2 = Math.max(dpMax[prev][2], dpMax[prev][1]);
			dpMax[cur][0] = prevMax0 + cur0;
			dpMax[cur][1] = prevMax1 + cur1;
			dpMax[cur][2] = prevMax2 + cur2;
			int prevMin0 = Math.min(dpMin[prev][0], dpMin[prev][1]);
			int prevMin1 = Math.min(prevMin0, dpMin[prev][2]);
			int prevMin2 = Math.min(dpMin[prev][2], dpMin[prev][1]);
			dpMin[cur][0] = prevMin0 + cur0;
			dpMin[cur][1] = prevMin1 + cur1;
			dpMin[cur][2] = prevMin2 + cur2;
		}
		int last = (n - 1) % 2;
		System.out.printf("%d %d", Math.max(Math.max(dpMax[last][0], dpMax[last][1]), dpMax[last][2]),
				Math.min(Math.min(dpMin[last][0], dpMin[last][1]), dpMin[last][2]));
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
