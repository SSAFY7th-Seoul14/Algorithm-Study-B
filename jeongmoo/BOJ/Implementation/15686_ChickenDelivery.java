import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 15686번. 치킨 배달
public class BOJ15686_ChickenDelivery {
	static int n, m, result;
	static int[][] map;
	static boolean[] isSelected;
	static ArrayList<int[]> houseList = new ArrayList<int[]>();;
	static ArrayList<int[]> chickenHouseList = new ArrayList<int[]>();;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); // 폐업 안할 치킨집
		map = new int[n][n];
		isSelected = new boolean[13];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					houseList.add(new int[] {i, j});
				else if (map[i][j] == 2)
					chickenHouseList.add(new int[] {i, j});
			}
		}
		
		// 부분집합
		result = Integer.MAX_VALUE;
		subset(0, 0);
		
		// 출력
		System.out.println(result);
	}
	
	public static void subset(int idx, int selCnt) {
		if (selCnt == m) {
			int dist = getDist();
			result = Math.min(result, dist);
			return;
		}
		
		if (idx == chickenHouseList.size())
			return;
		
		// 치킨집 idx번째를 고르거나 안 고르거나
		isSelected[idx] = true;
		subset(idx+1, selCnt+1);
		
		isSelected[idx] = false;
		subset(idx+1, selCnt);
	}
	
	public static int getDist() {
		int dist = 0;
		for (int[] house : houseList) {
			int curDist = Integer.MAX_VALUE;
			for (int i = 0; i < chickenHouseList.size(); i++) {
				if (isSelected[i]) {
					int[] ch = chickenHouseList.get(i);
					int temp = Math.abs(ch[0] - house[0]) + Math.abs(ch[1] - house[1]);
					curDist = Math.min(curDist, temp);
				}
			}
			dist += curDist;
		}
		return dist;
	}
}