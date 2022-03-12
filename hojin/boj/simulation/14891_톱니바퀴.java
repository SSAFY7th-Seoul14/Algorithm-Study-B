import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891_톱니바퀴 {
	static class Command {
		// 회전 시킬 톱니 번호, 방향은 1이면 시계, -1이면 반시계
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
		for (int i = 1; i <= 4; i++) {
			gear[i] = br.readLine().toCharArray();
		}
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
		// 비교하고 돌아가는지 여부를 기억해줄 compare 배열
		boolean[] compare = new boolean[4];
		for (int i = 0; i < k; i++) {
			for (int j = 1; j <= 3; j++) {
				// 1번의 3시와 2번의 9시 비교, 2번의 3시와 3번의 9시 비교, 3번의 3시와 4번의 9시 비교
				// 3시는 north + 2 % 8, 9시는 north + 6 % 8
				compare[j] = (gear[j][(north[j] + 2) % 8] != gear[j + 1][(north[j + 1] + 6) % 8]);
			}
			int curNo = commands[i].gearNo;
			int dirNo = commands[i].rotDir;
			north[curNo] += 8 - dirNo;
			switch (curNo) {
			case 1:
				if (compare[1]) {
					north[2] += 8 + dirNo;
					if (compare[2]) {
						north[3] += 8 - dirNo;
						if (compare[3]) {
							north[4] += 8 + dirNo;
						}
					}
				}
				break;
			case 2:
				if (compare[1]) {
					north[1] += 8 + dirNo;
				}
				if (compare[2]) {
					north[3] += 8 + dirNo;
					if (compare[3]) {
						north[4] += 8 - dirNo;
					}
				}
				break;
			case 3:
				if (compare[3]) {
					north[4] += 8 + dirNo;
				}
				if (compare[2]) {
					north[2] += 8 + dirNo;
					if (compare[1]) {
						north[1] += 8 - dirNo;
					}
				}
				break;
			case 4:
				if (compare[3]) {
					north[3] += 8 + dirNo;
					if (compare[2]) {
						north[2] += 8 - dirNo;
						if (compare[1]) {
							north[1] += 8 + dirNo;
						}
					}
				}
				break;
			}
			for (int j = 1; j <= 4; j++) {
				north[j] %= 8;
			}
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