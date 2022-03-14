package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//25분
//앞의 점수를 계산하여 기억해뒀다가 뒤에 점수를 계산할 때 사용하면 되겠다고 생각
//그림을 보며 분석해보다가 [i][j] 값의 최댓값은 [i^0][j-1]와 [i^0][j-2]중 최댓값에 자기자신을 더한것 임을 알게됨
//나처럼 j==1일 경우를 따로 생각해주지 않고 스티커 입력을 1열부터 받으면 j-2일때 인덱스 처리는 자연스럽게 해결되는 것을 다른 코드를 보고 알게됨
public class BOJ9465_스티커 {
	static int N;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int  t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			
			int[][] stickers = new int[2][N];
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					stickers[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			

			for (int j = 1; j < N; j++) {
				for(int i = 0; i < 2; i++) {
					//j==1이면 앞쪽 대각선만 따짐
					if(j==1) stickers[i][j] = stickers[i][j] + stickers[i^1][j-1];
					else {
						stickers[i][j] = stickers[i][j] + Math.max(stickers[i^1][j-1], stickers[i^1][j-2]);
					}
					
				}
			}
			
			int result = Math.max(stickers[0][N-1], stickers[1][N-1]);
			System.out.println(result);
		}
		
		
	}

}
