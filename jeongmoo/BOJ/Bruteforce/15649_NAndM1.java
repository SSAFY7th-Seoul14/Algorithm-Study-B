import java.util.Scanner;

// 백준 15649번. N과 M (1)
public class BOJ15649_NAndM1 {	
	static int n, m;
	static int[] nums;
	static boolean[] isSelected;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		isSelected = new boolean[n];
		nums = new int[m];
		
		permutation(0);
	}
	
	public static void permutation(int idx) {
		if (idx == m) {
			for (int i = 0; i < m; i++) {
				System.out.print(nums[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				nums[idx] = i+1;
				permutation(idx+1);
				isSelected[i] = false;
			}
		}
	}
}
