import java.io.*;
import java.util.*;


public class Main {
	
	static int n,h, ans, num;
	static int height[] = new int[500001];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		h = scan.nextInt();
		// 종유석과 석순을 따로 저장
		ans = 21000000;
		int m = n/2;
		double up[] = new double[m + 3];
		double down[] = new double[m + 3];
		for (int i=0; i<m+3; i++)
			up[i] = down[i] = Double.MAX_VALUE;
		for (int i=0; i<n; i++)
		{
			if (i % 2 == 1)
				up[i / 2] = scan.nextInt();
			else
				down[i / 2] = scan.nextInt();
		}
		// 이분 탐색을 위해 정렬
		Arrays.sort(up);
		Arrays.sort(down);
		int left,right, mid, cnt_down, cnt_up, result;
		for (int i=1; i<= h; i++)
		{
			left = 0; 
			right = m-1;
			cnt_down = 0;
			cnt_up = 0;
			
			// 위쪽과 아래쪽 각각 이분탐색을 진행해서 몇개씩 걸리는지 체크 
			// double로 한 이유 : int는 가끔 안걸리는게 있음 
			while (left <= right)
			{
				mid = (left + right) / 2;
				double d = i - 0.5;
				if (down[mid] >= d )
				{
					right = mid - 1;
					cnt_down = m - mid;
				}
				else 
					left = mid + 1;
			}
			left = 0;
			right = m - 1;
			while (left <= right) // 여기가 문제 
			{
				mid = (left + right) / 2;
				double d = (h - i) + 0.5;
				if (up[mid] >= d)
				{
					right = mid - 1;
					cnt_up = m - mid;
				}
				else
					left = mid + 1;
			}
			// 합쳐서 가장 작은 값 갱신 
			result = cnt_down + cnt_up;
			if (ans > result)
				ans = result;
			height[i] = result;
		}
		// ans의 개수 세기 
		int count = 0;
		for (int i = 1; i <= h; i++)
		{
			if (height[i] == ans)
				count++;
		}
		System.out.println(ans + " " + count);
	}
}