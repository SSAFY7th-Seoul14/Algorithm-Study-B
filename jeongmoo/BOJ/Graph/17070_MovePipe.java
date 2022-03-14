import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준17070번. 파이프 옮기기 1
public class BOJ17070_MovePipe {
	static int result = 0;
	static int n;
	static int[][] data;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		data = new int[n][n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		setPipe(0, 0, 0);
		
		System.out.println(result);
	}

	// dir : 0 가로, 1 세로, 2 대각선
	// prev : 이전좌표, dir : 현재 이동해야하는 방향
	public static void setPipe(int x, int y, int dir) {	
		// 먼저 이동
		int nextX = n;
		int nextY = n;
		switch (dir) {
		case 0:
			nextX = x;
			nextY = y+1;
			break;
		case 1:
			nextX = x+1;
			nextY = y;
			break;
		case 2:
			nextX = x+1;
			nextY = y+1;
			if (!rangeCheck(x, y+1) || !rangeCheck(x+1, y))
				return;
			
			break;

		default:
			break;
		}
		
		if (!rangeCheck(nextX, nextY))
			return;
		
		// 마지막에 도달하면 카운트
		if(nextX==n-1 && nextY==n-1) {
			result++;
			return;
		}
		
		// 재귀
		switch (dir) {
		case 0:	
			setPipe(nextX, nextY, 0);
			setPipe(nextX, nextY, 2);
			break;
		case 1:
			setPipe(nextX, nextY, 1);
			setPipe(nextX, nextY, 2);
			break;
		case 2:
			for (int i = 0; i < 3; i++) {
				setPipe(nextX, nextY, i);
			}
			break;

		default:
			break;
		}
	}
	
	public static boolean rangeCheck(int x, int y) {
		if (x<0 || y<0 || x>=n || y>=n || data[x][y] == 1)
			return false;
		return true;
	}
}
