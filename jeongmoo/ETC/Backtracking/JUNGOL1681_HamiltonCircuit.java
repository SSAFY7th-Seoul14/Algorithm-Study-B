import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 정올 1681번. 해밀턴 순환회로
public class JUNGOL1681_HamiltonCircuit {
	static int n, result = Integer.MAX_VALUE;
	static int[][] adj;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 장소의 수
		adj = new int[n][n];
		
		// 입력
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처리
		boolean[] isVisited = new boolean[n];
		isVisited[0] = true;
		dfs(0, 0, 0, isVisited);
		
		// 출력
		System.out.println(result);
	}
	
	public static void dfs(int num, int sum, int count, boolean[] isVisited) {
		// 더하는 도중에 기존 최소값보다 커지면 바로 리턴한다 (백트래킹)
		if (sum > result)
			return;
		
		boolean isFind = false;
		// 모든 정점 체크하여 방문하지 않고, 현재에서 연결된 경우 더해나감
		for (int i = 0; i < n; i++) {
			if (!isVisited[i] && adj[num][i] != 0) {
				isVisited[i] = true;
				dfs(i, sum+adj[num][i], count+1, isVisited);
				isFind = true;
				isVisited[i] = false;	// 다음 방문을 위해 초기화
			}
		}
		
		// 더 이상 갈 곳이 없고, 모든 곳을 방문했다면
		if(!isFind && count==n-1) {
			// 마지막으로 시작점으로 되돌아올 수 있는지 체크하여 최소값 갱신
			if (adj[num][0] != 0) {
				sum += adj[num][0];
				result = Math.min(result, sum);
			}
		}
	}
}
