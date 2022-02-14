import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static Stack<Character> left = new Stack<Character>();
	static Stack<Character> right = new Stack<Character>();
	
	// 스택을 2개 쓰는게 가장 편한 방법
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringBuilder ans = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		char c;
		for(int i =0 ; i<s.length(); i++)
			left.push(s.charAt(i)); 
		for (int i=0; i<n; i++)
		{
			String[] input = br.readLine().split(" ");
			if (input[0].equals("P"))
			{
				c = input[1].charAt(0);
				left.add(c);
			}
			else if (input[0].equals("D"))
			{
				if (right.empty())
					continue;
				left.add(right.peek());
				right.pop();
			}
			else if (input[0].equals("L"))
			{
				if (left.empty())
					continue;
				right.add(left.peek());
				left.pop();
			}
			else
			{
				if (left.empty())
					continue;
				left.pop();
			}
		}
		while(!left.isEmpty())
			right.push(left.pop());
		
		while(!right.isEmpty())
			ans.append(right.pop());
		System.out.println(ans);
	}
}
