import java.io.*;
import java.util.*;


/* 
 * b를 2^a + 2^ b + 2^ c + .... 꼴로 바꿔서 지수를 vector 저장
      지수만큼 행렬 제곱해서 나온 결과를 차례로 단위 행렬에 더해나가면 답
*/
public class Main {
	static long n, b;
	static long matrix[][] = new long[5][5];
	static long arr[][] = new long[5][5];
	static long ans[][] = new long[5][5];
	// 입력값을 분해한 지수를 저장하기 위한 배열 
	static ArrayList<Long> pw = new ArrayList<Long>();
	// 배열 초기화용 
	static void clear()
	{
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
				arr[i][j] = matrix[i][j];
		}
		return;
	}
	static void print()
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
		System.out.println();
		return;
	}
	// 행렬곱 
	static long multiple(int x, int y)
	{
		long temp = 0;
		for (int i = 0; i < n; i++)
			temp = (temp + (arr[x][i] * arr[i][y])) % 1000;
		return temp;
	}
	static void run(long target)
	{
		int cnt = 0;
		long arr2[][] = new long[5][5];
		while (cnt != target)  //arr을 계속 제곱 
		{
			cnt++;
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
					arr2[i][j] = multiple(i, j);
			}
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
					arr[i][j] = arr2[i][j];
			}
		}
		//print();
		// 이러고 ans와 arr을 곱 
		long arr3[][] = new long[5][5];
		for (int i = 0; i < n; i++)  //행렬 곱셈하면서 값이 변함 -> 방지하기 위해 arr3으로 분리
		{
			for (int j = 0; j < n; j++)
			{
				long temp = 0;
				for (int k = 0; k < n; k++)
					temp = (temp + (ans[i][k] * arr[k][j])) % 1000;
				arr3[i][j] = temp;
			}
		}
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				ans[i][j] = arr3[i][j];
		}
		clear();
		return;
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextLong();
		b = scan.nextLong();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				matrix[i][j] = scan.nextLong();
				arr[i][j] = matrix[i][j];
				if (i == j)
					ans[i][j] = 1;
			}
		}
		// 입력값 b를 2의 지수승으로 분해 
		int p = 36;
		while (b > 1)
		{
			long temp = (long) Math.pow(2, p);
			if (b / temp > 0)
			{
				b -= temp;
				pw.add((long) p);
			}
			p--;
		}
		if (b == 1)
			pw.add((long) 0);
		for (int i = 0; i < pw.size(); i++)
			run(pw.get(i));
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				System.out.print(ans[i][j] + " ");
			System.out.println();
		}
	}
}