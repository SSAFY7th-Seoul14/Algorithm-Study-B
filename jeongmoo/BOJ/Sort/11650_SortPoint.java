import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 백준11650번. 좌표 정렬하기
class Pair implements Comparable<Pair> {
	int x, y;
	Pair(int x, int y) {
		this.x= x;
		this.y = y;
	}
	
	@Override
	public int compareTo(Pair o) {
		if (this.x < o.x)
			return -1;
		else if (this.x > o.x)
			return 1;
		
		if (this.y < o.y)
			return -1;
		else if (this.y > o.y)
			return 1;
		
		return 0;
	}
}

public class BOJ11650_SortPoint {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		ArrayList<Pair> list = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for (Pair pair : list) {
			sb.append(pair.x + " " + pair.y + "\n");
		}
		System.out.println(sb.toString());
	}
}
