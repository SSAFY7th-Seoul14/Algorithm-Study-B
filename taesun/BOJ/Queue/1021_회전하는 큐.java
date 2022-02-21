import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static LinkedList<Integer> q = new LinkedList<Integer>();
	static int ans = 0;
	static void func1()
	{
		q.removeFirst();
		return;
	}
	static void func2()
	{
		int temp = q.getFirst();
		q.removeFirst();
		q.addLast(temp);
		ans++;
		return;
	}
	static void func3()
	{
		int temp = q.getLast();
		q.removeLast();
		q.addFirst(temp);
		ans++;
		return;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
 
		int n = scan.nextInt();
		int m = scan.nextInt();
		int temp, target, locate = 0;
		for (int i = 0; i < n; i++)
		{
			q.addLast(i + 1);
		}
		for (int i=0; i < m; i++)
		{
			target = scan.nextInt();
			for (int j=0; j<q.size(); j++)
			{
				if (q.get(j) == target)
					locate = j;
			}
			
			if (locate <= q.size() / 2)
			{
				while (q.getFirst() != target)
					func2();
				func1();
			}
			else if (locate > q.size() / 2)
			{
				while (q.getFirst() != target)
					func3();
				func1();
			}
		}
		System.out.println(ans);
	}
}
