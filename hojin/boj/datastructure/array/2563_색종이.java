import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader in;
	static int n, papers[][], dx, dy, area = 0;
	static String[] temp;

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		papers = new int[100][100];
		for (int i = 0; i < n; i++) {
			temp = in.readLine().split(" ");
			dx = Integer.parseInt(temp[0]);
			dy = Integer.parseInt(temp[1]);
			for (int x = dx; x < dx + 10; x++) {
				for (int y = dy; y < dy + 10; y++) {
					if (papers[y][x] == 0) {
						papers[y][x] = 1;
					}
				}
			}
		}
		for (int[] row : papers) {
			for (int el : row) {
				area += el;
			}
		}

		System.out.println(area);
	}
}
