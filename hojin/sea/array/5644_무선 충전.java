import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5644 {

	static class BC {
		int locX, locY, coverage, perfomance;
		int[][] area;

		BC(int x, int y, int coverage, int performance) {
			this.locX = x;
			this.locY = y;
			this.coverage = coverage;
			this.perfomance = performance;
		}
	}

	static class Person {
		int x, y, charged;
		int timeTable[][];

		Person(int x, int y, int m) {
			this.x = x;
			this.y = y;
			// 0~m시간, x, y 위치 저장되는 timeTable
			this.timeTable = new int[m + 1][2];
			this.charged = 0;
			move(0);
		}

		public void move(int t) {
			move(0, t);
		}

		public void move(int mode, int t) {
			switch (mode) {
			case 0:
				// 이동 x
				break;
			case 1:
				// 상 y-1
				--y;
				break;
			case 2:
				// 우 x+1
				++x;
				break;
			case 3:
				// 하 y+1
				++y;
				break;
			case 4:
				// 좌 x-1
				--x;
				break;
			}
			timeTable[t] = new int[] { x, y };
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			// 총 이동 시간 m
			int m = Integer.parseInt(st.nextToken());
			// BC의 개수 a
			int a = Integer.parseInt(st.nextToken());
			// 각 사람에게 m 시간 위치 입력해놓기
			Person userA = new Person(0, 0, m);
			st = new StringTokenizer(br.readLine());
			for (int t = 1; t <= m; t++) {
				userA.move(Integer.parseInt(st.nextToken()), t);
			}
			Person userB = new Person(9, 9, m);
			st = new StringTokenizer(br.readLine());
			for (int t = 1; t <= m; t++) {
				userB.move(Integer.parseInt(st.nextToken()), t);
			}
			BC[] BCs = new BC[a];
			// 각 BC 만들어 배열에 넣어주기
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				BCs[i] = new BC(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			// 각 시간동안
			for (int i = 0; i <= m; i++) {
				// A에 가능한 BC를 담기 위한 boolean 배열
				boolean[] availableA = new boolean[a];
				boolean[] availableB = new boolean[a];
				// 접근 가능한 BC의 개수가 3개(겹칠 경우), 4개(각각 다른 것 2개씩)이면 비교 가능
				for (int j = 0; j < a; j++) {
					int xBC = BCs[j].locX;
					int yBC = BCs[j].locY;
					// A로부터 j번째 BC까지의 거리가 coverage보다 작거나 같으면
					if (Math.abs(userA.timeTable[i][0] - xBC)
							+ Math.abs(userA.timeTable[i][1] - yBC) <= BCs[j].coverage) {
						availableA[j] = true;
					}
					// B로부터 j번째 BC까지의 거리가 coverage보다 작거나 같으면
					if (Math.abs(userB.timeTable[i][0] - xBC)
							+ Math.abs(userB.timeTable[i][1] - yBC) <= BCs[j].coverage) {
						availableB[j] = true;
					}
				}
				int maxA = 0;
				int maxB = 0;
//여기부터
				// 2-1. 각자 비교
				for (int j = 0; j < a; j++) {
					if (availableA[j] && !availableB[j]) {
						maxA = Math.max(maxA, BCs[j].perfomance);
					} else if (!availableA[j] && availableB[j]) {
						maxB = Math.max(maxB, BCs[j].perfomance);
					}
				}

				// 2-2. A,B 둘이 동시에 접속 가능
				for (int j = 0; j < a; j++) {
					if (availableA[j] && availableB[j]) {
						int tmpPerfom = BCs[j].perfomance;
						if (maxA < tmpPerfom && maxA <= maxB) {
							maxA = tmpPerfom;
						} else if (maxB < tmpPerfom && maxB <= maxA) {
							maxB = tmpPerfom;
						}
					}
				}
//여기까지 경우의 수를 잘 처리하지 못했다
				// 이런 경우의 수를 일단 되는대로라도 처리를 해줘야 할텐데, 자꾸 더 간단한 방법이 없을까 요령 피우다가 분명 거의 다 오고도 해결을 못하고 있었다..
				// 어떤 좋은 아이디어가 떠오르는 느낌이 든다고 해도 어쭙잖게 하지 말고 일단 답부터 구하고 봐라

				answer += maxA + maxB;
			}
			sb.append(String.format("#%d %d%n", tc, answer));
		}
		System.out.println(sb);
	}

}