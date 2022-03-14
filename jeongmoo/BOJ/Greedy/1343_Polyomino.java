import java.util.Scanner;

// 백준 1343. 폴리오미노
public class BOJ1343_Polyomino {
	public static void main(String[] args) throws Exception {
		// 입력
		Scanner sc = new Scanner(System.in);
		char[] data = sc.next().toCharArray();
		
		// 계산
		int index = 0;
		int count = 0;
		int startIdx = 0;
		
		// 글자 탐색
		while(index < data.length) {
			// X의 개수를 센다.
			if (data[index] == 'X')
				count++;
			// .을 만나면
			else {
				// X가 홀수면 -1
				if (count%2==1) {
					System.out.println("-1");
					return;
				}
				
				// 짝수면 마킹한다.
				mark(count, startIdx, data);
				count = 0;
				startIdx = index+1; // 다음 마킹 시작위치 변경
			}
			index++;
		}
		
		// .이 하나도 없는 경우가 있을 수 있으니 나와서 한번 더 체크한다.
		if (count%2==1) {
			System.out.println("-1");
			return;
		}
		mark(count, startIdx, data);
		
		
		// 출력
		System.out.println(String.valueOf(data));
	}
	
	// 글자를 마킹하는 함수
	public static void mark(int count, int startIdx, char[] data) {
		while (0 < count) {
			if (count >= 4) {
				for (int i = 0; i < 4; i++) {
					data[startIdx+i] = 'A';
				}
				count -= 4;
				startIdx += 4;
			} else {
				for (int i = 0; i < 2; i++) {
					data[startIdx+i] = 'B';
				}
				count -= 2;
				startIdx += 2;
			}
		}
	}
}
