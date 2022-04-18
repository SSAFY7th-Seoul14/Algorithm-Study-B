import java.io.*;
import java.util.*;


public class Main {

	static int n, m, k, ans, d;
	static int a[][] = new int[51][51];
	static int b[][] = new int[51][51];
	
	static void print()
	{
		System.out.println();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void change(int x, int y)
	{
		//System.out.println(x +" " + y);
		for (int i = x; i < x+3; i++)
		{
			for (int j = y; j < y + 3; j++)
			{
				if (a[i][j] == 1)
					a[i][j] = 0;
				else
					a[i][j] = 1;
			}
		}
		ans++;
	}
	
	// 두 개의 행렬이 일치하는지 체크
	static boolean check()
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				if (a[i][j] != b[i][j])
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		String s;
		for (int i=0; i<n; i++)
		{
			s = scan.next();
			for (int j=0; j<m; j++)
				a[i][j] = s.charAt(j) - '0';
		}
		for (int i=0; i<n; i++)
		{
			s = scan.next();
			for (int j=0; j<m; j++)
				b[i][j] = s.charAt(j) - '0';
		}
		// 제일 맨 앞 숫자부터 일치하는지 체크 -> 일치하지 않으면 변환 + 1
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<m; j++)
			{
				if (a[i][j] == b[i][j])
					continue;
				else
				{
					if (i + 3 <= n && j + 3 <= m)
						change(i, j);
				}
				//print();
			}
		}
		// 일치 여부 확인
		if (!check())
			ans = -1;
		System.out.println(ans);
	}
}
