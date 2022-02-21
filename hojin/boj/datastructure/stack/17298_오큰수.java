import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] numArr = new int[n];
		// 오른쪽 방향으로 비교를 해주는 경우이기 때문에 array에 넣어준 후 처리해야 한다. 입력과 동시에 처리 불가능
		for (int i = 0; i < n; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		// 오큰수를 담아 둘 stack
		Stack<Integer> stack = new Stack<Integer>();
		// 정답을 담아둘 array
		int[] ans = new int[n];

		// 가장 오른쪽 정답은 -1일 수밖에 없음
		ans[n - 1] = -1;
		// 우선 가장 오른쪽 값을 오큰수에 담아둔다.
		stack.push(numArr[n - 1]);
		// 왼쪽으로 돌아가면서 비교
		for (int i = n - 2; i >= 0; i--) {
			// stack이 비어 있으면 if 문 비교 불가
			while (!stack.isEmpty()) {
				// 해당 위치 값이 stack top보다 작으면 해당 위치의 오큰수는 top값이 된다.
				int top = stack.peek();
				if (numArr[i] < top) {
					ans[i] = top;
					// ans를 담았으므로 왼쪽 값으로 이동하기 위해 while문 탈출
					break;
				}
				// 오큰수 후보인 top값보다 현재 값이 크다면 더이상 유효한 오큰수 후보가 아니므로 pop해준다
				// 이 과정을 통해 stack이 빈다면 자연스레 while문 탈출한다
				else {
					stack.pop();
				}
			}
			// stack이 비어있다면 해당 위치 오큰수는 없는 것이므로 -1
			if (stack.isEmpty()) {
				ans[i] = -1;
			}
			// 다음 값인 왼쪽 입장에서 현재 값과 비교가 되려면 우선 stack에 담아줘서 오큰수 후보로 만들어줘야 한다.
			stack.push(numArr[i]);
		}
		StringBuilder sb = new StringBuilder();
		for (int el : ans) {
			sb.append(el).append(" ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}