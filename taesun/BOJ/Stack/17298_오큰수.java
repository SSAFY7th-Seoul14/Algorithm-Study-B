import java.io.*;
import java.util.*;


public class Main {
	
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int arr[] = new int[n];
		int ans[] = new int[n];
		boolean flag = false;
		String str[] = br.readLine().split(" ");
		for (int i=0; i<n; i++)
			arr[i] = Integer.parseInt(str[i]);
		Stack <Integer> s = new Stack<Integer>();
		StringBuilder st = new StringBuilder();
		for (int i= n-1; i>=0; i--)
		{
			flag = false;
			if (s.empty())
			{
				s.push(arr[i]);
				ans[i] = -1;
				continue;
			}
			while (!s.empty())
			{
				if (s.peek() > arr[i])
				{
					ans[i] = s.peek();
					s.push(arr[i]);
					flag = true;
					break;
				}
				s.pop();
			}
			if (flag)
				continue;
			if (s.empty())
			{
				s.push(arr[i]);
				ans[i] = -1;
			}
		}
		
		for (int i=0; i<n; i++)
			st.append(ans[i]).append(" ");
		System.out.println(st);
		return;
	}	
}
