import java.util.Scanner;

// 백준 14888번. 연산자 끼워넣기
public class BOJ14888_InsertOperator {	
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int n;
	
	static int[] arr;		// 숫자 데이터
	static int[] operator = new int[4]; // 연산자 개수
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		
		// 숫자
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 연산자
		for (int i = 0; i < 4; i++) {
			operator[i] = sc.nextInt();
		}
		
		// 처리 및 출력
		permutation(1, arr[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	// 연산자 카운팅하여 연산자로 순열
	public static void permutation(int idx, int ans) {
		if (idx == n) {
			min = Math.min(min, ans);
			max = Math.max(max, ans);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			// 연산자 개수 카운팅하여 백트래킹
			if (operator[i] > 0) {
				operator[i]--;
				if(i==0)
					permutation(idx+1, ans+arr[idx]);
				else if(i==1)
					permutation(idx+1, ans-arr[idx]);
				else if(i==2)
					permutation(idx+1, ans*arr[idx]);
				else if(i==3)
					permutation(idx+1, ans/arr[idx]);
				operator[i]++;
			}
		}
	}
}
