import java.util.*;
import java.io.*;

public class BOJ9935_문자열폭발 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] target = br.readLine().toCharArray();
		char[] explosion = br.readLine().toCharArray();
		// 최종 문자열을 담아줄 dq
		ArrayDeque<LinkedList<Character>> dq = new ArrayDeque<>();
		int lenExp = explosion.length;
		int lenTarget = target.length;
		// q를 생성해서 넣고
		LinkedList<Character> q = new LinkedList<>();
		dq.offer(q);
		for (int i = 0; i < lenTarget; ++i) {
			char tmp = target[i];
			// 기준은 첫번째 문자이므로 첫번째 자리와 일치하는 문자 나타나면 새로운 q를 생성해서 dq에 넣는다.
			if (tmp == explosion[0]) {
				q = new LinkedList<>();
				q.add(target[i]);
				dq.add(q);
			} else {
				// 첫번째 문자열과 일치하지 않으면 일단 dq 마지막 q에 전부 넣는다.
				LinkedList<Character> peekLast = dq.peekLast();
				// 문자열 담아줄 q가 아예 없을 경우 생성해서 넣어주기
				if (peekLast == null)
					peekLast = new LinkedList<>();
				peekLast.add(tmp);
			}
			// 문자열 넣어주고 처리해주려면 마지막 q를 다시 봐준다.
			LinkedList<Character> peekLast = dq.peekLast();
			int size = peekLast.size();
			// 마지막에 있는 q의 길이가 같고 현재 문자열이 폭발 문자열 마지막과 같다면
			if (size == lenExp && tmp == explosion[lenExp - 1]) {
				// 해당 q를 뒤져보면서 일치하는지 확인후
				if (check(peekLast, explosion)) {
					// 일치하면 pop해준다.
					dq.removeLast();
				}
			}
		}
		// dq에 담겨있는 문자열들이 답.
		StringBuilder sb = new StringBuilder();
		while (!dq.isEmpty()) {
			LinkedList<Character> one = dq.poll();
			while (!one.isEmpty()) {
				sb.append(one.poll());
			}
		}
		if (sb.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb);
		}
		br.close();
	}

	private static boolean check(LinkedList<Character> q, char[] explosion) {
		int len = explosion.length;
		for (int i = len - 1; i >= 0; --i) {
			if (q.get(i) != explosion[i]) {
				return false;
			}
		}
		return true;
	}

}
