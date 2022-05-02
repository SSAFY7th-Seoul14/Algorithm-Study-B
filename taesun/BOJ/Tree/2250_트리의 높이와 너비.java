import java.io.*;
import java.util.*;

// 반례
/*
5
5 -1 -1
1 4 5
4 -1 -1
2 1 3
3 -1 -1	

답 : 2 4
 * 
 */

public class Main {

	static int n, m, ans, w, h;
	
	// 트리 정보 저장 
	static int tree[][] = new int[10001][2];
	// 노드의 자식 개수 저장 
	static int child[][] = new int[10001][2];
	// 특정 depth에서 가장 큰 노드 번호와 작은 노드 번호 저장 
	static int p[][] = new int[10001][2];
	// 노드의 깊이 저장 
	static int depth[] = new int[10001];
	static int parent[] = new int[10001];
	static int dfs(int idx, int d)
	{
		depth[idx] = d;
		// 해당 depth에서 가장 우측 노드와 좌측 노드 찾기
		
		// 가장 왼쪽은 preorder 하면서 제일 처음에 방문
		if (p[d][0] == Integer.MAX_VALUE)
		p[d][0] = idx;
		
		// 가장 오른쪽은 preorder 하면서 제일 나중에 방문
		p[d][1] = idx;
		
		// 왼쪽 자식 수 카운트 
		if (tree[idx][0] != -1)
			child[idx][0] = dfs(tree[idx][0], d + 1);
		// 오른쪽 자식 수 카운트 
		if (tree[idx][1] != -1)
			child[idx][1] = dfs(tree[idx][1], d + 1);
		return child[idx][0] + child[idx][1] + 1;
	}
	
	// 주의점 
	// 노드 번호가 작다고 무조건 왼족이 아니다
	// 루트 노드가 1이 아닐 수도 있다 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		
		int a,b,c;
		for (int i =0; i<n; i++)
		{
			a = scan.nextInt();
			b = scan.nextInt();
			c = scan.nextInt();
			tree[a][0] = b;
			tree[a][1] = c;
			if (b != -1)
				parent[b] = a;
			if (c != -1)
				parent[c] = a;
		}
		for (int i=1; i<=n; i++)
			p[i][0] = Integer.MAX_VALUE;
		
		int left, right, len = 0, level = 1, prev, root = 1;
		while (parent[root] != 0)
			root = parent[root];
		dfs(root, 1);
		for (int i=1; i<=n; i++)
		{
			left = p[i][0];
			right = p[i][1];
			// 해당 depth에 노드가 없는 경우는 패스 
			if (left == Integer.MAX_VALUE || right == 0)
				continue;
			len = n - child[left][0] - child[right][1];
			// 부모를 거슬러 올라가면서 내 왼쪽에 있는 노드들을 빼낸다 
			prev = parent[left];
			while (left != root)
			{
				if (tree[prev][1] == left)
					len -= (child[prev][0] + 1);
				left = prev;
				prev = parent[left];
				//System.out.println(len);
			}
			
			prev = parent[right];
			while (right != root)
			{
				if (tree[prev][0] == right)
					len -= (child[prev][1] + 1);
				right = prev;
				prev = parent[right];
				//System.out.println(len);
			}
			//System.out.println("depth : " + i + " "+ p[i][0] + " " + p[i][1] + " " + len);
			if (len > ans)
			{
				ans = len;
				level = i;
			}
		}
		System.out.println(level + " " + ans);
	}
}