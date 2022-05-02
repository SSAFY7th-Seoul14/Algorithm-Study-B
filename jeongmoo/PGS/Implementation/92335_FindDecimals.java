// 프로그래머스 2022 KAKAO BLIND RECRUITMENT - k진수에서 소수 개수 구하기
public class P92335 {
	public static void main(String[] args) throws Exception {
		// 입력
		System.out.println(solution(437674,3));
		System.out.println(solution(110011,10));
		System.out.println(solution(100000,10));
	}
	
	public static int solution(int n, int k) {		
		// 100만을 2진수로 변환하면 Long범위에도 담을수 없으므로 String 처리
		char[] data = Integer.toString(n, k).toCharArray();
		int count = 0;
		
		int index = 0;
		StringBuilder sb = new StringBuilder();
		while (index < data.length) {
			if (data[index] != '0') {
				sb.append(data[index]);
			} else {
				if (sb.length() > 0 && isPrimeNumber(sb.toString()))
					count++;
					
				sb.setLength(0);
			}
			index++;
		}
		
		if (sb.length() > 0 && isPrimeNumber(sb.toString()))
			count++;
		
		return count;
    }
	
	private static boolean isPrimeNumber(String numstr) {
		// 100만을 3진수 변환시 int범위 넘을 수 있음
		long num = Long.parseLong(numstr);	
		if (num == 1)
			return false;
		
		// 소수 측정
		int max = (int)Math.sqrt(num);
		for (int i = 2; i <= max; i++) {
			if (num%i==0)
				return false;
		}
		return true;
	}
}
