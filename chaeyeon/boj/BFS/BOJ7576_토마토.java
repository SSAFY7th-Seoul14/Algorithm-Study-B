import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576_토마토 {
	static int[][] d = {{-1,0}, {1,0}, {0,-1},{0,1}};
	static int N,M;
	static int[][] map;
	static Queue<int[]> q = new LinkedList<>(); // 익은 토마토 담을 큐
	static int result=-1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {//익은 토마토라면
					q.add(new int[] {i,j});//큐에 추가
					map[i][j] = -1;//방문표시
				}
			}
		}
		
		
		bfs();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {//bfs로 다 돌았는데도 익지 않은 토마토가 있다면
					result = -1;
					break;
				}
				
			}
		}
		
		System.out.println(result);
	}
	
	
	public static void bfs() {
		while(!q.isEmpty()) {
			int size = q.size();//같은 너비인 애들끼리 돌게 시킬거여서
			result++;//너비증가
			while(size != 0) {
				int[] now = q.poll();
				
				for(int i = 0; i < 4; i++) {
					int dx = now[0] + d[i][0];
					int dy = now[1] + d[i][1];
					
					if(dx >=0 && dx < N && dy >= 0 && dy < M && map[dx][dy] != -1) {
						q.add(new int[] {dx,dy});
						map[dx][dy] = -1;
					}
				}
				size--;
			}
		}
	}

}

