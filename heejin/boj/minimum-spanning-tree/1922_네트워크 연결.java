import java.io.*;
import java.util.*;

// BOJ / 네트워크 연결 / G4 / 20분
// https://www.acmicpc.net/problem/1922

class Computer implements Comparable<Computer>{
	int a;
	int b;
	int c;
	public Computer(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	@Override
	public int compareTo(Computer o) {
		return this.c-o.c; //비용 순으로 오름차순 정렬
	}
	
}
public class Main_1922 {
	static int N,M;
	static PriorityQueue<Computer> pq;
	static int parents[];
	static int res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parents = new int[N+1];
		pq = new PriorityQueue<>();

		makeParents(); //parents 배열 초기화

		StringTokenizer st = null;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Computer(a,b,c));
		}
		 
		while(!pq.isEmpty()) {
			Computer cur = pq.poll();
			int from = cur.a, to = cur.b, cost = cur.c;
			if(find(from)!=find(to)) {
				union(from, to);
				res +=cost;
			}
		}
		System.out.println(res);
	}
	public static void makeParents() {
		for(int i=0;i<N+1;i++)
			parents[i] = i;
	}
	
	public static int find(int x) {
		if(x==parents[x]) return x;
		else 
			return parents[x]=find(parents[x]);
	}
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot)
			return false;
		else if(aRoot<bRoot)
			parents[bRoot] = aRoot;
		else
			parents[aRoot]= bRoot;
		return true;
	}

}
