import java.io.*;
import java.util.*;

// BOJ / 보호 필름 / 모의 역량 / 40분
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu
public class Solution_2112 {
	static int D,W,K; //D:두꼐(행), W:가로크기(열), K: 합격기준
	static int map[][], tmp[][]; //map: input값, tmp: 시약 넣었다가 되돌리는 용
	static int res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			tmp = new int[D][W];
		
			
			for(int i=0;i<D;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<D;i++) {	//배열 복사
				for(int j=0;j<W;j++)
					tmp[i][j] = map[i][j];
			}
			if(pass())
				res = 0;
			else {
				res = Integer.MAX_VALUE;
				dfs(0,0);
			}
				
			System.out.println("#"+t+" "+res);
		}
	}
	private static void dfs(int idx, int cnt) { //idx: 현재 행, cnt: 시약 사용 회수
		if(idx>=D) {
			if(pass()) { //테스트 결과 통과일 때
				res = Math.min(res, cnt);
			}
			return;
		}
		for(int i=0;i<2;i++) { //0(A), 1(B) 시약 투입
			fill(idx,i);
			dfs(idx+1, cnt+1);	//시약 투입할 경우
			recover(idx);
		}
		dfs(idx+1, cnt); //시약 투입 안 할 경우
		
	}
	private static void recover(int idx) {	//원래 값으로 되돌리기
		for(int i=0;i<W;i++)
			tmp[idx][i] = map[idx][i];
		
	}
	private static void fill(int idx, int val) {	//시약 투입
		for(int i=0;i<W;i++)
			tmp[idx][i] = val;
		
	}
	private static boolean pass() { //현재 tmp map 상태가 합격 기준 K를 만족하는지

		int cnt = 1; //동일한 특성의 셀
		int maxCnt = 0;
		
		for(int j=0;j<W;j++) {	//모든 열에 대해
			cnt = 1;
			maxCnt = 0;
			for(int i=0;i<D-1;i++) {
				if(tmp[i][j] == tmp[i+1][j])
					cnt++;
				else
					cnt=1;
				maxCnt = Math.max(maxCnt, cnt);
			}
			
			if(maxCnt<K)
				return false;
		}

			return true;

	}
}
