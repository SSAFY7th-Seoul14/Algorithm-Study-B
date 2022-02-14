import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//SW Expert Academy 1228번. 암호문
public class SWEA1228_Cryptogram {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			list.clear();
			
			// 입력
			int n = Integer.parseInt(br.readLine()); 	// 원본 암호문 길이			
			st = new StringTokenizer(br.readLine()); 	// 원본 암호문
			for (int j = 0; j < n; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int n2 = Integer.parseInt(br.readLine()); 	// 명령어 개수
			String[] commands = new String[n2]; 		// 명령어 입력
			st = new StringTokenizer(br.readLine(), "I");
			for (int j = 0; j < n2; j++) {
				commands[j] = st.nextToken();
			}
			
			// 처리
			for (int j = 0; j < n2; j++) {
				StringTokenizer st2 = new StringTokenizer(commands[j]);
				int x = Integer.parseInt(st2.nextToken());
				int count = Integer.parseInt(st2.nextToken());
				for (int k = 0; k < count; k++) {
					// 삽입
					int num = Integer.parseInt(st2.nextToken());
					list.add(x++, num); // x번째에 num 삽입
				}
			}
			
			// 출력
			System.out.print("#"+ (i+1) + " ");
			for (int j = 0; j < 10; j++) {
				System.out.print(list.get(j) + " ");
			}
			System.out.println();
		}
	}
}
