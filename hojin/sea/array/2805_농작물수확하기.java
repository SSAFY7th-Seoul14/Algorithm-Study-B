// sb append로만 붙이기 20,444 kb / 113 ms
// sb +로 붙이기  21,428 kb / 115 ms
// bw write로 붙이는게  더 오래걸림
// 30분
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA2805 {
	static BufferedReader br;
	static StringBuilder sb;
	static int T, ans, n, grid[][], l, r, i, j;
	static char[] temp;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			l = r = n / 2; // 두개의 포인터 사용
			ans = 0; // 정답 담을 ans
			flag = false; // 최대까지 늘어났을 때 알려줄 flag
			grid = new int[n][n];
			for (i = 0; i < n; i++) {
				temp = br.readLine().toCharArray(); // 입력받고 charArray화
				for (j = 0; j < n; j++) {
					grid[i][j] = temp[j] - '0';
				}
				
				for (j = l; j <= r; j++) {
					ans += grid[i][j]; // l부터 r까지 더해주기
				}
				if (flag) { // 최대 이후로는 더하는 범위가 줄어든다.
					l++;
					r--;
				} else { // 최대 전까지는 더하는 범위가 늘어난다.
					l--;
					r++;
					if (l == 0) // 최대에 도달하면 l포인터는 0이고 flag에 알려준다.
						flag = true;
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n"); // 그냥 +보다 append로 붙이는게 빠르다.
		}
		br.close();
		System.out.println(sb);
	}
}

// 이하 반 사람들 아이디어를 참고해 혼자 줄여본 코드
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA2805_class {
	static BufferedReader br;
	static StringBuilder sb;
	static int T, ans, n, grid[][], d, i, j, half;
	static String temp;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			ans = 0;
			grid = new int[n][n];
			for (i = 0; i < n; i++) {
				temp = br.readLine();
				d = Math.abs(n / 2 - i);
				for (j = 0; j < n; j++) {
					grid[i][j] = temp.charAt(j) - '0';
					if (j >= d && j < n - d)
						ans += grid[i][j];
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		br.close();
		System.out.println(sb);
	}
}