import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int no;
		ArrayList<Node> next, prev;

		public Node(int no) {
			this.no = no;
			this.next = new ArrayList<>();
			this.prev = new ArrayList<>();
		}

		public int explore() {
			boolean[] visited = new boolean[n];
			int cnt = 1;
			Queue<Node> q = new LinkedList<>();
			q.offer(this);
			visited[this.no] = true;
			while (!q.isEmpty()) {
				Node cur = q.poll();
				for (int i = 0; i < cur.next.size(); ++i) {
					Node next = cur.next.get(i);
					if (!visited[next.no]) {
						visited[next.no] = true;
						++cnt;
						q.offer(next);
					}
				}
			}
			visited = new boolean[n];
			q.offer(this);
			visited[this.no] = true;
			while (!q.isEmpty()) {
				Node cur = q.poll();
				for (int i = 0; i < cur.prev.size(); ++i) {
					Node prev = cur.prev.get(i);
					if (!visited[prev.no]) {
						visited[prev.no] = true;
						++cnt;
						q.offer(prev);
					}
				}
			}
			return cnt;
		}

	}

	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        // 학생들의 수2<= n<-500
        n = Integer.parseInt(st.nextToken());
        // 학생 키 비교 횟수 0<=m<=n(n-1)/2 = 1~n-1까지의 합
        int m = Integer.parseInt(st.nextToken());
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; ++i) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            nodes[from].next.add(nodes[to]);
            nodes[to].prev.add(nodes[from]);
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            // 해당 node에서 탐색했을 때 전부 다 탐색 가능하면 자신의 위치를 알 수 있다.
            if (nodes[i].explore() == n)
                ++cnt;
        }
		System.out.println(cnt);
		br.close();
	}

}