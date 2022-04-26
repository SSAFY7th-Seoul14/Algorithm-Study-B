import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 19542번. 전단지 돌리기
public class BOJ19542_FlyerDistribution {
	static int[] depth;
	static List<Integer>[] list;
	static int n, s, d;
	static int result = 0;
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		depth = new int[n+1];
		list = new List[n+1];
		for (int i = 1; i < n+1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list[from].add(to);
			list[to].add(from);
		}
		
		// dfs
		dfs(s, -1);
		System.out.println(result*2);
	}
	
	public static int dfs(int v, int parent) {
		for (int next : list[v]) {
			if (next == parent)
				continue;
			depth[v] = Math.max(depth[v], dfs(next, v)+1);
		}
		
		if (v != s && depth[v] >= d) {
			result++;
		}
		
		return depth[v];
	}
}
