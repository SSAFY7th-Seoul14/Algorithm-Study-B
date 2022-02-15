package com;
import java.util.*;
import java.util.Collection;

public class Main {
	
	static int n;
	static int ans;
    static String str;
    static int check()
    {
    	char c;
    	Stack<Character> s = new Stack<Character>();
    	for (int i=0; i<str.length(); i++)
    	{
    		c = str.charAt(i);
    		if (c == '(' || c == '[' || c == '{' || c == '<')
    		{
    			s.add(c);
    			continue;
    		}
    		if (c == ')')
    		{
    			if (s.isEmpty() == false && s.peek() == '(')
    				s.pop();
    			else
    				return 0;
    		}
    		else if (c == ']')
    		{
    			if (s.isEmpty() == false && s.peek() == '[')
    				s.pop();
    			else
    				return 0;
    		}
    		else if (c == '}')
    		{
    			if (s.isEmpty() == false && s.peek() == '{') 
    				s.pop();
    			else
    				return 0;
    		}
    		else if (c == '>')
    		{
    			if (s.isEmpty() == false && s.peek() == '<')
    				s.pop();
    			else
    				return 0;
    		}
    	}
    	if (s.empty())
    		return 1;
    	else
    		return 0;
    }
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T;
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			n = scan.nextInt();
			str = scan.next();
			ans = check();
			System.out.println("#" + test_case + " " + ans);
		}
	}	
	
}
