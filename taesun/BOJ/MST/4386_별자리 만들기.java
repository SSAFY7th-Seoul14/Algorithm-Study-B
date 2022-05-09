import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
	double w;
	int x,y;
	public pair(double w, int x, int y) {
		this.w = w;
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(pair o) {
		return (int)(this.w - o.w);
	}
	
}
public class Main {

	static long n, m,w, h, k;
	static int cycle[] = new int[101]; 
	static int find(int i)
	{
		if (i == cycle[i])
			return i;
		return cycle[i] = find(cycle[i]);
	}
	static void union_set(int a, int b)
	{
		int root1 = find(a), root2 = find(b);
		if (root1 == root2)
			return;
		cycle[root2] = root1;
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		double a, b, ans = 0 ,weight ,x1 ,y1 , x2 , y2 , w;
		int x, y;
		double star[][] = new double[103][2];
		ArrayList<pair> arr = new ArrayList<pair>();
		
		for (int i = 0; i < 101; i++)
			cycle[i] = i;
		
		for (int i = 0; i < n; i++)  // 일단 좌표 저장
		{
			a = scan.nextDouble();
			b = scan.nextDouble();
			star[i + 1][0] = a;
			star[i + 1][1] = b;
		}
		for (int i = 1; i <= n; i++) // 돌면서 간선 정보를 벡터에 저장
		{
			for (int j = i+1; j <= n; j++)
			{
				if (i != j)
				{
					x1 = star[i][0];
					y1 = star[i][1];
					x2 = star[j][0];
					y2 = star[j][1];
					weight = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
					arr.add(new pair(weight, i, j));
				}
			}
		}
		Collections.sort(arr);
		for (int i = 0; i < arr.size(); i++) // 저장된 간선 정렬하고 작은 것부터 추출
		{
			w = arr.get(i).w;
			x = arr.get(i).x;
			y = arr.get(i).y;
			if (find(x) == find(y)) // x y 가 이미 그래프에 존재할 경우 사이클을 막기 위해서 pass
				continue;
			else
			{
				union_set(x, y);
				ans += w;
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("%.2f", ans));
		System.out.println(sb.toString());
	}
}