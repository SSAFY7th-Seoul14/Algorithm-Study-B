import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3109번. 빵집
public class BOJ3109_Bakery {
	static int r, c;
	static char[][] map;
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
		int result = 0;
		for (int i = 0; i < r; i++) {
			if (setPipe(i, 0)) // i행 0열부터 시작
				result++;
		}
		
		// 출력
		System.out.println(result);
	}
	
	public static boolean setPipe(int row, int col) {
		// 끝까지 도착하면 true 리턴
		if (col == c-1) {
			return true;
		}
		
		// 오른쪽은 무조건 가야하므로 col+1
		// 위 가운데 아래로 가니까 row-1 ~ row+1까지 반복
		// 근데 아래로 내려갈수록 다음 길을 막아버리므로. 위가 가능하면 무조건 위로 가는 것이 맞다. (그리디)
		boolean isFind = false;
		for(int i=row-1; i<=row+1; i++) {
			if (isAvailable(i, col+1)) 	{	// 갈 수 있으면 간다.
				map[i][col+1] = 'o'; 		// 한번 간 길은 방문처리한다. (빈칸 -> 길)
				isFind = setPipe(i, col+1);	// 다음 길 방문하고 도착가능한지 체크
				if (isFind)					// 도착했으면 다음 길은 안 봐도 됨
					break;
			}
		}
		return isFind;		// 이번 방문을 통해 끝까지 도착하는지
	}
	
	// 갈 수 있는 지 체크. 빈 칸인지
	public static boolean isAvailable(int row, int col) {
		if (c <= col || row < 0 || r <= row)
			return false;
		
		if (map[row][col] == '.')
			return true;
		
		return false;
	}
}
