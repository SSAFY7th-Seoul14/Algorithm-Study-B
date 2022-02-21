import java.util.Arrays;
import java.util.Scanner;

// 백준 15654번. N과 M (5)
public class BOJ15654_NAndM5 {	
	static int n, m;
	static int[] data;
	static int[] nums;
	static boolean[] isSelected;
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
		isSelected = new boolean[n];
		
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
			if (!isSelected[i]) {
				isSelected[i] = true;
				nums[idx] = data[i];
				permutation(idx+1);
				isSelected[i] = false;
			}
		}
	}
}
