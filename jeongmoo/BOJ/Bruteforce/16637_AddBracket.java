import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준16637번. 괄호 추가하기
public class BOJ16637_AddBracket {
	static int result = Integer.MIN_VALUE;
	static int n;
	static char[] data;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		data = br.readLine().toCharArray();
		
		// 괄호를 씌우거나 안씌우거나 둘중 하나.
		calc(1, data[0]-'0', 0, '0', false);
		calc(1, 0, data[0]-'0', '+', true);
		
		System.out.println(result);
	}
	
	public static void calc(int idx, int sum, int temp, char oper, boolean bracket) {
		if (idx == n) {
			if (!bracket) {
				result = Math.max(result, sum);
			}
			return;
		}
		
		char operator = data[idx];
		int num = data[idx+1] - '0';
		switch (operator) {
		case '+':
			if (bracket)
				calc(idx+2, calc2(sum, temp+num, oper), 0, '0', false);
			else {
				calc(idx+2, sum+num, 0, '0', false);
				calc(idx+2, sum, num, operator, true);
			}
			break;
		case '-':
			if (bracket)
				calc(idx+2, calc2(sum, temp-num, oper), 0, '0', false);
			else {
				calc(idx+2, sum-num, 0, '0', false);
				calc(idx+2, sum, num, operator, true);
			}
			break;
		case '*':
			if (bracket)
				calc(idx+2, calc2(sum, temp*num, oper), 0, '0', false);
			else {
				calc(idx+2, sum*num, 0, '0', false);
				calc(idx+2, sum, num, operator, true);
			}
			break;

		default:
			break;
		}
	}
	
	public static int calc2(int num1, int num2, char oper) {
		switch (oper) {
			case '+':
				return num1+num2;
			case '-':
				return num1-num2;
			case '*':
				return num1*num2;
			default :
				return 0;
		}
	}
}
