import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686_치킨배달 {

	static int m;
	static ArrayList<int[]> houses, chickenShops;
	static int[][][] chickenDist;
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 2 ≤ N ≤ 50
		int n = Integer.parseInt(st.nextToken());
		// 1 ≤ M ≤ 13
		m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][n];
		chickenDist = new int[n][n][m];
		houses = new ArrayList<int[]>();
		chickenShops = new ArrayList<int[]>();
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; ++j) {
				// 0은 빈 칸, 1은 집, 2는 치킨집
				int inp = Integer.parseInt(st.nextToken());
				switch (inp) {
				case 1:
					houses.add(new int[] { i, j });
					break;
				case 2:
					chickenShops.add(new int[] { i, j });
					break;
				default:
					break;
				}
				map[i][j] = inp;
			}
		}
		min = Integer.MAX_VALUE;
		combi(0, 0, 0);
		System.out.println(min);
		br.close();
	}

	private static void combi(int cnt, int start, int visited) {
		if (cnt == m) {
			int nth = 0;
			for (int i = 0; i < chickenShops.size(); ++i) {
				// 선택된 치킨집으로부터
				if ((visited & 1 << i) > 0) {
					int chickenR = chickenShops.get(i)[0];
					int chickenC = chickenShops.get(i)[1];
					// 각 집까지의 거리 구하고
					for (int j = 0; j < houses.size(); ++j) {
						int houseR = houses.get(j)[0];
						int houseC = houses.get(j)[1];
						int dist = Math.abs(houseR - chickenR) + Math.abs(houseC - chickenC);
						// 번호에 맞는 배열에 저장
						chickenDist[houseR][houseC][nth] = dist;
					}
					++nth;
				}
			}
			int distSum = 0;
			// house 마다 각 배열 타고 들어가서 최소값 선택
			for (int i = 0; i < houses.size(); ++i) {
				int houseR = houses.get(i)[0];
				int houseC = houses.get(i)[1];
				int nthHouseMin = Integer.MAX_VALUE;
				for (int j = 0; j < m; ++j) {
					nthHouseMin = Math.min(nthHouseMin, chickenDist[houseR][houseC][j]);
				}
				distSum += nthHouseMin;
			}
			// 최소값 갱신
			min = Math.min(min, distSum);
			return;
		}
		for (int i = start; i < chickenShops.size(); ++i) {
			combi(cnt + 1, i + 1, visited | 1 << i);
		}
	}

}