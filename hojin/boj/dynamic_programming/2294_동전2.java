import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class BOJ2294_동전2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coins = new int[n];
		for (int i = 0; i < n; ++i) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(coins);
		// k보다 큰 값은 과정에 포함시키지 않기 위해 정렬 후 rangeCheck
		int range = -1;
		for (int i = n - 1; i >= 0; --i) {
			// k와 같은 값이 있다면 1개만으로 충분
			if (coins[i] == k) {
				range = n;
				break;
				
			}
			// k보다 작은 값이 발생한 범위까지만 가져가면 된다.
			else if (coins[i] < k) {
				range = i;
				break;
			}
		}
		// k보다 작은 값이 없을 경우 -1
		if (range == -1) {
			System.out.println("-1");
		} else if (range == n) {
			System.out.println("1");
		} else {
			// 경우의 수 담을 dp
			int[] dp = new int[k + 1];
			// 최악으로 1만 주어져도 10000번이 최대이므로 10001로 채워두고 dp 시작
			Arrays.fill(dp, 10001);
			// 0이 될 가능성은 0
			dp[0] = 0;
			// 순서 기억 하는 set 이용
			LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
			for (int i = 0; i <= range; ++i) {
				set.add(coins[i]);
			}
			// 각 값에 대해서
			for (Integer integer : set) {
				// dp 점화식 수행
				for (int i = integer; i <= k; ++i) {
					//
					dp[i] = Math.min(dp[i - integer] + 1, dp[i]);
				}
			}
			// dp 다 돌고도 값이 갱신되지 않았다면 만들 수 없는 것
			System.out.println(dp[k] == 10001 ? "-1" : dp[k]);
		}
	}

}