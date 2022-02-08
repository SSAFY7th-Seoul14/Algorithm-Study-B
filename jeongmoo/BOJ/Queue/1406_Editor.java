import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

// 백준1406번. 에디터
public class BOJ1406_Editor {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		

		LinkedList<Character> list = new LinkedList<>();
		char[] origin = br.readLine().toCharArray();
		ListIterator<Character> iter = list.listIterator();
		for (char c : origin) {
			iter.add(c);
		}
		
		// list에서 remove나 insert를 하면 n만큼 반복한다.
		// listIterator는 진짜 커서임.		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "P":
				char c = st.nextToken().charAt(0);
				iter.add(c);
				break;
			case "L":
				if (iter.hasPrevious())
					iter.previous();
				break;
			case "D":
				if (iter.hasNext())
					iter.next();
				break;
			case "B":
				if (iter.hasPrevious()) {
					iter.previous();
					iter.remove();
				}					
				break;
			default:
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (char c : list) {
			sb.append(c);
		}
		System.out.println(sb.toString());
	}
}
