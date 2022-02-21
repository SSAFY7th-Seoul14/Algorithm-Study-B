import java.util.Scanner;

// 백준 14888번. 연산자 끼워넣기
public class BOJ14888_InsertOperator {	
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int n;
	
	static int[] arr;		// 숫자 데이터			
	static int[] operdata;	// 연산자 데이터
	static int[] seloper;	// 선택한 연산자 (순열)
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		operdata = new int[n-1];
		seloper = new int[n-1];
		isSelected = new boolean[n-1];
		
		// 숫자
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 연산자
		int start = 0;
		for (int i = 0; i < 4; i++) {
			int num = sc.nextInt();
			for(int j=start; j<start+num; j++) {
				operdata[j] = i;
			}
			start += num;
		}
		
		// 처리 및 출력
		permutation(0);
		System.out.println(max);
		System.out.println(min);
	}
	
	// 연산자 모든 경우의수 나열해서 계산 (중복 포함)
	public static void permutation(int idx) {
		if (idx == n-1) {
			int calc = arr[0];
			for (int i = 1; i < arr.length; i++) {
				int oper = seloper[i-1];
				int num = arr[i];
				
				switch (oper) {
				case 0:
					calc += num;
					break;
				case 1:
					calc -= num;
					break;
				case 2:
					calc *= num;
					break;
				case 3:
					calc /= num;
					break;

				default:
					break;
				}
			}
			min = Math.min(min, calc);
			max = Math.max(max, calc);
			return;
		}
		
		for (int i = 0; i < n-1; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				seloper[idx] = operdata[i];
				permutation(idx+1);
				
				isSelected[i] = false;
			}
		}
	}
}
