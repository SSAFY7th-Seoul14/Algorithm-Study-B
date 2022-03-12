import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Command {
		// 회전 시킨 번호, 방향은 1이면 시계, -1이면 반시계
		int gearNo, rotDir;

		public Command(int gearNo, int rotDir) {
			this.gearNo = gearNo;
			this.rotDir = rotDir;
		}
	}

	static char[][] gear;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 12시부터 시계방향, n극 0, s극 1
		gear = new char[5][];
		gear[1] = br.readLine().toCharArray();
		gear[2] = br.readLine().toCharArray();
		gear[3] = br.readLine().toCharArray();
		gear[4] = br.readLine().toCharArray();
		// 회전횟수 1<=k<=1
		int k = Integer.parseInt(br.readLine());
		Command[] commands = new Command[k];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			// gear 번호, 1은 시계방향, -1은 반시계
			commands[i] = new Command(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		// target이 되는 방향은 각각 다 12시, 1번 3시, 2번 3시 9시, 3번 3시 9시, 4번 3시 9시
		int[] north = new int[5];
		boolean[] compare = new boolean[4];
		for (int i = 0; i < k; i++) {
			for (int j = 1; j <= 3; j++) {
				// 1번의 3시와 2번의 9시 비교
				// 2번의 3시와 3번의 9시 비교
				// 3번의 3시와 4번의 9시 비교
				// 3시는 north + 2 % 8
				// 9시는 north + 6 % 8
				compare[j] = (gear[j][(north[j] + 2) % 8] != gear[j + 1][(north[j + 1] + 6) % 8]);
			}
			int curNo = commands[i].gearNo;
			int dirNo = commands[i].rotDir;
			switch (curNo) {
			case 1:
				if (dirNo != 1) {
					north[1] += 1;
				} else {
					north[1] += 7;
				}
				if (compare[1]) {
					if (dirNo != 1) {
						north[2] += 7;
					} else {
						north[2] += 1;
					}
					if (compare[2]) {
						if (dirNo != 1) {
							north[3] += 1;
						} else {
							north[3] += 7;
						}
						if (compare[3]) {
							if (dirNo != 1) {
								north[4] += 7;
							} else {
								north[4] += 1;
							}
						}
					}
				}
				break;
			case 2:
				if (dirNo != 1) {
					north[2] += 1;
				} else {
					north[2] += 7;
				}
				if (compare[1]) {
					if (dirNo != 1) {
						north[1] += 7;
					} else {
						north[1] += 1;
					}
				}
				if (compare[2]) {
					if (dirNo != 1) {
						north[3] += 7;
					} else {
						north[3] += 1;
					}
					if (compare[3]) {
						if (dirNo != 1) {
							north[4] += 1;
						} else {
							north[4] += 7;
						}
					}
				}
				break;
			case 3:
				if (dirNo != 1) {
					north[3] += 1;
				} else {
					north[3] += 7;
				}
				if (compare[3]) {
					if (dirNo != 1) {
						north[4] += 7;
					} else {
						north[4] += 1;
					}
				}
				if (compare[2]) {
					if (dirNo != 1) {
						north[2] += 7;
					} else {
						north[2] += 1;
					}
					if (compare[1]) {
						if (dirNo != 1) {
							north[1] += 1;
						} else {
							north[1] += 7;
						}
					}
				}
				break;
			case 4:
				if (dirNo != 1) {
					north[4] += 1;
				} else {
					north[4] += 7;
				}
				if (compare[3]) {
					if (dirNo != 1) {
						north[3] += 7;
					} else {
						north[3] += 1;
					}
					if (compare[2]) {
						if (dirNo != 1) {
							north[2] += 1;
						} else {
							north[2] += 7;
						}
						if (compare[1]) {
							if (dirNo != 1) {
								north[1] += 7;
							} else {
								north[1] += 1;
							}
						}
					}
				}
				break;
			}
			north[4] %= 8;
			north[3] %= 8;
			north[2] %= 8;
			north[1] %= 8;
		}
		int sum = 0;
		for (int i = 1; i <= 4; i++) {
			if (gear[i][north[i]] == '1') {
				sum += Math.pow(2, i - 1);
			}
		}
		System.out.println(sum);
	}

}