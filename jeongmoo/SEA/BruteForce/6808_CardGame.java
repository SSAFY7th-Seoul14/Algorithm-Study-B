import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//SW Expert Academy 6808번. 규영이와 인영이의 카드게임
public class SWEA6808_CardGame {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int winCount = 0;
		int loseCount = 0;
		int[] enemy = new int[9];
		int[] me = new int[9];
		
		boolean[] numbers = new boolean[19];	// 전체 숫자
		for (int i = 1; i <= tc; i++) {
			// 초기화
			winCount = 0;
			loseCount = 0;
			Arrays.fill(numbers, false);
			
			// 입력
			// 규영이가 내는 카드 및 사용한 카드 체크
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				enemy[j] = Integer.parseInt(st.nextToken());
				numbers[enemy[j]] = true;
			}
			
			// 사용하지 않은 카드를 인영이가 가진다.
			int idx = 0;
			for(int j=1; j<=18; j++) {
				if (!numbers[j])
					me[idx++] = j;
			}
			
			// 처리
			Arrays.sort(me);
			
			// 해당 카드는 모두 다른 수이므로 nPn 순열 이용하여 모든 경우의 수에서 비교
			int sum = 0;
			int myScore = 0;
			int enemyScore = 0;
			do {
				myScore = 0;
				enemyScore = 0;
				// 9라운드 진행하며 스코어 계산
				for(int j=0; j<9; j++) {
					sum = me[j] + enemy[j];
					if (me[j] > enemy[j])
						myScore += sum;
					else if (me[j] < enemy[j])
						enemyScore += sum;
				}
				// 스코어 따라 승패 계산 (인영이 기준)
				if (myScore > enemyScore)
					winCount++;
				else if (myScore < enemyScore)
					loseCount++;
			} while(np(me));
			
			// 출력
			// 규영이 기준으로 승패를 출력하라고 하여 반대로 출력
			System.out.printf("#%d %d %d\n", i, loseCount, winCount);
		}
	}
	
	public static boolean np(int[] data) {
		int n = data.length;
		int i = n-1;
		while(i>0 && data[i-1]>=data[i]) i--;
		if (i==0) return false;
		
		int j = n-1;
		while(data[i-1]>=data[j]) j--;
		
		swap(data, i-1, j);
		
		int k = n-1;
		while(i<k)	swap(data, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] data, int x, int y) {
		int temp = data[x];
		data[x] = data[y];
		data[y] = temp;
	}
}
