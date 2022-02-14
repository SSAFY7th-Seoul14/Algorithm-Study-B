import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SW Expert Academy 1210번. Ladder 1
public class SWEA1210_Ladder1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		int[] dy = {-1, 1}; // 좌, 우
		
		int[][] map = new int[100][100];
		int x = 99, y = 0;	// 시작 y좌표는 99부터, x좌표는 아래에서 탐색
		
		for (int i = 0; i < 10; i++) {
			// 초기화
			x = 99; y = 0;
			
			// 입력
			int t = Integer.parseInt(br.readLine());
			for(int j = 0; j < 100; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < 100; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());	
					
					if(map[j][k] == 2) // 시작 x좌표
						y = k;
				}
			}
			
			// 처리
			while(0<x) {
				// 좌우 이동
				for (int j = 0; j < 2; j++) {
					int ny = y + dy[j];
					boolean find = false;
					
					// 사다리가 이어져있으면 그 방향으로 계속 탐색
					while(0<=ny && ny<100 && map[x][ny] == 1) {
						find = true;
						ny += dy[j];
					}
					ny -= dy[j]; // 끝나면 넘어간 1칸을 다시 돌아온다.
					
					if (find) { // x좌표 이동 후 반대 방향 탐색 안 함
						y = ny;
						break;
					}
				}
				
				// 위로(y좌표) 이동
				x--;
			}
			
			// 출력
			System.out.println("#" + t + " " + y);
		}
	}
}
