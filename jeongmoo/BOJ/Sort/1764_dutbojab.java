import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

// 백준 1764번. 듣보잡
public class BOJ1764_dutbojab {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		HashSet<String> set = new HashSet<>();
		TreeSet<String> result = new TreeSet<>();
		
		for (int i = 0; i < n; i++) {
			set.add(br.readLine());
		}
		for (int i = 0; i < m; i++) {
			String word = br.readLine();
			boolean res = set.add(word);
			if (!res)
				result.add(word);
		}
		
		System.out.println(result.size());
		for (String str : result) {
			System.out.println(str);
		}
	}
}
