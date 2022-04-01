import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 17609번. 회문
public class BOJ17609_Palindrome {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			int result = StringCheck(str);
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int StringCheck(String str) {
		StringBuilder sb = new StringBuilder();
		String reverse = sb.append(str).reverse().toString();
		
		if (str.equals(reverse))
			return 0;
		
		int size = str.length();
		for (int i = 0; i < size; i++) {
			if (str.charAt(i) != reverse.charAt(i)) { 
				// 다른 인덱스
				// 기존 문자열에서 i인덱스를 빼본다.
				String f1 = str.substring(0, i) + str.substring(i+1, size);
				String f2 = reverse.substring(0, size-i-1) + reverse.substring(size-i, size);
				if (f1.equals(f2))
					return 1;
				
				// 반전 문자열에서 i인덱스를 빼본다.
				String s1 = reverse.substring(0, i) + reverse.substring(i+1, size);
				String s2 = str.substring(0, size-i-1) + str.substring(size-i, size);
				if (s1.equals(s2))
					return 1;
				
				return 2;
			}
		}
		return 2;
	}
}
