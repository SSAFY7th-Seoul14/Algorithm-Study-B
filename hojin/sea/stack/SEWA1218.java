import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	static int caseLength;
	static Stack<String> stack;
	static String[] eachCase;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10, ans;
		for (int tc = 1; tc <= T; tc++) {
			stack = new Stack<String>();
			caseLength = Integer.parseInt(in.readLine());
			eachCase = in.readLine().split("");
			ans = pairing();
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static int pairing() {
		for (int i = 0; i < caseLength; i++) {
			switch (eachCase[i]) {
			case "(":
			case "{":
			case "[":
			case "<":
				stack.push(eachCase[i]);
				break;
			case ")":
				if (!stack.empty()&&!stack.pop().equals("(")) return 0;
				
				break;
			case "}":
				if (!stack.empty()&&!stack.pop().equals("{")) return 0;
				break;
			case "]":
				if (!stack.empty()&&!stack.pop().equals("[")) return 0;
				break;
			case ">":
				if (!stack.empty()&&!stack.pop().equals("<")) return 0;
				break;
			}
		}
		return 1;
	}
}

