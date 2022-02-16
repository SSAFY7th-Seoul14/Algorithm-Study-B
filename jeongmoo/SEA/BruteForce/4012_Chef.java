import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SW Expert Academy 4012번. 요리사
public class SWEA4012_Chef {
	static int n;
	static int min = Integer.MAX_VALUE;
	static int[][] data;
	static boolean[] materials;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int i = 1; i <= tc; i++) {
			// 초기화
			min = Integer.MAX_VALUE;
			
			// 입력
			n = Integer.parseInt(br.readLine());
			data = new int[n][n];
			materials = new boolean[n];
			
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < n; k++) {
					data[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			// n개중 2/n개 선택하는 조합
			combination(0, 0);
			
			// 출력
			System.out.printf("#%d %d\n", i, min);
		}
	}
	
	public static void combination(int idx, int start) {
		if(idx == n/2) {
			calc();
			return;
		}
		
		for(int i=start; i<n; i++) {
			materials[i] = true;
			combination(idx+1, i+1);
			materials[i] = false;
		}
	}
	
	public static void calc() {				
		// 각팀 점수 합계
		int sumA = 0;
		int sumB = 0;
		for (int i=0; i<n; i++) {
			boolean isMaterialA = materials[i];		// true면 A팀
			for (int j=0; j<n; j++) {
				if(i==j) continue;					// 현재 선택된 재료는 제외
				
				if(isMaterialA == materials[j]) {	// 같은 팀 재료면
					if(isMaterialA)
						sumA += data[i][j];
					else
						sumB += data[i][j];
				}
			}
		}
		
		min = Math.min(min, Math.abs(sumA-sumB));
	}
}
