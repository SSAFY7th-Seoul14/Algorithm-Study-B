import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();

		int T = Integer.parseInt(in.readLine()), cardsNum, half, first, second;
		String[] cardList;

		for (int tc = 1; tc <= T; tc++) {
			ans.append("#" + tc + " ");

			cardsNum = Integer.parseInt(in.readLine());
			cardList = in.readLine().split(" ");
			half = (int) Math.ceil(cardsNum / 2.0);
			first = 0;
			second = half;

			for (int i = 0; i < half; i++) {
				ans.append(cardList[first++] + " ");
				if (i == half - 1 && cardsNum % 2 == 1)
					continue;
				ans.append(cardList[second++] + " ");
			}
			ans.append("\n");
		}
		in.close();
		System.out.println(ans);
	}
}
