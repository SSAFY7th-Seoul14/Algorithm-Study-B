import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
	int x,y;
	public pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(pair o) {
		if (this.x != o.x)
			return this.x - o.x;
		else
			return this.y - o.y;
	}
	
}
/*
반례
4
1 2
1 4
2 6
4 5
답 : 2
 */
public class Main {

	static long n, m, ans, w, h, k;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		int a,b, d,t;
		
		PriorityQueue<pair> q = new PriorityQueue<pair>();
		PriorityQueue<Integer> last = new PriorityQueue<Integer>();
		for (int i=0; i<n; i++)
		{
			a = scan.nextInt();
			b = scan.nextInt();
			q.add(new pair(a,b));
		}
		d = q.peek().y;
		q.poll();
		last.add(d);
		while (!q.isEmpty())
		{
			a = q.peek().x;
			b = q.peek().y;
			d = last.peek(); // 현재까지 제일 빨리 끝난 수업 시간 
			if (a >= d) // 강의실을 이어서 쓸 수 있을때 
			{
				last.poll();
				last.add(b);
			}
			else // 강의실을 따로 써야할때 
				last.add(b);
			q.poll();
		}
		System.out.println(last.size());
	}
}