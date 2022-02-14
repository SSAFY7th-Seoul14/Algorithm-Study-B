import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11723번. 집합
public class BOJ11723_Set {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int bitmask = 0;
		int x = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("add") || command.equals("remove")|| command.equals("check")|| command.equals("toggle"))
				x = Integer.parseInt(st.nextToken()) - 1;
			
			switch (command) {
			case "add":
				bitmask |= (1<<x);
				break;
			case "remove":
				bitmask &= ~(1<<x); // 해당 자리를 1로 만든 후 not으로 뒤집어 버린 후에 &를 하면 해당 비트만 0이 된다.
				break;
			case "check":
				if ((bitmask & (1<<x)) != 0)
					sb.append("1\n");
				else
					sb.append("0\n");
				break;
			case "toggle":
				bitmask ^= (1<<x);
				break;
			case "all":
				bitmask = (1<<20)-1; // -1로 초기화하여 전부 1로 만드는 방법도 있다.
				break;
			case "empty":
				bitmask = 0;
				break;
			default:
				break;
			}
		}
		
		System.out.println(sb);
	}
}
