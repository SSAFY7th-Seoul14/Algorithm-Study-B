import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2342번. Dance Dance Revolution
public class BOJ2342_DDR {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		ArrayList<Integer> data = new ArrayList<>();
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			if (num == 0) break;
			data.add(num);
		}
		int size = data.size();
		
		// min값 갱신을 위해 max로 초기화
		int[][][] dp = new int[5][5][size+1];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Arrays.fill(dp[i][j], 400001);
			}
		}
		dp[0][0][0] = 0; // 시작좌표는 0,0이므로 여기만 0으로 두고 시작해야함.
		
		for (int k = 0; k < size; k++) {
			int num = data.get(k);
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					// 오른발을 j->num으로 이동해서 [i][num] 최소값을 갱신
					if (num != i)
						dp[i][num][k+1] = Math.min(dp[i][num][k+1], dp[i][j][k] + check(j, num));

					// 왼발을 i->num으로 이동해서 [num][j] 갱신
					if (num != j)
						dp[num][j][k+1] = Math.min(dp[num][j][k+1], dp[i][j][k] + check(i, num));
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				result = Math.min(result, dp[i][j][size]);
			}
		}
		System.out.println(result);
	}
	
	public static int check(int from, int to) {
		if (from==0)
			return 2;
		if (from==to)
			return 1;
		
		// 반대편이면 4, 인접이면 3
		return (to+2)%4==from%4? 4 : 3; 
	}
}