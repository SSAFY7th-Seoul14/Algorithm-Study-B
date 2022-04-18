import java.io.*;
import java.util.*;

// BOJ / 호석이 두마리 치킨 / G5 /
// https://www.acmicpc.net/problem/21278
public class Main_21278 {
	static int N, M;
	static int graph[][];
	static int selected[]; // 선택된 치킨집
	static int resSum; // 모든 도시에서의 왕복 시간 합
	static int res[]; // 건물 2개가 지어질 건물 번호

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N + 1][N + 1];
		selected = new int[2];
		res = new int[2];
		resSum = Integer.MAX_VALUE;

		// 최단거리 테이블 모두 무한으로 초기화
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(graph[i], (int) 1e9);
		}

		// 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				if (i == j)
					graph[i][j] = 0;
			}
		}

		// 도로 정보 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1;
			graph[b][a] = 1;
		}

		// 플로이드 수행
		for (int k = 1; k < N + 1; k++) {
			for (int a = 1; a < N + 1; a++) {
				for (int b = 1; b < N + 1; b++) {
					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
				}
			}
		}
		// 1~N에서 치킨집 2개 선택
		selectChicken(0, 1);
		System.out.println(res[0] + " " + res[1] + " " + resSum);

	}

	private static void selectChicken(int idx, int start) { // 1~N에서 치킨집 2개 선택
		if (idx == 2) {
			// 현재 선택된 치킨집으로 총 소요 시간 구하기
			getTime();
			return;
		}

		for (int i = start; i < N + 1; i++) {
			selected[idx] = i;
			selectChicken(idx + 1, i + 1);
		}

	}

	private static void getTime() { //왕복 소요시간 구하기
		int sum = 0;
		int s1 = selected[0];
		int s2 = selected[1];
		for (int i = 1; i < N + 1; i++) {
			sum += Math.min(graph[i][s1] * 2, graph[i][s2] * 2); // 치킨집 1과 치킨집 2 중 더 적은 왕복시간
		}

		if (sum < resSum) { //전체 왕복시간 더 적을 때 갱신
			resSum = sum;
			res[0] = s1;
			res[1] = s2;
		}

	}

}
