import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SW Expert Academy 1225번. 암호 생성기
public class SWEA1225_PasswordGenerator {
	static int[] arr = new int[8];
	
	public static void generate() {
		int count = 1;
		boolean end = false;
		while (true) {
			arr[0] -= count;
			
			if (arr[0] <= 0) {
				arr[0] = 0;
				end = true;
			}
			
			// 밀기
			int temp = arr[0];
			for (int i = 1; i < 8; i++) {
				arr[i-1] = arr[i];
			}
			arr[7] = temp;
			
			// 사이클
			if (count++ == 5)
				count = 1;
			
			// 종료
			if (end)
				break;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String line = null;
		while ((line = br.readLine()) != null) {
			// 입력
			int tc = Integer.parseInt(line);
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 처리
			StringBuilder result = new StringBuilder();
			generate();
			for (int i : arr) {
				result.append(i + " ");
			}
			
			// 출력
			System.out.printf("#%d %s\n", tc, result.toString());
		}
		br.close();
	}
}
