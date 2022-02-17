import java.util.Arrays;
import java.util.Scanner;

// 백준 15655번. N과 M (6)
public class BOJ15655_NAndM6 {	
	static int n, m;
	static int[] data;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = sc.nextInt();
		}
		nums = new int[m];
		
		Arrays.sort(data);
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
			nums[idx] = data[i];
			combination(idx+1, i+1);
		}
	}
}
