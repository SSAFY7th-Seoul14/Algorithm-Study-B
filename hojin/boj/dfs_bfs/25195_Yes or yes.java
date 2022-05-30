import java.io.*;
import java.util.*;

public class BOJ25195_Yesoryes {

	static boolean[] fans;
	static boolean yes = false;
	static ArrayList<Integer>[] link;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = stoi(st.nextToken());
		link = new ArrayList[n + 1];
		fans = new boolean[n + 1];
		for (int i = 1; i <= n; ++i)
			link[i] = new ArrayList<Integer>();
		int m = stoi(st.nextToken());
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			link[stoi(st.nextToken())].add(stoi(st.nextToken()));
		}
		int S = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < S; ++i)
			fans[stoi(st.nextToken())] = true;
		dfs(1, fans[1]);
		if (yes)
			System.out.println("yes");
		else
			System.out.println("Yes");
	}

	private static void dfs(int no, boolean YES) {
		if (link[no].isEmpty()) {
			yes = !(YES || fans[no]);
			return;
		}
		for (Integer next : link[no]) {
			dfs(next, YES || fans[next]);
			if (yes)
				return;
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
