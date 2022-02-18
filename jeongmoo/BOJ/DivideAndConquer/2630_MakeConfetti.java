import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2630번. 색종이 만들기
public class BOJ2630_MakeConfetti {	
	static int white = 0, blue = 0;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처리
		count(0, 0, n);
		
		// 출력
		System.out.println(white);
		System.out.println(blue);
	}
	
	public static void count(int x, int y, int size) {
		int start = map[x][y];
		boolean allSame = true;
outer:	for (int i = x; i < x+size; i++) {
			for (int j = y; j < y+size; j++) {
				if(start != map[i][j]) {
					allSame = false;
					break outer;
				}
			}
		}
		
		if (allSame) {
			if(start == 1)
				blue++;
			else
				white++;
			
			return;
		}
		
		int half = size/2;
		count(x, y, half);
		count(x+half, y, half);
		count(x, y+half, half);
		count(x+half, y+half, half);
	}
}
