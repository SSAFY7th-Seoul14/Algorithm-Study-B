import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static LinkedList<Integer> q = new LinkedList<Integer>();
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
			if (s[0].equals("push"))
			{
				temp = Integer.parseInt(s[1]);
				q.add(temp);
			}
			else if (s[0].equals("pop"))
			{
				if (q.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(q.poll()).append("\n");
			}
			else if (s[0].equals("size"))
				sb.append(q.size()).append("\n");
			else if (s[0].equals("empty"))
			{
				if (q.isEmpty())
					sb.append(1).append("\n");
				else
					sb.append(0).append("\n");
			}
			else if (s[0].equals("front"))
			{
				if (q.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(q.peek()).append("\n");
			}
			else if (s[0].equals("back"))
			{
				if (q.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(q.getLast()).append("\n");
			}

		}
		System.out.println(sb);
	}
}
