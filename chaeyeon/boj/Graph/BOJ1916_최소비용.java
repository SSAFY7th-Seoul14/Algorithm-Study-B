package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916_최소비용 {
	public static class Bus implements Comparable<Bus> {
		int to, w;
		Bus link;
		
		public Bus(int to, int w, Bus link) {
			super();
			this.to = to;
			this.w = w;
			this.link = link;
		}

		@Override
		public int compareTo(Bus o) {
			return this.w-o.w;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		Bus[] busList = new Bus[N+1];
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			busList[from] = new Bus(to,w,busList[from]);
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		distance[start] = 0;

		PriorityQueue<Bus> pq = new PriorityQueue<>();
		pq.offer(new Bus(start, 0, null));
		
		while(!pq.isEmpty()) {
			Bus min = pq.poll();
			int minBus = min.to;
			
			if(minBus == end) break;
			if(visited[minBus]) continue;
			visited[minBus] = true;
			
			for(Bus tmp = busList[minBus]; tmp != null; tmp = tmp.link) {
				if(!visited[tmp.to] && distance[tmp.to] > distance[minBus] + tmp.w) {
					distance[tmp.to] = distance[minBus] + tmp.w;
					pq.offer(new Bus(tmp.to, distance[tmp.to], null));
				}
				
			}
		}
		
		System.out.println(distance[end]);
		
	}

}
