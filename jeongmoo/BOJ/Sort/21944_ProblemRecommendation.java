import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

// 백준 21944번. 문제 추천 시스템 Version 2
public class BOJ21944_ProblemRecommendation {
	static class Problem implements Comparable<Problem> {
		int p, l, g;

		public Problem(int p, int l, int g) {
			this.p = p;
			this.l = l;
			this.g = g;
		}

		@Override
		public int compareTo(Problem o) {
			return this.p - o.p; // 문제번호 기준 정렬
		}
	}
	
	static int n, m;
	// 전체 리스트
	static TreeMap<Integer, Problem> allList = new TreeMap<>();
	// 알고리즘 분류별 리스트 (분류, 난이도, 문제번호)
	static TreeMap<Integer, TreeMap<Integer, TreeSet<Integer>>> list = new TreeMap<>();
	// 난이도별 리스트 (난이도, 문제번호)
	static TreeMap<Integer, TreeSet<Integer>> Llist = new TreeMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int p, l, g, x, tempP;

		// 추천 문제 리스트 문제 등록
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken()); // 문제번호
			l = Integer.parseInt(st.nextToken()); // 난이도
			g = Integer.parseInt(st.nextToken()); // 알고리즘 분류
			addList(p, l, g);
		}

		// 명령어 입력 및 처리
		StringBuilder sb = new StringBuilder();
		m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			switch(command) {
			case "recommend":
				g = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				// 알고리즘 분류가 G인 리스트
				TreeMap<Integer, TreeSet<Integer>> temp = list.get(g);
				if (x == 1)
					sb.append(temp.get(temp.lastKey()).last()).append("\n"); // 어려운문제 중 문제번호 큰
				else
					sb.append(temp.get(temp.firstKey()).first()).append("\n"); // 쉬운문제 중 문제번호 작은
				break;
			case "recommend2":
				x = Integer.parseInt(st.nextToken());
				if (x == 1)
					sb.append(Llist.get(Llist.lastKey()).last()).append("\n"); // 어려운문제 중 문제번호 큰
				else
					sb.append(Llist.get(Llist.firstKey()).first()).append("\n"); // 쉬운문제 중 문제번호 작은
				break;
			case "recommend3":
				x = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				tempP = -1;
				if (x == 1) {
					// 난이도 l보다 크거나 같은 문제 중 쉬운 문제 중 작은 번호
					for (int j = l; j <= 100; j++) {
						if (Llist.get(j) != null && !Llist.get(j).isEmpty()) {
							tempP = Llist.get(j).first();
							break;
						}
					}
					sb.append(tempP).append("\n");
				} else {
					// 난이도가 l보다 작은 문제 중 가장 어려운 문제 중 큰 번호
					for (int j = l-1; j >= 1; j--) {
						if (Llist.get(j) != null && !Llist.get(j).isEmpty()) {
							tempP = Llist.get(j).last();
							break;
						}
					}
					sb.append(tempP).append("\n");
				}
				break;
			case "add":
				p = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				g = Integer.parseInt(st.nextToken());
				addList(p, l, g);
				break;
			case "solved":
				p = Integer.parseInt(st.nextToken());
				removeList(p);
				break;
			}
		}
		System.out.println(sb);
	}
	
	public static void addList(int p, int l, int g) {
		Problem problem = new Problem(p, l, g);
		// 전체 리스트에 삽입
		allList.put(p, problem);
		
		// 알고리즘 분류별 리스트에 삽입
		if (list.get(g) == null)
			list.put(g, new TreeMap<>());
		
		if (list.get(g).get(l) == null)
			list.get(g).put(l, new TreeSet<>());
		
		list.get(g).get(l).add(p);
		
		// 난이도 리스트에 삽입
		if (Llist.get(l) == null)
			Llist.put(l, new TreeSet<>());
		
		Llist.get(l).add(p);
	}
	
	public static void removeList(int p) {
		Problem problem = allList.get(p);
		
		// 전체 리스트에서 제거
		allList.remove(p);
		
		// 알고리즘 분류별 리스트에서 제거
		list.get(problem.g).get(problem.l).remove(p);
		if (list.get(problem.g).get(problem.l).isEmpty())
			list.get(problem.g).remove(problem.l);
		
		if (list.get(problem.g).isEmpty())
			list.remove(problem.g);
		
		
		// 난이도 리스트에서 제거
		Llist.get(problem.l).remove(p);
		if (Llist.get(problem.l).isEmpty())
			Llist.remove(problem.l);
	}
}
