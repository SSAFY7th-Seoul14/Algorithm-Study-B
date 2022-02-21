import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;

		int set = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int cmdNum = 0;
			String cmd = st.nextToken();
			if (st.hasMoreTokens()) {
				cmdNum = Integer.parseInt(st.nextToken());
			}
			switch (cmd) {
			case "add":
				set = set | 1 << cmdNum;
				break;
			case "remove":
				if ((set & 1 << cmdNum) != 0) {
					set = set ^ 1 << cmdNum;
				}
				break;
			case "check":
				if ((set & 1 << cmdNum) != 0) {
					sb.append("1\n");
				} else {
					sb.append("0\n");
				}
				break;
			case "toggle":
				if ((set & 1 << cmdNum) != 0) {
					set = set ^ 1 << cmdNum;
				} else {
					set = set | 1 << cmdNum;
				}
				break;
			case "all":
				set = (int) Math.pow(2, 21) - 1;
				break;
			case "empty":
				set = 0;
				break;
			}
		}
		System.out.println(sb);
	}
}