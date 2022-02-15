import java.io.*;
import java.util.*;


class pair{
	int first,second;
	pair(int first, int second){
		this.first = first;
		this.second = second;
	}
}
public class Main {
	
	static int n, m, ans;
	static int arr[][] = new int[100][100];
	static int arr_copy[][] = new int[100][100];
	static int dx[] = { 0,0,-1,1 };
	static int dy[] = { -1,1,0,0 };
	static LinkedList<pair> q = new LinkedList<pair>(); 
	public static void print()
	{
		System.out.println();
		for (int i=0; i<n; i++)
		{
			for (int j=0; j <m; j++)
			{
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void bfs()
	{
		int x, y;
		while (!q.isEmpty())
		{
			x = q.getFirst().first;
			y = q.getFirst().second;
			for (int i = 0; i < 4; i++)
			{
				if ((x + dx[i] >= 0 && x + dx[i] < n) && (y + dy[i] >= 0 && y + dy[i] < m))
				{
					if (arr[x + dx[i]][y + dy[i]] == 0)
					{
						arr[x + dx[i]][y + dy[i]] = 2;
						q.addLast(new pair(x + dx[i], y + dy[i]));
					}
				}
			}
			q.removeFirst();
		}
	}
	
	public static void find()
	{
		int temp = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				// 바이러스의 시작 위치를 찾아서 큐에 넣어준다
				if (arr[i][j] == 2)
					q.addLast(new pair(i, j));
			}
		}
		// 모든 바이러스를 큐에 넣었으면 bfs 탐색을 통해 바이러스를 확산시킨다
		bfs();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				// 바이러스가 퍼지지 않은 공간이면 +1을 한다
				if (arr[i][j] == 0)
					temp++;
				else if (arr[i][j] != 3)
					arr[i][j] = arr_copy[i][j];
			}
		}
		// 최댓값을 갱신해준다
		ans = Math.max(ans, temp);
	}
	// 우선 벽을 3개 고른다 
	// 부분집합 코드를 응용한다고 생각하면 편하다
	public static int set_wall(int count)
	{
		if (count == 3)
		{
			find();
			return 1;
		}
		else
		{
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < m; j++)
				{
					if (arr[i][j] == 0)
					{
						// 내가 고른 벽은 3이라고 지칭 
						arr[i][j] = 3;
						set_wall(count + 1);
						// 다시 원상복구 시켜줘야 모든 경우 탐색 가능
						arr[i][j] = 0;
					}
				}
			}
		}
		return 0;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		for (int i=0; i<n; i++)
		{
			for (int j=0; j <m; j++)
			{
				arr[i][j] = scan.nextInt();
				arr_copy[i][j] = arr[i][j];
			}
		}
		//print();
		set_wall(0);
		System.out.println(ans);
		return;
	}	
}