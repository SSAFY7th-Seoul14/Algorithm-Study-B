import java.io.*;
import java.util.*;

public class Main {

	static int n, m, ans, h;
	static int arr[][] = new int[11][40];

	// i번째에서 i 결과가 나오는지 확인하는 함수 
	static boolean test() {
		int x, y, next;

		for (int i = 1; i <= n; i++) {
			x = i;
			for (int j = 1; j <= h; j++) {
				if (arr[x][j] != 0)
					x++;
				else if (arr[x - 1][j] != 0)
					x--;
			}
			if (x != i)
				return false;
		}
		return true;
	}

	// 모든 경우를 보면서 백트래킹
	static void select(int c, int t)
	{
		if (c == t)
		{
			if (test())
				ans = t;
			return;
		}
		for (int i = 1; i < n; i++)
		{
			for (int j = 1; j <= h; j++)
			{
				// 내 왼쪽 오른쪽 모두 비교해서 사다리가 있으면 pass 
				if ((arr[i - 1][j] > 0 || arr[i][j] > 0) || arr[i + 1][j] > 0)
					continue;
				// 사다리 설치하고 다음으로 
				arr[i][j] = 1;
				select(c + 1, t);
				arr[i][j] = 0;
				
				// 이미 양쪽에 사다리면 넘어가가ㅣ 
				while (j < h) 
				{
					if (arr[i-1][j] != 0 || arr[i+1][j] != 0)
						break;
					j++;
				}
			}
		}
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		h = scan.nextInt();
		ans = -1;
		int a,b;
		
		for (int i = 0; i < m; i++)
		{
			a = scan.nextInt();
			b = scan.nextInt();
			arr[b][a] = b + 1;
		}

		if (test())
		{
			System.out.println(0);
			return;
		}
		for (int i = 1; i <= 3; i++)
		{
			select(0, i);
			if (ans != -1)
			{
				System.out.println(ans);
				return;
			}
		}
		System.out.println(ans);
	}
}
