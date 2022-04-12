import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1929_소수구하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[n + 1];
		for (int i = 2; i <= n; ++i) {
			if (!visited[i]) {
				visited[i] = true;
				if (i >= m)
					sb.append(i).append('\n');
				for (int j = 2; j * i <= n; ++j) {
					visited[j * i] = true;
				}
			}
		}
		System.out.println(sb);
		br.close();
	}

}
