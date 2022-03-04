import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ9466_텀프로젝트 {

	static int[] studentsChoice, indexStudents;
	static boolean[] visited;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());

			// 팀이 되지 않을 후보 n명
			cnt = n;
			// 각 학생들이 선택한 학생 번호 저장 배열
			studentsChoice = new int[n + 1];
			// 풀이해나가다가 필요에 의해 만들게된 배열. 
			// 각 dfs마다 그룹에 속한 명수를 세어주기 위해 dfs depth 들어갈 때 몇번째 사람이었는지 저장하기 위한 배열
			indexStudents = new int[n + 1];
			// dfs 수행위해 배열 여부 체크할 visited
			visited = new boolean[n + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (i == tmp) {
					// 자신이 자신을 가리키면 갈 이유가 없으므로 방문처리
					visited[i] = true;
					// 그룹이 됐으니까 빼주기
					--cnt;
				}
				studentsChoice[i] = tmp;
			}

			// 속하지 못한 학생을 세어줄 cnt
			for (int i = 1; i <= n; i++) {
				// 이미 방문 처리 된 학생, 그룹에 속한 학생
				if (visited[i] 
						// 해당 학생이 가리키는 학생이 이미 그룹에 속했다면 해당 학생은 dfs 탐색할 이유가 없으므로 continue
						|| visited[studentsChoice[i]]) {
					// 방문처리
					visited[i] = true;
					continue;
				}
				// 미방문한 학생에 대해서 dfs 탐색 수행
				dfs(i, 1);
			}
			bw.write(String.format("%d%n", cnt));
		}
		br.close();
		bw.flush();
		bw.close();
	}

	private static void dfs(int i, int num) {
		// dfs 전에 방문한 사람은 이미 처리했기 때문에 dfs 안에서 방문된 학생이라는 뜻은 곧 circular를 만들었다는 뜻.
		if (visited[i]) {
			// 하지만 dfs 돌다가 이미 그룹에 속한 학생을 향하는 학생이 있을 수 있으므로
			// 예를 들어 1 2 3 4 5 학생 중에서 1 2 3 끼리는 그룹이고 4 -> 5, 5-> 3일때는 dfs로 돌았을 때 이미 그룹에 속해있는 3을 마치 순환 중 방문한 것처럼 착각할 수 있다.
			// 현재 dfs에 속하지 않은 학생은 하단 코드에 의해 index값을 가질 수 없고 따라서 0이 아닐 경우만 그룹으로 묶이는 처리가 된다.
			// 0이 아님으로써 그룹에 속할 경우는 속한 명수만큼 전체 cnt에서 빼준다.
			if (indexStudents[i] != 0) {
				cnt -= num - indexStudents[i];
			}
			// dfs 종료 조건에 들어온 학생이 현재 dfs에 참여한적이 없다면 그냥 dfs 종료
			return;
		}
		// 현재 dfs에 속한 학생 방문 처리
		visited[i] = true;
		// 몇번째 학생이었는지 기억
		indexStudents[i] = num;
		// dfs 들어가기
		dfs(studentsChoice[i], num + 1);
		// 다음 dfs를 위해서 초기화
		indexStudents[i] = 0;
	}

}