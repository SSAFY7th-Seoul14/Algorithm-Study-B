import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916 {

	static class Bus {
		int to, cost;
		Bus link;

		public Bus(int to, int cost, Bus link) {
			super();
			this.to = to;
			this.cost = cost;
			this.link = link;
		}

	}

	static class Vertex implements Comparable<Vertex> {
		int no, minCost;

		public Vertex(int no, int minCost) {
			super();
			this.no = no;
			this.minCost = minCost;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minCost - o.minCost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 도시의 개수 n <= 1,000
		int n = Integer.parseInt(br.readLine());
		// 버스의 개수 m <= 100,000
		int m = Integer.parseInt(br.readLine());

		// 사실 도시 개수가 1000개 밖에 안돼서 인접행렬로 풀어도 된다. 하지만 연습 차원으로 한번더 인접 리스트로 풀어보기

		Bus[] buses = new Bus[n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			// 버스 비용 cost
			int cost = Integer.parseInt(st.nextToken());
			buses[from] = new Bus(to, cost, buses[from]);
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		br.close();
		// 각 도시까지의 최소 비용을 저장해줄 costs 배열
		int[] costs = new int[n + 1];
		Arrays.fill(costs, Integer.MAX_VALUE);

		boolean[] confirmed = new boolean[n + 1];

		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		costs[start] = 0;
		pq.offer(new Vertex(start, costs[start]));

		while (!pq.isEmpty()) {
			// 현재 pq에서 최소 비용을 가진 Vertex 즉, 최소비용이 확정된 정점 꺼내기
			Vertex current = pq.poll();
			
			// 나온 최소 비용 정점이 목표 점이라면 종료
			if (current.no == end)
				break;

			// 최소비용 확정 짓기
			if (confirmed[current.no])
				continue;
			confirmed[current.no] = true;

			// 최소비용 후보 갱신
			for (Bus temp = buses[current.no]; temp != null; temp = temp.link) {
				if (!confirmed[temp.to] && costs[temp.to] > costs[current.no] + temp.cost) {
					costs[temp.to] = costs[current.no] + temp.cost;
					pq.offer(new Vertex(temp.to, costs[temp.to]));
				}
			}
		}
		System.out.println(costs[end]);
	}

}