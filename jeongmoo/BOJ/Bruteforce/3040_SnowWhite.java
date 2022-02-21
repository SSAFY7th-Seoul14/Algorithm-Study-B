import java.util.Scanner;

// 백준 3040번. 백설 공주와 일곱 난쟁이
public class BOJ3040_SnowWhite {
	static int n = 9, r = 7;
	static int arr[] = new int[n];
	static int result[] = new int[r];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		combination(0, 0, 0);
	}
	
	static void combination(int idx, int start, int sum) {		
		if(idx == r) {
			if (sum==100) {
				for (int i = 0; i < r; i++) {
					System.out.println(result[i]);
				}
			}			
			return;
		}
		
		for(int i=start; i<n; i++) {
			result[idx] = arr[i];
			combination(idx+1, i+1, sum+arr[i]);
		}
	}
}
