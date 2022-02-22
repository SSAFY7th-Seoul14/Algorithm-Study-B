import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
	int x,y,z;
	public pair(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public int compareTo(pair o) {
		// TODO Auto-generated method stub
		return this.z - o.z;
	}
}

// 마을을 2개로 나눈다 -> 최소 스패닝 트리에서 간선 하나를 지우면??
// 가장 긴 간선을 지운다면 최소한으로 2개의 스패닝 트리가 생길 것이다.
public class Main {
	static int n, m;
	static int arr[] = new int[200001];
	
	static int find(int i)
	{
		if (i == arr[i])
			return i;
		return arr[i] = find(arr[i]);
	}
	static void union_set(int a, int b)
	{
		int root1 = find(a);
		int root2 = find(b);
		if (root1 == root2)
			return;
		arr[root1] = root2;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a,b,c, ans, MAX = 0;
		
		String s[] = br.readLine().split(" ");
		m = Integer.parseInt(s[0]);
		n = Integer.parseInt(s[1]);
		ans = 0;
		ArrayList<pair> edge = new ArrayList<pair>();
			
		for (int i=0; i<=m; i++)
			arr[i] = i;
		for (int i=0; i<n; i++)
		{
			s = br.readLine().split(" ");
			a = Integer.parseInt(s[0]);
			b = Integer.parseInt(s[1]);
			c = Integer.parseInt(s[2]);
			edge.add(new pair(a,b,c));
		}
		Collections.sort(edge);
		for (int i=0; i<edge.size(); i++)
		{
			a = edge.get(i).x;
			b = edge.get(i).y;
			c = edge.get(i).z;
			if (find(a) == find(b))
				continue;
			else
			{
				union_set(a,b);
				ans += c;
				MAX = Math.max(c, MAX);
			}
		}
		System.out.println(ans - MAX);
	}	
}
