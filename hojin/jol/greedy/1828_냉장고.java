import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

	static class DegreeFromTo implements Comparable<DegreeFromTo> {
		int min, max;

		public DegreeFromTo(int min, int max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public int compareTo(DegreeFromTo o) {
			return this.max == o.max ? this.min - o.min : this.max - o.max;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		DegreeFromTo[] deg = new DegreeFromTo[n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			deg[i] = new DegreeFromTo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		br.close();
		Arrays.sort(deg);
		DegreeFromTo first = deg[0];
		int cnt = 1;
		for (int i = 1; i < n; i++) {
			if (first.max < deg[i].min) {
				cnt++;
				first = deg[i];
			}
		}
		System.out.println(cnt);
	}

}