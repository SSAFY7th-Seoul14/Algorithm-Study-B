import java.io.*;
import java.util.*;


public class Main {
	
	static int n;
	static String ans;
	static void func(int d, int pos, int len)
	{
		int k = (len - (d + 3)) / 2;
		if (d == 0 || len == 3)
		{
			if (pos == 1)
				ans = "m";
			else
				ans = "o";
			return;
		}
		if (pos <= k)
		{
			func(d - 1, pos, k);
			return;
		}
		else if (pos > len - k)
		{
			func(d - 1, pos - (len - k), k);
			return;
		}
		else
		{
			if (pos == k + 1)
				ans = "m";
			else
				ans = "o";
			return;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		int idx = 0;
		int temp = 3;
		while (temp < n)
		{
			idx++;
			temp = temp * 2 + idx + 3;
		}
		func(idx, n, temp);
		System.out.println(ans);
	}
}
