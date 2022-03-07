import java.io.*;
import java.util.*;


public class Main {
	static int n, ans;
	static int arr[] = new int[100003];
	// 방문처리와 별개로 이미 사이클에 포함된건지 체크하는 배열이 하나 더 있어아 한다
	static boolean visit[] = new boolean[100003];
	static boolean cycle[] = new boolean[100003];
	static void dfs(int idx)
	{
		// 우선 일반적으로 dfs를 돌면서 방문 체크 
		visit[idx] = true;
		int next = arr[idx];
		if (!visit[next])
			dfs(next);
		// 여기가 중요 , 내가 사이클에 포함된건지 아닌지 확인
		// 내가 방문은 했지만 아직 사이클 판정을 받지 않은 경우
		if (visit[next] && !cycle[next])
		{
			int curr = next;
			// 이 문제 조건에선 사이클이 존재할 수 밖에 없어서 항상 while이 종료됨
			while (curr != idx)
			{
				curr = arr[curr];
				ans--;
			}
			ans--;
		}
		// 사이클 판정을 한번 한 노드는 더이상 할 필요 x 
		cycle[idx] = true;
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int t=0; t<test; t++)
		{
			n = scan.nextInt();
			ans = n;
			for (int i=1; i<=n; i++)
				arr[i] = scan.nextInt();
			for (int i = 1; i<=n; i++)
			{
				if (!visit[i])
					dfs(i);
				//System.out.println(i + " " + ans);
			}
			System.out.println(ans);
			for (int i=0; i<100003; i++)
			{
				arr[i] = 0;
				cycle[i] = visit[i] = false;
			}
		}
	}
}
