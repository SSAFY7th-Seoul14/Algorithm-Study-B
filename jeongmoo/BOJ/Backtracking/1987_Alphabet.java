import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1987번. 알파벳
public class BOJ1987_Alphabet {
	static int r, c;
	static boolean isSelected[] = new boolean[26];
	static char[][] map;
	
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static int max = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		// 위치
		map = new char[r][c];
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 계산
		isSelected[map[0][0]-'A'] = true;
		check(0,0);
		
		// 출력
		System.out.println(max);
	}
	
	public static void check(int row, int col) {
		boolean isMovable = false;
		// 사방 탐색
		for (int i = 0; i < 4; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			
			// 이동가능하면
			if (isAvailable(nx, ny)) {
				isMovable = true;
				// 해당 알파벳 사용체크하고 재귀
				isSelected[map[nx][ny] - 'A'] = true;
				check(nx, ny);
				isSelected[map[nx][ny] - 'A'] = false;
			}
		}
		
		// 이동할 곳이 없다면 거기서 count 체크하여 max 갱신
		if(!isMovable) {
			int count = 0;
			for (int i = 0; i < 26; i++) {
				if (isSelected[i]) {
					count++;
				}
			}
			max = Math.max(max, count);
		}
	}
	
	// 범위체크 및 지나온 알파벳 체크
	public static boolean isAvailable(int row, int col) {
		if (col < 0 || c <= col || row < 0 || r <= row)
			return false;
		
		if (isSelected[map[row][col] - 'A'])
			return false;
		
		return true;
	}
}
