import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 1213번. 팰린드롬 만들기
public class BOJ1213_MakePalindrome {
	static String result = "I'm Sorry Hansoo";
	static int[] alpha;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin = br.readLine();
		
		alpha = new int[26];
		for (char c : origin.toCharArray()) {
			alpha[c-'A']++;
		}
		
		boolean check = false;
		int odd = -1;
		for (int i = 0; i < 26; i++) {
			if (alpha[i]%2 == 1) {
				if (!check) {
					check = true;
					odd = i;
				} else { // 홀수개인 알파벳이 2개이상이면 팰린드롬을 만들수 없다
					System.out.println(result);
					return;
				}
			}
		}
		
		// 사전순으로 개수가 짝수인 것들을 더한다.
		result = "";
		for (int i = 0; i < 26; i++) {
			while(alpha[i]>1) {
				alpha[i] -= 2;
				result += (char)('A'+i);
			}
		}
		
		// 원본을 뒤집는다.
		StringBuilder sb = new StringBuilder();
		String reverse = sb.append(result).reverse().toString();
		
		// 홀수가 존재하면 앞+홀수알파벳 더한다.
		if (check) {
			result += (char)('A'+odd);
		}

		// 앞+뒤
		System.out.println(result+reverse);
	}
}
