

import java.io.*;
import java.util.*;

// BOJ / 파티 / G3 / 25분
// https://www.acmicpc.net/problem/1238

class Node implements Comparable<Node>{
	int end;
	int weight;
	
	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	@Override
	public int compareTo(Node o) {
		return this.weight-o.weight;
	}
	
}
public class Main_1238 {
	static int N,M,X;
	static int distGo[], distBack[], dist[]; //distGo: 가는 길, distBack: 오는 길, dist: 가는길+오는길
	static List<ArrayList<Node>> graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		
		//그래프 초기화
		for(int i=0;i<N+1;i++) {
			graph.add(new ArrayList<Node>());
		}
		//그래프 입력
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(start).add(new Node(end,weight));
			 
		}
		//각자 집에서 X번째(파티장소)까지 가는 거리 계산
		distGo = new int[N+1];
		for(int i=1;i<N+1;i++) {
			int d[] =  dijkstra(i);
			distGo[i]=d[X];
		}
		//X에서 각자 집까지 가는 거리 계산
		distBack = new int[N+1];
		distBack = dijkstra(X);
		//오고 가는 길 계산
		dist = new int[N+1];
		for(int i=0;i<N+1;i++) {
			dist[i] = distGo[i]+distBack[i];
		}
		int res=Integer.MIN_VALUE;
		for(int i=1;i<N+1;i++) {
			res = Math.max(res, dist[i]);
		}
		System.out.println(res);
	}

	private static int[] dijkstra(int start) {
		int d[] = new int[N+1];//임시 저장용 distance
		boolean visited[] = new boolean[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(d, Integer.MAX_VALUE);
		
		d[start]=0;
		pq.offer(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int cur = current.end;
			
			if(visited[cur]) continue;
			
			visited[cur]=true;
			
			for(Node node: graph.get(cur)) {
				if(!visited[node.end] && d[node.end]>d[cur]+node.weight) {
					d[node.end]=d[cur]+node.weight;
					pq.offer(new Node(node.end,d[node.end]));
				}
				
			}
		}
		return d;
	}

}
