import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 15566번. 개구리 1
public class BOJ15566_Frog1 {
	static int n;
	static int[][] frog;	// 1~4
	static int[][] prefer;	// 0~n-1
	static int[][] connect;	// 0~n-1
	
	static boolean isAvailable = false;
	static int[] isSelected; // 해당 자리에 있는 개구리 번호
	static String answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		isSelected = new int[n];
		
		// 개구리 정보
		frog = new int[n][5]; // 1~4로 맞추기 위해
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 4; j++) {
				frog[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 선호정보
		prefer = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				prefer[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 연결정보
		connect = new int[n][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int dialog = Integer.parseInt(st.nextToken());
			connect[from][to] = connect[to][from] = dialog;
		}
		
		permutation(0);
		
		if (isAvailable) {
			System.out.println("YES");
			System.out.println(answer);
		} else {
			System.out.println("NO");
		}
	}
	
	public static void permutation(int idx) {
		if (idx == n) {
			isAvailable = true;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n; i++) {
				sb.append(isSelected[i] + " ");
			}
			answer = sb.toString();
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (isSelected[i] > 0) continue; // 이미 다른 개구리가 선택했으면
			
			// idx번 개구리가 선호하는 자리가 아니면 넘긴다.
			boolean isPrefer = false;
			for (int j = 0; j < 2; j++) {
				if (prefer[idx][j] == i+1) {
					isPrefer = true;
					break;
				}
			}
			if (!isPrefer) continue;
			
			// 현재 자리(i)와 연결된 자리 중에 개구리가 있으면 관심사가 일치하는지 체크한다.
			boolean talkable = true;
			for (int j = 0; j < n; j++) {
				int interest = connect[i][j];
				// 연결되어 있고 j번 자리에 개구리가 있으면
				if (interest > 0 && isSelected[j] > 0) {
					int jFrog = isSelected[j]-1; // j번 자리에 있는 개구리
					if (frog[idx][interest] != frog[jFrog][interest]) {
						talkable = false;
						break;
					}
				}
			}
			if (!talkable) continue;
			
			isSelected[i] = idx+1;
			permutation(idx+1);
			isSelected[i] = 0;
		}
	}
}