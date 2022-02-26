package 최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238_파티 {
	static List<Node>[] list;
	static int N, max=-1;
	public static class Node implements Comparable<Node>{
		int v, w;
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}

	}
	
	public static void main(String[] args) throws Exception {
		final int INF = Integer.MAX_VALUE;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		int[] party = new int[N+1];
		int[] home = new int[N+1];
		int[] distance = new int[N+1];
		int[] result = new int[N+1];
		//int max = -1;
		
		
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
			party[i] = INF;
			home[i] = INF;
			distance[i] = INF;
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to,w));
		}
		
		
		for(int i = 1; i <= N; i++) {
			party[i] = dijkstra(i,X);
		}
		dijkstra(X,home);
		
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, party[i] + home[i]);
		}
		System.out.println(max);
	
		
	}
	
	public static void dijkstra(int start, int[] distance) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		
		distance[start] = 0;
		pq.offer(new Node(start, distance[start]));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(visited[current.v]) continue;
			
			visited[current.v] = true;
			
			
			for(Node adj : list[current.v]) {
				if(!visited[adj.v] && distance[adj.v] > distance[current.v]+adj.w) {
					distance[adj.v] = distance[current.v]+adj.w;
					pq.offer(new Node(adj.v, distance[adj.v]));
				}
			}
		}
	}

	public static int dijkstra(int start, int des) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		distance[start] = 0;
		pq.offer(new Node(start, distance[start]));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(visited[current.v]) continue;
			if(des == current.v) return distance[current.v];
			
			visited[current.v] = true;
			max = Math.max(max, distance[current.v]);
			
			for(Node adj : list[current.v]) {
				if(!visited[adj.v] && distance[adj.v] > distance[current.v]+adj.w) {
					distance[adj.v] = distance[current.v]+adj.w;
					pq.offer(new Node(adj.v, distance[adj.v]));
				}
			}
		}
		return 0;
		
	}

}
