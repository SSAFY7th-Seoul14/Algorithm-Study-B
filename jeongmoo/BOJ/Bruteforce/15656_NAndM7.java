import java.util.Arrays;
import java.util.Scanner;

// 백준 15656번. N과 M (7)
public class BOJ15656_NAndM7 {	
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
			nums[idx] = data[i];
			permutation(idx+1);
		}
	}
}
