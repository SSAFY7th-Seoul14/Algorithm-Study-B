import java.io.*;
import java.util.*;

//BOJ / G5 / 로봇 청소기 / 2시간+
// https://www.acmicpc.net/problem/14503
public class Main_14503_origin{
	static int N,M;
	static int r,c,d;
	static int dx[] = {-1,0,1,0}; //북,동,남,서
	static int dy[] = {0,1,0,-1};
	static int map[][];
	static boolean visited[][]; //청소기 방문 여부(청소 여부)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		//r,c,d 입력받기
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()); //로봇청소기 r 좌표
		c = Integer.parseInt(st.nextToken()); //로봇청소기 c 좌표
		d = Integer.parseInt(st.nextToken()); //로봇 청소기 현재 방향
		
		//map 입력받기
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		clean(); //로봇청소기 청소 시작
		
		//로봇청소기 청소구역 계산
		int res=0; //청소구역(정답)
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(visited[i][j])
					res++;
			}
		}
		System.out.println(res);
	}
	private static void clean() {
		int x=r, y = c; //로봇청소기 좌표
		visited[x][y]=true; //현재자리 청소
		int dir = d; //로봇청소기 방향
		int cnt=0; //4방향 다 청소 못하는지 체크하는 변수 
		
		while(true) {
			//왼쪽방향 체크
			int left = (dir-1)>=0?dir-1:3; //현재 위치의 왼쪽 방향 구하기
			int nx = x + dx[left], ny = y+ dy[left]; //왼쪽방향 좌표
			if(nx>=0 && nx<N && ny>=0 && ny<M) {
				if(check(nx,ny)) { //청소 가능하면
					dir=left; //그 방향으로 회전
					x = nx;  // 그 방향으로 전진
					y = ny; 
					visited[x][y]=true; //청소
					cnt=0;
				}
				else { //청소 불가능하면
					cnt++; //청소 못하는 방향 수 +1
					dir=left; //그 방향(왼쪽)으로 회전
					if(cnt==4) { // 4방향 모두 청소X
						if(back(x,y,dir)) { //후진 가능?
							if(dir==0) x++; //후진
							else if(dir==1) y--;
							else if(dir==2) x--;
							else if(dir==3) y++;
							cnt=0;
							continue;
							
						}
						else //후진 불가능
							return;
					}
					
					
				}
			}
		
			
		}
		
	}
	private static boolean back(int x, int y, int dir) { //후진 가능 여부
		if(dir==0) { //상 방향에서 후진
			x++;
			if(x<N && map[x][y]!=1)
				return true;
			else
				return false;
		}
		else if(dir==1) { //우 방향에서 후진
			y--;
			if(y>=0 && map[x][y]!=1)
				return true;
			else 
				return false;
		}
		else if(dir==2) { //좌 방향에서 후진
			x--;
			if(x>=0 && map[x][y]!=1)
				return true;
			else
				return false;
			
		}
		else {
			y++;
			if(y<M && map[x][y]!=1)
				return true;
			else
				return false;
		}
	}
	private static boolean check(int nx, int ny) {
		if(!visited[nx][ny] && map[nx][ny]==0)
			return true;
		else
			return false;
	}

}
