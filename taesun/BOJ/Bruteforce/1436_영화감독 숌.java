import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int ans = 0;
		int n = scan.nextInt();
		int count = 0;
		String temp;
		for (int i = 666; i < 50000000; i++)
		{
			temp = Integer.toString(i);
			if (temp.contains("666"))
			{
				count++;
				if (count == n)
				{
					ans = i;
					break;
				}
			}
		}
		System.out.println(ans);
	}
}
