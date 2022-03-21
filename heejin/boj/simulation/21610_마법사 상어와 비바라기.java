import java.io.*;
import java.util.*;
import java.awt.*;

// BOJ / G5 / 마법사 상어와 비바라기 / 70분
// https://www.acmicpc.net/problem/21610
public class Main_21610 {
	static int N,M;
	static int map[][]; //물의 양
	static boolean cloud[][]; //구름 여부
	static int dx[] = {0,-1,-1,-1,0,1,1,1};
	static int dy[] = {-1,-1,0,1,1,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		cloud = new boolean[N][N];
		
		//(N,1), (N,2), (N-1,1), (N-1,2)에 비구름 생성
		cloud[N-1][0]=true;
		cloud[N-1][1]=true;
		cloud[N-2][0]=true;
		cloud[N-2][1]=true;
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rainMagic(d,s);
		}
		//물 양 계산
		int res=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++)
				res +=map[i][j];
		}
		System.out.println(res);
	}
	
	private static void rainMagic(int d, int s) {
		//1.모든 구름이 d방향으로 s칸 이동
		
		//1-1. 구름 좌표들 저장
		ArrayList<Point> list = new ArrayList<Point>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(cloud[i][j]){
					list.add(new Point(i,j));
					cloud[i][j]=false; //구름 이동할거기 때문에 false 처리
				}
			}
		}
		//1-2. 모든 구름 좌표들 이동 시작
		for(int i=0;i<list.size();i++) {
			Point cur = list.get(i);
			int x = cur.x;
			int y = cur.y;
			int nx=x, ny=y;
			for(int j=0;j<s;j++) {
				nx = (nx + dx[d-1] + N)%N;
				ny = (ny + dy[d-1] + N)%N;
			}
			cur.move(nx, ny);
			//cloud[nx][ny]=true; //구름 이동 좌표 true 처리
			//2. 구름 있는 칸의 바구니 물 양 + 1
			map[nx][ny] +=1;
		}
		
		//4. 2에서 물 증가한 칸에 물복사버그 마법 시전
		for(int i=0;i<list.size();i++) {
			Point cur = list.get(i);
			int x = cur.x;
			int y = cur.y;
			waterCopy(x,y);
		}
		//5. 바구니 물 양 2 이상인 칸에 구름 생김.
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]>=2) {
					boolean flag=true;
					for(int k=0;k<list.size();k++) {
						if(list.get(k).x==i && list.get(k).y==j) {
							flag=false;
							break;
						}
					}
					if(flag) {
						cloud[i][j]=true;
						map[i][j]-=2;
					}
				}
					
			}
		}
	}

	

	private static void waterCopy(int x, int y) { //물 복사 마법
		int plus = 0; //증가하는 물 양(대각선의 물 있는 칸 수
		//대각선 방향 살펴보기
		for(int i=0;i<4;i++) {
			//대각선 방향의 좌표가 1,3,5,7
			int nx = x + dx[2*i+1];
			int ny = y + dy[2*i+1];
			if(nx>=0 && nx<N && ny>=0 && ny<N) {
				if(map[nx][ny]>0)
					plus++;
			}
			
		}
		//물 복사
		map[x][y] +=plus;
		
	}
}
