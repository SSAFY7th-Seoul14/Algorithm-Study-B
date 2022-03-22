import java.io.*;
import java.util.*;

// BOJ / G5 / 녹색 옷 입은 애가 젤다지? / 70분
// https://www.acmicpc.net/problem/4485
class Node implements Comparable<Node>{
	int no; //노드번호
	int weight; //비용
	public Node(int no, int weight) {
		super();
		this.no = no;
		this.weight = weight;
	}
	@Override
	public int compareTo(Node o) {
		return this.weight-o.weight;
	}
}
public class Main_4485_dijkstra {
	static int N;
	static int map[][];
	static List<ArrayList<Node>> graph;
	static int d[]; 
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt=1; //출력을 위한 index
		while(true) {
			N = Integer.parseInt(br.readLine());

			if(N==0) break; //반복 종료
			
			graph = new ArrayList<ArrayList<Node>>(); // N*N개의 node를 가지는 그래프 생성
			for(int i=0;i<N*N;i++)
				graph.add(new ArrayList<Node>());
			
			map = new int[N][N];
			d = new int[N*N];
			Arrays.fill(d, Integer.MAX_VALUE);
			
			
			StringTokenizer st =null;
			for(int i=0;i<N;i++) { //map 입력
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					int curNum = N*i+j;
					for(int k=0;k<4;k++) { //현재 노드의 4방에 있는 노드들 연결
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(nx>=0 && nx<N && ny>=0 && ny <N) {
							int nextNum = N*nx+ny;
							graph.get(curNum).add(new Node(nextNum, map[nx][ny]));
							
						}
					}
				}	
			}
			dijkstra(0); //비용 최솟값 찾기 시작
			
			System.out.println("Problem "+(cnt++)+": "+ d[N*N-1]); //최솟값 출력
		}
	}


	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start,map[0][0]));
		d[start] = map[0][0];
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(d[cur.no]<cur.weight) continue;
			for(int i=0;i<graph.get(cur.no).size();i++) {
				Node next = graph.get(cur.no).get(i);
				int cost = d[cur.no]+next.weight;
				if(cost<d[next.no]) {
					d[next.no] = cost;
					pq.offer(new Node(next.no, cost));
				}
			}
		}
	}
}
