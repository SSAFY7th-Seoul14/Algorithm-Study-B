import java.io.*;
import java.util.*;

public class BOJ2629_양팔저울 {
	static int[] weights;
	static boolean[][] dp = new boolean[31][15001];
	// 연산값들을 담아줄 set
	static HashSet<Integer> set = new HashSet<Integer>();
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringToInteger 함수 sToi
		// 입력 처리
		n = sToi(br.readLine());
		weights = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			weights[i] = sToi(st.nextToken());
		}
		// 모든 부분 집합 봐주기
		// 하지만 이 방법은 최대 3^30번까지 연산하기에 시간 초과 발생
		subSet(0, 0);
		int cntCheck = sToi(br.readLine());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cntCheck; ++i) {
			// 모든 부분 집합을 돌고 온 set에 입력한 값이 있을 경우 'Y', 없으면 'N'
			sb.append(set.contains(sToi(st.nextToken())) ? 'Y' : 'N').append(' ');
		}
		System.out.println(sb);
		br.close();
	}

	private static void subSet(int i, int accum) {
		if (i > n || dp[i][accum]) {
			// 넘어온 연산 값이 0보다 클 때만 set에 넣어주기
			return;
		}
		dp[i][accum] = true;
		set.add(accum);
		// 왼쪽에 두는 경우
		subSet(i + 1, Math.abs(accum - weights[i]));
		// 연산에 포함하지 않는 경우
		subSet(i + 1, accum);
		// 오른쪽에 두는 경우
		subSet(i + 1, accum + weights[i]);
	}

	private static int sToi(String str) {
		return Integer.parseInt(str);
	}

}