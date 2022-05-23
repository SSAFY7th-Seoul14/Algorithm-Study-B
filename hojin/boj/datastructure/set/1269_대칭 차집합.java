import java.util.*;
import java.io.*;

public class BOJ1269_대칭차집합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Set<Integer> set = new HashSet<Integer>();
		int na = stoi(st.nextToken());
		int nb = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < na; ++i) {
			set.add(stoi(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nb; ++i) {
			int tmp = stoi(st.nextToken());
			if (set.contains(tmp)) {
				set.remove(tmp);
			} else {
				set.add(tmp);
			}
		}
		System.out.println(set.size());
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
