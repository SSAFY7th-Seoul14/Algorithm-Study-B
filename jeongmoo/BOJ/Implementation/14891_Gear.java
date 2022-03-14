import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14891번. 톱니바퀴
public class BOJ14891_Gear {
	static char[][] gearList; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gearList = new char[4][];
		for (int i = 0; i < 4; i++) {
			gearList[i] = br.readLine().toCharArray();
		}
		
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int gearNo = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			moveGear(gearNo-1, dir, true, true);
		}
		
		// 점수계산
		int result = 0;
		int score = 1;
		for (int i = 0; i < 4; i++) {
			if (gearList[i][0] == '1') {
				result += score;
			}
			score *= 2;
		}		
		
		System.out.println(result);
	}
	
	public static void moveGear(int gearNo, int dir, boolean leftCheck, boolean rightCheck) {
		int left = gearList[gearNo][6];
		int right = gearList[gearNo][2];
		rotate(gearNo, dir);
		
		// 좌우비교
		if (leftCheck && 0 < gearNo) {
			if (gearList[gearNo-1][2] != left) {
				moveGear(gearNo-1, dir*-1, true, false);
			}
		}
		if (rightCheck && gearNo < 3) {
			if (gearList[gearNo+1][6] != right) {
				moveGear(gearNo+1, dir*-1, false, true);
			}
		}
	}
	
	public static void rotate(int gearNo, int dir) {
		if (dir == 1) { // 시계
			char temp = gearList[gearNo][7];
			for (int i = 6; i >= 0; i--) {
				gearList[gearNo][i+1] = gearList[gearNo][i]; 
			}
			gearList[gearNo][0] = temp;
		} else { // 반시계
			char temp = gearList[gearNo][0];
			for (int i = 0; i < 7; i++) {
				gearList[gearNo][i] = gearList[gearNo][i+1]; 
			}
			gearList[gearNo][7] = temp;
		}
	}
}
