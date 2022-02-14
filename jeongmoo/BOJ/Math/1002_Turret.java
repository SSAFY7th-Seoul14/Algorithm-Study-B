import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준1002번. 터렛
// 쉽게 생각해서 각 점에서 반지름이 r1, r2인 원을 그려서 교점의 개수를 찾는 문제!
// 근데 내접, 외접을 둘다 생각해야함.
public class BOJ1002_Turret {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int x1, y1, r1, x2, y2, r2;
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			r1 = Integer.parseInt(st.nextToken());
			
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			r2 = Integer.parseInt(st.nextToken());
			
			// 두 점 사이의 거리
			int diffX = Math.abs(x2-x1);
			int diffY = Math.abs(y2-y1);
			int diff = diffX*diffX + diffY*diffY; // 루트 안 씌우고 제곱해서 계산
			int sumR = (r1+r2)*(r1+r2);
			int minusR = (r1-r2)*(r1-r2);
			
			// 좌표가 같을 때
			if (diff == 0) {
				if (r1 != r2) { // 반지름이 다르면 접점 0
					System.out.println("0");
				} else { // 반지름이 같으면 무한대
					System.out.println("-1");
				}					
			} else {
				if (diff == minusR || diff == sumR) // 내접 or 외접
					System.out.println("1");
				else if (diff < minusR || sumR < diff) // 원 내부 or 원 밖
					System.out.println("0");
				else
					System.out.println("2");
			}
		}
	}
}
