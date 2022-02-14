import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1874 {

	static BufferedReader br;
	static StringBuilder sb;
	static Stack<Integer> stack;
	static int n, i, targetList[], val;
	// error 여부를 체크하기 위한 flag
	static boolean err;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		stack = new Stack<Integer>();
		n = Integer.parseInt(br.readLine());
		targetList = new int[n];
		for (i = 0; i < n; i++) {
			// 만들 targetList 생성
			targetList[i] = Integer.parseInt(br.readLine()); 
		}
		// stack에 집어넣을 val을 1로 선언해두기
		val = 1;
		// target에 대해서
		for (i = 0; i < n; i++) {
			// stack에 집어넣을 조건 설정
			while (targetList[i] >= val && val <= n) {
				// target값을 만들기 위해서는 해당 값까지는 stack에 넣어줘야 한다.
				stack.push(val++);
				sb.append("+\n");
			}
			if (stack.isEmpty()) {
				// targetList 생성 전에 stack이 비어있게 되면 err 케이스
				err = true;
				break;
			}
			if (targetList[i] == stack.peek()) {
				// targetList는 pop을 통해 생성
				stack.pop();
				sb.append("-\n");
			}
		}
		// err 상황이나 targetList를 다 돌았는데 stack내부에 값이 남아 있다면
		if (err || !stack.isEmpty()) {
			System.out.println("NO");
		} else {
			System.out.println(sb);
		}
	}
}