import java.util.Stack;

class Solution {
	public int solution(int n, int k) {
		int answer = 0;
		// 입력된 n을 k진수로 변환
		String nk = nToK(n, k);
		int len = nk.length();
		StringBuilder sb = new StringBuilder();
		// 길이마다 돌면서
		for (int i = 0; i < len; ++i) {
			char charI = nk.charAt(i);
			// 0이고 sb에 내용이 있을 때 소수 판별 들어가기
			if (charI == '0' && sb.length() > 0) {
				long num = Long.parseLong(sb.toString());
				// 소수면
				if (isPrime(num))
					++answer;
				sb.setLength(0);
			} else if (charI != '0') {
				sb.append(charI);
			}
		}
		// 다 돌고 남았을 경우 따져줘야 한다.
		if (sb.length() > 0) {
			long num = Long.parseLong(sb.toString());
			if (isPrime(num))
				++answer;
		}
		return answer;
	}

	// long 범위 숫자가 들어올 수 있기 때문에 boolean 이용한 소수 판별로는 불가
	private boolean isPrime(long num) {
		if (num == 1)
			return false;
		for (long i = 2; i * i <= num; ++i) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	private String nToK(int n, int k) {
		Stack<Integer> stack = new Stack<>();
		while (n > 0) {
			stack.add(n % k);
			n /= k;
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
}
