import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

// 백준 15665번. N과 M (11)
public class BOJ15665_NAndM11 {	
	static int n, m;
	static int[] data;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();
	static LinkedHashSet<String> set = new LinkedHashSet<>();
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
		for (String str : set) {
			sb.append(str).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void permutation(int idx) {
		if (idx == m) {
			for (int i = 0; i < m; i++) {
				sb.append(nums[i] + " ");
			}
			set.add(sb.toString().trim());
			sb.setLength(0);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			nums[idx] = data[i];
			permutation(idx+1);
		}
	}
}
