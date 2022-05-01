package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//실버1 https://www.acmicpc.net/problem/1946
//첫번째 열 기준으로 정렬시킨 후 차례로 두번째 열 비교. 
//뒤에 오는 애들은 앞에 나왔던 애들보다 서류성적이 이미 낮기 때문에 면접등급(두번째열)은 앞에꺼보다 높아야함.
public class BOJ1946_신입사원 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			//방법 1 : 첫번째 열 기준으로 정렬
			/*int[][] grade = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				grade[i][0] = Integer.parseInt(st.nextToken());
				grade[i][1] = Integer.parseInt(st.nextToken());
				
			}
			
			Arrays.sort(grade, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});

			int min = grade[0][1];
			int cnt  = 1;
			for(int i=1; i < N; i++) {
				if(min > grade[i][1]) {
					min = grade[i][1];
					cnt++;
				}
			}*/
			
			//방법2 : 번호에 해당하는 인덱스에 값 저장 => 자동 정렬됨
			int[] grade = new int[N+1];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				grade[n1] = n2;
				
			}

			int min = grade[1];
			int cnt  = 1;
			for(int i=2; i <= N; i++) {
				if(min > grade[i]) {
					min = grade[i];
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

}
