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
		boolean flag = true;
		int arr[] = new int[n+1];
		for (int i = 1; i <= n; i++)
			arr[i] = Integer.parseInt(br.readLine());
		int i = 1, index = 1;
		StringBuilder v = new StringBuilder();
		Stack <Integer> s = new Stack<Integer>();
		while(index < n+1)
		{
			if (!s.empty() && arr[index] == s.peek())
			{
				v.append('-').append("\n");
				s.pop();
				index++;
				continue;
			}
			else if (s.empty()||(arr[index] > s.peek() && index <= n))
			{
				s.push(i);
				v.append('+').append("\n");
				i++;
				continue;
			}
			else if (arr[index] < s.peek())
			{
				flag = false;
				break;
			}
		}
		if (flag)
			System.out.println(v);
		else
			System.out.println("NO");
	}
}
