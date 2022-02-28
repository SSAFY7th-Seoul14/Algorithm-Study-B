import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2567번. 색종이2
public class BOJ2567_Confetti2 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[102][102]; // 1~100까지만 사용. 0번, 101번 인덱스는 항상 0으로 벽에 붙은 색종이도 처리
		
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int k = y; k < y+10; k++) {
				for (int j = x; j < x+10; j++) {
					map[k][j] = 1;
				}
			}
		}
		
		// 처리
		int result = 0;
		for (int k = 1; k <= 100; k++) {
			for (int j = 1; j <= 100; j++) {
				// 색종이 일 때
				// 상하좌우를 확인하여 비어있으면 그쪽은 선이 있으므로 둘레 1을 더한다.
				if (map[k][j] == 1) {
					if (k>=1 && map[k-1][j]==0)
						result++;
					if (k<=100 && map[k+1][j]==0)
						result++;
					if (j>=1 && map[k][j-1]==0)
						result++;
					if (j<=100 && map[k][j+1]==0)
						result++;
				}
			}
		}
		
		// 출력
		System.out.println(result);
	}
}

