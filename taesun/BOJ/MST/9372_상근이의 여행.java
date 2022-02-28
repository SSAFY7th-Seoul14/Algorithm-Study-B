import java.io.*;
import java.util.*;

public class Main {
	static int ans, n, m;
	static int arr[] = new int[1001];
	
	static int find(int idx)
	{
		// 자기 자신이 루트 -> 아직 다른 집합에 속한적 없음 
		if (idx == arr[idx])
			return idx;
		// path compression -> 매우 중요
		return arr[idx] = find(arr[idx]);
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
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		int a,b;
		for (int k = 0; k < test; k++)
		{
			ans = 0;
			n = scan.nextInt();
			m = scan.nextInt();
			
			// 반드시 초기 루트값을 자기 자신으로 초기화해야 유니온 파인드 사용가능
			for (int i = 0; i < 1001; i++)
				arr[i] = i;
			for (int i = 0; i < m; i++)
			{
				a = scan.nextInt();
				b = scan.nextInt();
				if (find(a) == find(b)) // x y 가 이미 그래프에 존재할 경우 사이클을 막기 위해서 pass
					continue;
				else
				{
					union_set(a, b);
					ans++;
				}
			}
			System.out.println(ans);
		}
	}	
}
