
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int n,m;
	static int[][] map;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		ArrayList<Point> list = new ArrayList<>(); //0값 모은 배열
		int ans = 0; //안전영역 최대값
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				int val = sc.nextInt();
				map[i][j] = val;
				if(val == 0) list.add(new Point(i,j));
			}
		}
		
		//벽 세우기 좌표 i j k
		for(int i=0; i<list.size()-2; i++) {
			for(int j=i+1; j<list.size()-1; j++) {
				for(int k=j+1; k<list.size(); k++) {
					//벽 3개 세울 수 있는 모든 경우의 수에 대해서
					//복사를 위한 배열
					int[][] temp = new int[n][m];
					for(int a =0; a<n; a++) {
						for(int b=0; b<m; b++) {
							temp[a][b] = map[a][b];
						}
					}
					//벽 3개 세우기
					Point p1 = list.get(i);
					Point p2 = list.get(j);
					Point p3 = list.get(k);
					temp[p1.x][p1.y] = 1; 
					temp[p2.x][p2.y] = 1; 
					temp[p3.x][p3.y] = 1;
					//바이러스 퍼뜨리기 
					bfs(temp);
					//바이러스 퍼진후 0개수 세기
					int cnt = 0;
					for(int a =0; a<n; a++) {
						for(int b=0; b<m; b++) {
							if(temp[a][b] == 0) {
								cnt++;
							}
						}
					}
					if(cnt > ans) ans = cnt;
				}
			}
		}
		System.out.println(ans);

		
	}
	
	public static void bfs(int[][] temp) {
		Queue<Point> q = new LinkedList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(temp[i][j] == 2) q.offer(new Point(i,j));
			}
		}
		while(!q.isEmpty()) {
			Point p = q.poll();
			temp[p.x][p.y] = 2;
			
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(0 <= nx && nx < n && 0 <= ny && ny < m) {
					if(temp[nx][ny] == 0) {
						q.offer(new Point(nx,ny));
					}
				}
			}
		}
	}
}
