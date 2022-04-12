import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 17135번. 캐슬 디펜스
public class BOJ17135_CastleDefence {
	static int n, m, d, result;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 행(세로)
		m = Integer.parseInt(st.nextToken()); // 열(가로)
		d = Integer.parseInt(st.nextToken()); // 사거리
		
		// 궁수위치까지 1칸 더 크게잡는다.
		map = new int[n+1][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 가장 아래는 성(2)으로 채운다.
		for (int i = 0; i < m; i++) {
			map[n][i] = 2;
		}
		
		// 조합
		result = 0;
		combi(0, 0);
		
		// 출력
		System.out.println(result);
	}
	
	public static void combi(int idx, int start) {
		if (idx == 3) {
			result = Math.max(result, getMaxCount());
			return;
		}
		
		for (int i = start; i < m; i++) {
			map[n][i] = 3;
			combi(idx+1, i+1);
			map[n][i] = 2;
		}
	}
	
	public static int getMaxCount() {
		// map copy
		int[][] newMap = new int[n+1][m];
		int enemyCnt = 0;
		for (int i = 0; i < n+1; i++) {
			for (int j = 0; j < m; j++) {
				newMap[i][j] = map[i][j];
				if (newMap[i][j] == 1)
					enemyCnt++;
			}
		}
		
		// 처리
		int killCnt = 0;
		// 행 1칸씩 당기면서 (적 이동) 반복
		for (int i = 0; i < n; i++) {
			ArrayList<int[]> attackList = new ArrayList<>();
			// 열 돌면서 궁수 위치 찾는 반복문
			for (int j = 0; j < m; j++) {
				if (enemyCnt == 0)
					break;
				
				// 궁수인 경우에 적을 찾는다.
				if (map[n][j] == 3) {
					int[] enemyPos = findEnemy(newMap, j);
					attackList.add(enemyPos);
				}
			}
			
			// 한번에 공격해야 하므로 리스트에 담았다가 처리
			for (int[] pos : attackList) {
				// 적이 없거나 이미 죽은 상태면 continue
				if (pos[0] == -1)
					continue;
				if (newMap[pos[0]][pos[1]] == 0)
					continue;
				
				newMap[pos[0]][pos[1]] = 0;
				killCnt++;
				enemyCnt--;
			}
			
			// 적 이동 전에 다 죽었는지 체크
			if (enemyCnt == 0)
				break;
			
			// 적 이동
			// 맨 아랫줄 적은 그대로 사라지면 되므로 n-1부터 시작
			for (int j = n-1; j >= 0; j--) {
				for (int k = 0; k < m; k++) {
					newMap[j+1][k] = newMap[j][k]; 
				}
			}
			// 맨 윗줄은 0으로 채운다.
			for (int j = 0; j < m; j++) {
				newMap[0][j] = 0;
			}
		}
		
		return killCnt;
	}
	
	public static int[] findEnemy(int[][] newMap, int archer) {
		// 그냥 완탐돌면 최대 15*15 = 225. 궁수 3명이니까 최대 675.
		// n행 반복해도 675*15 = 약 1만.		
		int[] enemyPos = {-1, -1};
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (newMap[i][j] == 1) {
					int dist = Math.abs(j-archer) + n-i;
					if (dist <= d && dist < min) {
						min = dist;
						enemyPos[0] = i;
						enemyPos[1] = j;
					} else if (dist == min) {
						// 거리 가까운 적 여러개면 가장 왼쪽 적으로 바꾼다.
						if (j < enemyPos[1]) {
							enemyPos[0] = i;
							enemyPos[1] = j;
						}
					}
				}
			}
		}
		return enemyPos;
	}
}