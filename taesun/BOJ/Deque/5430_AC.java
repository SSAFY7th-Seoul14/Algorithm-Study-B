import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static LinkedList<String> dq = new LinkedList<String>();
	static int d(boolean flag)
	{
		if (dq.isEmpty())
			return -1;
		if (flag)
			dq.removeFirst();
		else
			dq.removeLast();
		return 0;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		for (int t = 1; t<= test; t++)
		{
			int temp = 0;
			
			boolean flag = true;  
			String s1 = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String s2 = br.readLine();
			String s3 = s2.substring(1, s2.length() - 1);
			StringTokenizer st = new StringTokenizer(s3, ",");
			while (st.hasMoreTokens())
			{
				dq.add(st.nextToken());
				//System.out.println(st.nextToken());
			}
			for (int i = 0; i < s1.length(); i++)
			{
				if (s1.charAt(i) == 'R')
					flag = !flag;
				else if (s1.charAt(i) == 'D')
				    temp = d(flag);
			}
			if (temp == -1)
				System.out.println("error");
			else
			{
				StringBuilder ans = new StringBuilder("[");
				if (flag)
				{
					Iterator<String> it = dq.iterator();
					while (it.hasNext()) 
						ans.append(it.next()).append(","); 
				}
				else
				{
					Iterator<String> it = dq.descendingIterator();
					while (it.hasNext()) 
						ans.append(it.next()).append(","); 
				}
				if (ans.length() >= 2)
					ans.setCharAt(ans.length() - 1, ']');
				else
					ans.append("]");
				System.out.println(ans);
			}
			dq.clear();
		}
	}
}
