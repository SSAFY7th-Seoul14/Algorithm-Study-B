import java.util.Scanner;

// 백준 15651번. N과 M (3)
public class BOJ15651_NAndM3 {	
	static int n, m;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		nums = new int[m];
		
		permutation(0);
		System.out.println(sb);
	}
	
	public static void permutation(int idx) {
		if (idx == m) {
			for (int i = 0; i < m; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < n; i++) {
			nums[idx] = i+1;
			permutation(idx+1);
		}
	}
}
