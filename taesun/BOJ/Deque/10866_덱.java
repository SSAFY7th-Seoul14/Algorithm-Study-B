import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
	static Deque<Integer> q = new ArrayDeque<>();
    
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		StringBuilder sb = new StringBuilder();
 
		int n = Integer.parseInt(br.readLine());
		int temp;
		for (int i=0; i<n; i++)
		{
			String[] s = br.readLine().split(" ");
			if (s[0].equals("push_front"))
			{
				temp = Integer.parseInt(s[1]);
				q.addFirst(temp);
			}
			else if (s[0].equals("push_back"))
			{
				temp = Integer.parseInt(s[1]);
				q.addLast(temp);
			}
			else if (s[0].equals("pop_front"))
			{
				if (q.isEmpty())
					System.out.println("-1");
				else
				{
					System.out.println(q.getFirst());
					q.removeFirst();
				}
			}
			else if (s[0].equals("pop_back"))
			{
				if (q.isEmpty())
					System.out.println("-1");
				else
				{
					System.out.println(q.getLast());
					q.removeLast();
				}
			}
			else if (s[0].equals("size"))
				System.out.println(q.size());
			else if (s[0].equals("empty"))
			{
				if (q.isEmpty())
					System.out.println(1);
				else
					System.out.println(0);
			}
			else if (s[0].equals("front"))
			{
				if (q.isEmpty())
					System.out.println("-1");
				else
					System.out.println(q.getFirst());
			}
			else if (s[0].equals("back"))
			{
				if (q.isEmpty())
					System.out.println("-1");
				else
					System.out.println(q.getLast());
			}

		}
	}
}
