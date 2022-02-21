import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 백준 2108번. 통계학
public class BOJ2108_Statistics {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int sum = 0;
		int[] counting = new int[8002];
		int maxCount = 0;
		int maxNumber = Integer.MIN_VALUE;
		int minNumber = Integer.MAX_VALUE;
		
		// 처리
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			sum += num;
			maxNumber = Math.max(maxNumber, num);
			minNumber = Math.min(minNumber, num);

			num+=4000; // -4000부터 0번 인덱스로 사용
			counting[num]++;
			maxCount = Math.max(maxCount, counting[num]);
		}
		
		ArrayList<Integer> maxCountNumbers = new ArrayList<>();
		int count = 0;
		int middleIdx = (n+1)/2;
		int middleNumber = 0;
		boolean findMiddle = false;
		for (int i = 0; i < 8002; i++) {
			if (counting[i] != 0) {
				count += counting[i];
				
				if(count >= middleIdx && !findMiddle) {
					middleNumber = i-4000;
					findMiddle = true;
				}
			}
			if (counting[i] == maxCount)
				maxCountNumbers.add(i);
		}
		
		Collections.sort(maxCountNumbers);
		
		// 출력
		System.out.println((int)Math.round(sum*1.0/n));
		System.out.println(middleNumber);
		if (maxCountNumbers.size() >= 2)			
			System.out.println(maxCountNumbers.get(1)-4000);
		else
			System.out.println(maxCountNumbers.get(0)-4000);
		System.out.println(maxNumber-minNumber);
	}
}