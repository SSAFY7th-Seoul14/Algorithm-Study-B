import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int arr[][] = new int[100][100];
	static StringBuilder ans = new StringBuilder();
	public static void divide(int size, int x1, int x2, int y1, int y2)
	{
		// 1칸짜리면 더이상 압축할 필요 x
		if (size == 1)
		{
			if (arr[x1][y1] == 1)
				ans.append(1);
			else
				ans.append(0);
			return;
		}
		// 이중 for 돌면서 전부 같은 숫자인지 아닌지 체크
		// 왼쪽 상단에 있는 숫자를 기준으로 삼는다
		int num = arr[x1][y1];
		for (int i = x1; i < x2; i++)
		{
			for (int j = y1; j < y2; j++)
			{
				if (arr[i][j] != num) 
				{
					ans.append("(");
					// 항상 4개의 구간으로 나눠서 진행한다
					divide(size / 2, x1 , x1 + size/2 , y1 , y1+ size/2 );  // 왼쪽 상단
					divide(size / 2, x1, x1 + size / 2, y1 + size / 2, y2);
					divide(size / 2, x1 + size / 2 , x2 , y1, y1 + size / 2 ); // 오른쪽 상단
					divide(size / 2, x1 + size / 2, x2 , y1 + size / 2, y2);
					ans.append(")");
					return;
				}
			}
		}
		// 전부다 같은 숫자일때는 괄호가 필요없다
		if (num == 1)
			ans.append(1);
		else
			ans.append(0);
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		String s = scan.nextLine();
		for (int i=0; i<n; i++)
		{
			s = scan.nextLine();
			for (int j=0; j<s.length(); j++)
				arr[i][j] = s.charAt(j) - '0';
		}
		divide(n,0,n,0,n);
		System.out.println(ans);
		return;
	}	
}
