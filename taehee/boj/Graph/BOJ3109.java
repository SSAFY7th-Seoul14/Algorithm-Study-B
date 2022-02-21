import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static char[][] arr;
	static int r,c;
	static int[] dx = {-1,0,1};
	static int ans=0;
	static boolean complete; //연결했는지
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new char[r][c];
		
		for(int i=0; i<r; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<r; i++) {
			complete = false;
			dfs(i,0);
		}
		System.out.println(ans);
	}

	private static void dfs(int x, int y) {
		//완성됐으면 돌아가기
		if(complete) return;
		//완성했을 때
		if(y == c-1) {
			complete = true;
			ans++;
			return;
		}
		//3가지 방향 탐색
		for(int d=0; d<3; d++) {
			int nx = x + dx[d];
			int ny = y + 1;
			if(0<= nx && nx < r && y < c && arr[nx][ny] == '.') {
				arr[x][y] = 'x';
				dfs(nx,ny);
			}
		}
		//3가지 방향 모두 안되면 되돌아가기
		return;
	}

}
