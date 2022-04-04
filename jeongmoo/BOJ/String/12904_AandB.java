import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 12904번. A와 B
public class BOJ12904_AandB {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start = br.readLine();
		String end = br.readLine();
		
		// 역으로 간다.
		int size = start.length();
		StringBuilder sb = new StringBuilder();
		while (size < end.length()) {
			// 마지막 글자가 A면 그냥 지운다.
			if (end.charAt(end.length()-1) == 'A') {
				end = end.substring(0, end.length()-1);
			} else {
				sb.setLength(0);
				end = sb.append(end.substring(0, end.length()-1)).reverse().toString();
			}
		}
		
		if (start.equals(end))
			System.out.println("1");
		else
			System.out.println("0");
	}
}
