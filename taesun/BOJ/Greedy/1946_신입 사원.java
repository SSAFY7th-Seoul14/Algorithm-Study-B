import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
	int x,y;
	pair(int x, int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(pair o) {
		if (this.x != o.x)
			return this.x - o.x;
		else
			return this.y - o.y;
	}
	
}
// 백준 
// 그리디 + 정렬 문제
public class Main {
	
	static int n,m, ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		int a, b;
		for (int i=0; i<t; i++)
		{
			n = scan.nextInt();
			ans = n;
			ArrayList<pair> arr = new ArrayList<pair>();
			for (int j=0; j<n; j++)
			{
				a = scan.nextInt();
				b = scan.nextInt();
				arr.add(new pair(a,b));
			}
			Collections.sort(arr);
			a = arr.get(0).x;
			b = arr.get(0).y;
			for (int j=1; j<n; j++)
			{
				if (arr.get(j).x > a && arr.get(j).y > b)
					ans--;
				else
				{
					a = arr.get(j).x;
					b = arr.get(j).y;
				}
			}
			System.out.println(ans);
		}
	}
}
