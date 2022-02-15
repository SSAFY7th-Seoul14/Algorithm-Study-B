import java.io.*;
import java.util.*;


public class Main {
	
	static int m, v;
	
	static void add(int x)
	{
		v |= (1 << x);
	}
	static void remove(int x)
	{
		v &= ~(1 << x);
	}
	
	static boolean check(int x)
	{
		if ((v & (1 << x)) > 0)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		m = Integer.parseInt(br.readLine());
		StringBuilder st = new StringBuilder();
		int t;
		for (int i=0; i<m; i++)
		{
			String s[] = br.readLine().split(" ");
			switch (s[0]) {
				case "add":
					t = Integer.parseInt(s[1]);
					add(t);
					break;
				case "remove":
					t = Integer.parseInt(s[1]);
					remove(t);
					break;
				case "check":
					t = Integer.parseInt(s[1]);
					if (check(t))
						st.append("1\n");
					else
						st.append("0\n");
					break;
				case "toggle":
					t = Integer.parseInt(s[1]);
					if (check(t))
						remove(t);
					else
						add(t);
					break;
				case "all":
					v = v | ((1 << 21) - 1);
					break;
				case "empty":
					v = v & 0;
					break;
				default:
					break;
			}
		}
		System.out.print(st);
		return;
	}	
}
