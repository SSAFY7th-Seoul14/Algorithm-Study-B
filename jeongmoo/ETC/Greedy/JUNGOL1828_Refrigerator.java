import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

// 정올 1828번. 냉장고
public class JUNGOL1828_Refrigerator {
	static class Chemical implements Comparable<Chemical>{
		int min, max;

		public Chemical(int min, int max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public int compareTo(Chemical o) {
			return this.max != o.max? this.max - o.max : this.min - o.min;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		Chemical[] chemicals = new Chemical[n];
		for (int i = 0; i < n; i++) {
			chemicals[i] = new Chemical(sc.nextInt(), sc.nextInt());
		}
		
		// 최고 기준으로 정렬해서 먼저 끝나는거부터 쭉 올라가기
		Arrays.sort(chemicals);
		
		int count = 1;
		int index = 1; // 0번은 고르고 1번부터 순회 시작
		int end = chemicals[0].max;
		while (index < n) {
			if (chemicals[index].min > end) {
				count++;
				end = chemicals[index].max;
			}
			index++;
		}
		
		System.out.println(count);
	}
}
