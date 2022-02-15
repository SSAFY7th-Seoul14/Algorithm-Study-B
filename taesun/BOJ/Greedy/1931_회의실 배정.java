import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
	long x,y;
	public pair(long a, long b) {
		this.x = a;
		this.y = b;
	}
	@Override
	public int compareTo(pair p) {
		if	(this.x == p.x)
	         return Long.compare(this.y, p.y);
	    else
	         return Long.compare(this.x, p.x);
	}
}

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Integer.parseInt(br.readLine());
		long a,b, start = 0, ans = 0;
		ArrayList<pair> list = new ArrayList<pair>();
		// 끝나는 시간 위주로 소팅하기 위해 pair 앞쪽에 종료시간 넣음 
		for (int i=0; i<n; i++)
		{
			String s[] = br.readLine().split(" ");
			b = Integer.parseInt(s[0]);
			a = Integer.parseInt(s[1]);
			list.add(new pair(a,b));
		}
		Collections.sort(list);
		// 끝나는 시간이 빠른 순으로 우선적으로 채우면서 진행 
		for (int i=0; i<list.size(); i++)
		{
			if (start <= list.get(i).y)
			{
				ans++;
				start = list.get(i).x;
			}
		}
		System.out.println(ans);
		return;
	}	
}