import java.io.*;
import java.util.*;


public class Main {
	
	static int n, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		String s1 = scan.next();
		String s2 = scan.next();
		char c;
		while (s1.length() != s2.length())
		{
			n = s2.length() - 1;
			c = s2.charAt(n);
			s2 = s2.substring(0, n);
			if (c == 'B')
			{
				StringBuffer sb = new StringBuffer(s2);
				s2 = sb.reverse().toString();
			}
			//System.out.println(s2);
		}
		if (s1.equals(s2))
			ans = 1;
		System.out.println(ans);
	}
}