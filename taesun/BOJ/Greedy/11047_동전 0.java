import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		int ans = 0;
		int arr[] = new int[n];
		for (int i=0; i<n; i++)
			arr[i] = scan.nextInt();
		for (int i = n-1; i >=0; i--)
		{
			if (k >= arr[i])
			{
				ans += k / arr[i]; 
				k= k % arr[i];
			}
		}
		System.out.println(ans);
	}
}
