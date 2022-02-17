import java.util.Scanner;

// 백준 15652번. N과 M (4)
public class BOJ15652_NAndM4 {	
	static int n, m;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		nums = new int[m];
		
		combination(0, 0);
		System.out.println(sb);
	}
	
	public static void combination(int idx, int start) {
		if (idx == m) {
			for (int i = 0; i < m; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < n; i++) {
			nums[idx] = i+1;
			combination(idx+1, i);
		}
	}
}
