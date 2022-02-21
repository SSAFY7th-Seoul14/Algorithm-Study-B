import java.util.Scanner;

// 백준 15650번. N과 M (2)
public class BOJ15650_NAndM2 {	
	static int n, m;
	static int[] nums;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		nums = new int[m];
		
		combination(0, 0);
	}
	
	public static void combination(int idx, int start) {
		if (idx == m) {
			for (int i = 0; i < m; i++) {
				System.out.print(nums[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = start; i < n; i++) {
			nums[idx] = i+1;
			combination(idx+1, i+1);
		}
	}
}
