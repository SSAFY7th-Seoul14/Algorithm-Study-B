import java.util.Scanner;

// 백준 2563번. 색종이
public class BOJ2563_ColorPaper {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 처리
		// 제한시간 1초. n 최대 100이므로 전부 다돌아도 충분
		int[][] map = new int[101][101];
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for(int j=0; j<10; j++) {
				for(int k=0; k<10; k++) {
					map[x+j][y+k] = 1;
				}
			}
		}
		
		// 출력
		int count = 0;
		for(int j=1; j<=100; j++) {
			for(int k=1; k<=100; k++) {
				if(map[j][k] == 1) {
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
}
