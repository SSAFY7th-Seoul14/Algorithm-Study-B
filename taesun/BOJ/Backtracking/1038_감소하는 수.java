import java.io.*;
import java.util.*;

public class Main {
	static long ans, n;
	// 전체 경우 2^10 - 1개
	static long arr[] = new long[1030];

	// 마지막 감소하는 수는 9876543210
	// 내가 임의의 숫자를 뽑았을때 그 숫자들로 만들 수 있는 감소하는 수는 하나라는걸 생각하면 편함
	static boolean bitset(long a, long b) {
		return (a & (1 << b)) > 0;
	}

	static long brute()
	{
		long temp = 0;
		long size = 0;
		Queue<Long> q = new LinkedList<Long>();
		for (long i = 1; i <= 1023; i++)
		{
			for (long j = 0; j <= 9; j++)
			{
				if (bitset(i, j))
					q.add(j);
			}
			size = q.size();
			// 감소하는 수를 만들어줌 
			for (long j = 0; j < size; j++)
			{
				temp += q.peek() * Math.pow(10, j);
				q.poll();
			}
			arr[(int)(i - 1)] = temp;
			temp = 0;
		}
		
		// 다 집어넣고 순서대로 sort
		Arrays.sort(arr, 1, 1023);

		if (n < 1023)
			return arr[(int)n];
		else
			return -1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextLong();
		ans = brute();
		System.out.println(ans);
	}
}