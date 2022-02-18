import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1780번. 종이의 개수
public class BOJ1780_NumberOfPapers {	
	static int minus = 0, zero = 0, plus = 0;
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
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(plus);
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
			if(start == -1)
				minus++;
			else if(start == 0)
				zero++;
			else
				plus++;
			
			return;
		}
		
		int dist = size/3;
		count(x, y, dist);
		count(x+dist, y, dist);
		count(x+dist*2, y, dist);
		
		count(x, y+dist, dist);
		count(x+dist, y+dist, dist);
		count(x+dist*2, y+dist, dist);
		
		count(x, y+dist*2, dist);
		count(x+dist, y+dist*2, dist);
		count(x+dist*2, y+dist*2, dist);
	}
}
