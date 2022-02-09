import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 중요한 포인트는 입력할 때마다 비교를 수행하는 것 -> 시간 복잡도를 줄이는 방법이 된다.
public class BOJ2493 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(in.readLine()); // 비교할 높이의 개수
		Stack<int[]> stackH = new Stack<int[]>(); // 비교할 높이를 담아 줄 stack
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] currentH, topH;
		for (int i = 1; i <= n; i++) {
			// 사용해줄 객체 { 높이, index }
			currentH = new int[] { Integer.parseInt(st.nextToken()), i };
			// stack이 비어있지 않을 때 == 현재 높이랑 비교해줄 높이가 stack 안에 있다는 것
			while (!stackH.isEmpty()) {
				topH = stackH.peek(); // stack top
				// 현재 높이보다 top이 높으면 peek의 index를 sb.append
				if (currentH[0] < topH[0]) {
					sb.append(topH[1] + " ");
					// 그 외의 stack 안의 값은 비교할 필요가 없으므로 탈출
					break;
				}
				stackH.pop();
			}
			// stack이 비어있으면 0 넣기
			if (stackH.isEmpty())
				sb.append("0 ");
			stackH.push(currentH);
		}
		in.close();
		System.out.println(sb);
	}
}

