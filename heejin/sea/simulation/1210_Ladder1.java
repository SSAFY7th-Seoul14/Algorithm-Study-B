package com.ssafy.pcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//[SWEA] Ladder1 / D4
public class Solution_1210 {

	static int N = 100;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int t=1;t<=10;t++) {
			int T = Integer.parseInt(br.readLine());
			map = new int[N][N]; //map 생성
			int res= -1;
			
			//100x100 입력받기
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++)
					map[i][j]= Integer.parseInt(st.nextToken());
			}
			
			// X의 위치 찾기
			int x=N-1, y = 0;
			for(int i=0;i<N;i++) {
				if(map[N-1][i]==2) {
					y = i;
					break;
				}
			} 
			//X에서 거꾸로 타고 올라가기 - 사다리 타기 시작	
			while(true) {
				if(x==0) {  // 0행 도달하면 사다리 타기 종료 및 현재 사다리 열 값 반환
					res=y;
					break;
				}
					
				if(y-1 >=0 && map[x][y-1]==1) { //좌측에 있는지 체크
					while(x>=0 && x<N && y-1>=0 && y<N) { //위로 가는거 나올 때까지 반복  + 범위 체크
						y= y-1;						
						if(x-1>=0 && map[x-1][y]==1) { //만약 위로 가는 거 나올 경우, 한 칸 위로 보내주고 break
							x= x-1;
							break;
						}
					}
				}
				else if(y+1 <N && map[x][y+1]==1) { //우측에 있는지 체크
					while(x>=0 && x<N && y>=0 && y<N-1) { //위로 가는거 나올 때까지 반복 + 범위 체크
						y=y+1;
						if(x-1>=0 && map[x-1][y]==1) { //만약 위로 가는 거 나올 경우, 한 칸 위로 보내주고 break
							x=x-1;
							break;
						}
					}
				}
				else if(x-1>=0 && map[x-1][y]==1) { //위에 있는지 체크
					while(x-1>=0 && x<N && y>=0 && y<N) {
						x = x-1;
						if((y-1 >=0 && map[x][y-1]==1) || (y+1<N &&map[x][y+1]==1)) // 만약 좌측이나 우측에 사다리 있을 경우 break
							break;
					}	
				}
			}
			//출력
			sb.append("#"+t+" "+res);
			sb.append('\n');
			
		}	
		bw.write(sb.toString());
		bw.close();
		br.close();
	}	
}

