package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//한시간 넘게 걸림
public class BOJ17609_회문 {

	public static int chance;
	public static String s;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int t=0; t < N; t++) {
			s = br.readLine();
			int l = s.length();
			chance = 1;
			
			int result = calc(0, l-1);
			if(result == 1 && chance == 1) result = 0;
			System.out.println(result);
			
		}

	}
	
	public static int calc(int left, int right) {

		while(left<right) {
			
			if(s.charAt(left) != s.charAt(right)) {
				if(chance == 1) {
					chance--;
					int n1 = calc(left+1, right);
					int n2 = calc(left, right-1);
					if(n1 == 1 || n2 == 1) return 1;
					else return 2;
				}
				else {
					return 2;
				}
			}
			left++;
			right--;
		}
		
		return 1;
		
	}

}
