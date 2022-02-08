import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받기
		int T = 10;
		Queue<Integer> queue;
		for (int i = 1; i <= T; i++) {
			queue = new LinkedList<Integer>();
			int tc = Integer.parseInt(in.readLine());
			String input = in.readLine();

			// 입력 처리를 tokenizer로 해주는 거 연습해보기
			StringTokenizer st = new StringTokenizer(input, " ");

			// 입력 받고서 Queue로 처리해주기
			while (st.hasMoreTokens()) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			int forMinus = 1;
			// 빼줄 값 forMinus 1로 초기화
			while (true) {
				// forMinus = 6되면 다시 1로 초기화
				if (forMinus == 6)
					forMinus = 1;
				// poll하고 forMinus만큼 빼주고 forMinus++, 다시 Queue에 offer
				int offer = queue.poll() - forMinus++;
				// offer해줄 값이 0보다 작거나 같으면
				if (offer <= 0) {
					// Queue에 0 push
					queue.offer(0);
					// 종료
					break;
				} else {
					queue.offer(offer);
				}
			}
			System.out.print("#" + tc + " ");
			Iterator<Integer> iter = queue.iterator();
			while (iter.hasNext()) {
				System.out.print(iter.next() + " ");
			}
			System.out.println();
		}
	}
}

