import java.util.*;
import java.io.*;

public class SWEA5515_2016년요일맞추기 {

	static int[] dayPerMonth = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			st = new StringTokenizer(br.readLine());
			int month = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			int firstDay = 4;
			int dayCnt = 0;
			for (int i = 1; i < month; ++i) {
				dayCnt += dayPerMonth[i];
			}
			dayCnt += day - 1;
			int answer = (firstDay + dayCnt) % 7;
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

}
