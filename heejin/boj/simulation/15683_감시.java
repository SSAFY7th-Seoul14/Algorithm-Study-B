import java.io.*;
import java.util.*;

// BOJ / 감시 / G5 / 80분
//https://www.acmicpc.net/problem/15683
class CCTV{
	int num; // CCTV 종류
	int x; // x 좌표
	int y; // y 좌표
	
	public CCTV(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
}
public class Main_15683 {
	static int N,M;
	static int map[][];
	static List<CCTV> list = new ArrayList<CCTV>(); //CCTV 종류, 좌표들 저장
	static int dx[] = {-1,0,1,0}; //상우하좌
	static int dy[] = {0,1,0,-1};
	static int res = Integer.MAX_VALUE; //사각지대(정답)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		//map 입력받기
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]!=0 && map[i][j]!=6) { // CCTV 종류, 좌표 list에 저장
					list.add(new CCTV(map[i][j],i,j));
				}
			}
		}
		
		//cctv 감시 시작
		backtracking(0, new ArrayList<int[]>()); //인자: idx(cctv 설치개수), selected(cctv의 방향이랑 좌표)
		System.out.println(res);
	}
	
	private static void backtracking(int idx, List<int[]> selected) {
		if(idx==list.size()) { //cctv 설치 다 했을 경우
			 count(selected); //cctv 감시 + 사각지대 계산
			 return;
		}
		CCTV cur = list.get(idx);
		if(cur.num==1) { //1번 CCTV인 경우 -> 4가지
			for(int i=0;i<4;i++) {
				selected.add(new int[] {i, cur.x, cur.y}); //selected에 dir, x좌표, y좌표 저
				backtracking(idx+1, selected);
				selected.remove(selected.size()-1);
			}
		}
		else if(cur.num==2) { //2번 CCTV인 경우 -> 2가지
			for(int i=0;i<2;i++) {
				selected.add(new int[] {i, cur.x, cur.y});
				selected.add(new int[] {i+2, cur.x, cur.y});
				backtracking(idx+1, selected);
				selected.remove(selected.size()-1);
				selected.remove(selected.size()-1);
			}
			
		}
		else if(cur.num==3) { //3번 CCTV인 경우 -> 4가지
			for(int i=0;i<4;i++) {
				selected.add(new int[] {i, cur.x, cur.y});
				selected.add(new int[] {(i+1)%4, cur.x, cur.y});
				backtracking(idx+1, selected);
				selected.remove(selected.size()-1);
				selected.remove(selected.size()-1);
			}
		}
		else if(cur.num==4) { //4번 CCTV인 경우 -> 4가지
			for(int i=0;i<4;i++) {
				selected.add(new int[] {i, cur.x, cur.y});
				selected.add(new int[] {(i+1)%4, cur.x, cur.y});
				selected.add(new int[] {(i+2)%4, cur.x, cur.y});
				backtracking(idx+1, selected);
				selected.remove(selected.size()-1);
				selected.remove(selected.size()-1);
				selected.remove(selected.size()-1);
			}
		}
		else if(cur.num==5) { //5번 CCTV인 경우 -> 1가지
			selected.add(new int[] {0, cur.x, cur.y});
			selected.add(new int[] {1, cur.x, cur.y});
			selected.add(new int[] {2, cur.x, cur.y});
			selected.add(new int[] {3, cur.x, cur.y});
			backtracking(idx+1, selected);
			selected.remove(selected.size()-1);
			selected.remove(selected.size()-1);
			selected.remove(selected.size()-1);
			selected.remove(selected.size()-1);
		}
		
	}
	private static void count(List<int[]> selected) {
		//map 복사한 배열 tmp
		int tmp[][] = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				tmp[i][j] = map[i][j];
			}
		}
		//cctv 감시 시작
		for(int i=0;i<selected.size();i++) {
			int[] cur = selected.get(i);
			int dir = cur[0], cx = cur[1], cy = cur[2];
			while(true) {
				cx += dx[dir];
				cy += dy[dir];
				if(cx<0 || cx>=N || cy<0 || cy>=M) break; //범위 벗어나면 탈출
				if(tmp[cx][cy]!=6) { //벽 아닐 경우
					tmp[cx][cy]=-1;				
				}
				else if(tmp[cx][cy]==6) //벽 만나면 탈출
					break;
			}
			
		}
		//사각지대 계산
		int cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tmp[i][j]==0)
					cnt++;
			}
		}
		res = Math.min(res, cnt);
	}
}
