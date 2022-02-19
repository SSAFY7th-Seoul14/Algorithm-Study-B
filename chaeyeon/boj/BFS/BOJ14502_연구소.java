import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_연구소 {
	static int N,M;
	static int[][] map;
	static int[][] d = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static Queue<int[]> q = new LinkedList<>();
	static ArrayList<int[]> virusList;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());//행
		M = Integer.parseInt(st.nextToken());//열
		map = new int[N][M];
		virusList = new ArrayList<>();//바이러스 위치 담을 리스트
		
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virusList.add(new int[] {i,j});//바이러스일때 위치 저장
				}
			}
		}
		
		calc(0,0);
		System.out.println(max);

	}
	
	//벽 세우기
	//처음엔 j도 넘겨줬었는데 그렇게 되면 다음 재귀에서 행 다 돈 후 j가 0부터 시작해야하는데 넘겨준j부터 시작하게 돼서 안됐다.
	public static void calc(int sx, int cnt) {//dfs로
		if(cnt == 3) {
			bfs();
			return;
		}
		
		for(int i = sx; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
						if(j == M-1)
							calc(i+1, cnt+1);//다음에 뽑을 행 위치를 넘겨줌	
						else				
							calc(i, cnt+1);//다음에 뽑을 행 위치를 넘겨줌	
					map[i][j] = 0;
				}
			}
		}
	}
	
	//바이러스 퍼트리기
	public static void bfs() {
		for(int[] vi: virusList) {//바이러스 담아놨던 정보를 큐에 넣음
			q.add(vi);
		}
		int[][] temp = new int[N][M];
		for(int i=0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		while(!q.isEmpty()) {//큐에서 정보를 뽑아 해당 위치의 상하좌우로 퍼져나가게
			int[] virus = q.poll();
			for(int i = 0; i < 4; i++) {
				int dx = virus[0]+d[i][0];
				int dy = virus[1]+d[i][1];
				if(dx >= 0 && dy >= 0 && dx < N && dy < M && temp[dx][dy]==0) {
					q.add(new int[] {dx,dy});
					temp[dx][dy] = 2;
				}
			}
			
		}
		safeArea(temp);
	}
	
	public static void safeArea(int[][] temp) {//빈칸인 부분 체크
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j< M; j++) {
				if(temp[i][j] == 0)
					count++;
			}
		}
		
		max = Math.max(max, count);
	}

}
