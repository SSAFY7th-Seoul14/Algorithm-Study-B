import java.util.Scanner;

// 백준 9663번. N-Queen
public class BOJ9663_NQueen {	
	static int count = 0;
	static int n;
	static int[] map;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n];
		
		setQueen(0);
		System.out.println(count);
	}
	
	public static void setQueen(int row) {
		if (row == n) {
			count++;
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (check(row, i)) {
				map[row] = i; 
				setQueen(row+1);
			}
		}
	}
	
	public static boolean check(int row, int col) {
		for (int i = 0; i < row; i++) {
			// 같은 열 체크 (이전 행까지)
			if(map[i] == col)
				return false;
			
			// 대각선 체크
			if(row-i == Math.abs(col-map[i]))
				return false;
		}

		return true;
	}
}
