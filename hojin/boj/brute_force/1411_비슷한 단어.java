import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1411_비슷한단어 {

	static char[][] words;
	static int len, n, ans;
	static int[] selected = { -1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 1 <= n <= 100
		n = Integer.parseInt(br.readLine());
		words = new char[n][];
		// 단어의 길이는 최대 50
		for (int i = 0; i < n; i++) {
			words[i] = br.readLine().toCharArray();
		}
		len = words[0].length;
		ans = 0;
		// 단어 길이는 같음, 중복 X, 소문자로만 이루어짐
		// 각 자리 수 조합만 같으면 비슷한 단어 쌍
		// 조합으로 비교
		combi(0, 0);
		System.out.println(ans);
	}

	private static void combi(int cnt, int start) {
		if (cnt == 2) {
			// 각 자리수 비교
			if (compare(selected[0], selected[1])) {
				++ans;
			}
			return;
		}
		for (int i = start; i < n; i++) {
			selected[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

	private static boolean compare(int from, int to) {
		// 매 비교 연산마다 방문 여부 check할 visited
		boolean[] visited = new boolean[len];
		// words[i]와 words[j]를 비교
		for (int i = 0; i < len - 1; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			// 맨 앞부터 둘이 동시에 자신의 문자열을 돌면서 자신과 같은 index 찾기
			for (int j = i + 1; j < len; j++) {
				if (visited[j])
					continue;
				// 자신과 같은 index가 발생했을 때
				if (words[from][i] == words[from][j]) {
					// 다른 문자열은 아니면 false 반환
					if (words[to][i] != words[to][j]) {
						return false;
					}
					visited[j] = true;
				} else if (words[to][i] == words[to][j]) {
					if (words[from][i] != words[from][j]) {
						return false;
					}
					visited[j] = true;
				}
			}
		}
		// 다 통과하고 나면 true 반환
		return true;
	}

}