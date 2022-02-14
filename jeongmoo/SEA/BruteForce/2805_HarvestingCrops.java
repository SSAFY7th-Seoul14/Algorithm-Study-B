import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SW Expert Academy 2805번. 농작물 수확하기
public class SWEA2805_HarvestingCrops {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int i=0; i<t; i++) {
			// 입력
			int n = Integer.parseInt(br.readLine());
			char[][] arr = new char[n][n];
			for(int j=0; j<n; j++) {
				arr[j] = br.readLine().toCharArray();
			}
			
			// 처리
			int start = n/2;
			int total = 0;
			// 가운데 줄은 다 더한다.
			for(int j=0; j<n; j++) {
				total += (arr[start][j] - '0');
			}
			
			// 그 위아래줄 순회
			for(int y=start-1; y>=0; y--) { // n이 5이면 2,4번줄/1,5번줄/...
				int count = 2*y+1; 		// 수확 개수
				int x = (n-count)/2;	// 해당 높이의 시작지점
				for (int k = x; k < x+count; k++) {		// 시작지점부터 count개 만큼
					total += (arr[y][k] - '0');			// 앞에서 y번째 줄
					total += (arr[n-1-y][k] - '0');		// 뒤에서 y번째 줄
				}
			}
			
			// 출력
			System.out.printf("#%d %d\n", i+1, total);
		}
	}
}
