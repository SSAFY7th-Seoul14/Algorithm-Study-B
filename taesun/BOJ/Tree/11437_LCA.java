import java.io.*;
import java.util.*;

/*
* 이 문제에선 누가 부모인지 안알려주므로 dfs 과정에서 부모도 같이 구해주어야 한다. 
* lca에 대해 아직 구현이 미숙하니 더 공부해보도록 하자 
* 지금 구현한 방식의 시간 복잡도는 최악의 경우 n이 된다, log n으로 하려면 희소 배열을 사용해야함 
*/

public class Main {

	static int n, m, ans, h;
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
	static int parent[] = new int[50001];
	static int depth[] = new int[50001];
	static boolean visit[] = new boolean[50001];
	// 미리 depth를 전부 구해서 저장하기 
	static void dfs(int idx, int d, int p)
	{
		parent[idx] = p;
		depth[idx] = d;
		if (!visit[idx])
			visit[idx] = true;
		else
			return;
		int next;
		for (int i=0; i<tree.get(idx).size(); i++)
		{
			next = tree.get(idx).get(i);
			if (!visit[next])
				dfs(next, d + 1, idx);
		}
	}
	static int lca(int x, int y)
	{
		int a = x, b = y;
		while (true)
		{
			if (depth[a] == depth[b])  // 깊이가 일치할때 
			{
				if (a == b)      // 한쪽이 다른 한쪽의 조상인 경우 예외처리 
					return a;
				if (parent[a] == parent[b])  // 부모도 일치하면 정답  
					return parent[a];
				else                  // 부모가 일치하지 않으면 둘다 1단계 위로
				{
					a = parent[a];
					b = parent[b];
					continue;
				}
			}
			else   // 뎁스가 다르면 더 깊은 쪽을 1칸 위로 
			{
				if (depth[a] > depth[b])
					a = parent[a];
				else 
					b = parent[b];
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		for (int i=0; i<=50000; i++)
			tree.add(new ArrayList<Integer>());
		int a,b;
		for (int i=0; i<n-1; i++)
		{
			a = scan.nextInt();
			b = scan.nextInt();
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		// 루트는 1
		dfs(1, 0 , 0);
		//for (int i=1; i<=n; i++)
			//System.out.println(i +" " +depth[i] + " " + parent[i]);
		m = scan.nextInt();
		for (int i=0; i<m; i++)
		{
			a = scan.nextInt();
			b = scan.nextInt();
			System.out.print(lca(a, b) + "\n");
		}
	}
}
