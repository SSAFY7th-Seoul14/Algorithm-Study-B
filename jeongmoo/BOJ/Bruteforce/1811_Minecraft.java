import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1811번. 마인크래프트
public class BOJ1811_Minecraft {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		int min = 256;
		int total = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
				if (map[i][j] < min)
					min = map[i][j];
			}
		}
		
		// 최소시간일때 최대높이를 출력해야하는 문제임...!
		// n*m = 25만 * 256층 = 1초 충분
		// 가장 낮은 땅에서부터 올라가보자.
		
		// 깎기 2초, 쌓기 1초
		int count = n*m;
		int minTime = Integer.MAX_VALUE;
		int height = min;
		for (int floor = min; floor <= 256; floor++) {
			if (total+b < count*floor)
				break;
			
			int time = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					int diff = floor - map[i][j];
					if (diff >= 0)
						time += diff;
					else
						time -= 2*diff;
				}
			}
			
			if(time <= minTime) {
				minTime = time;
				height = floor;
			}			
		}
		
		System.out.println(minTime + " " + height);
	}
}
