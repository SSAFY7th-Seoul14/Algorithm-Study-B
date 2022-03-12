import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
	static int n, ans;
	static int arr[][] = new int[100003][2];
    // n번째까지 왔을때 최고점을 저장 
	static int cache[][] = new int[100003][2];
	static int dp(int x, int y)
	{
        // 인덱스를 벗어나거나 이미 값을 갱신한 경우는 바로 리턴 
		if (x < 0)
			return 0;
		if (cache[x][y] != -1)
			return cache[x][y];
		int result = 0;
		// 나랑 인접한 스티커는 포함 x , n-1 단계에선 1개만 볼 수 있음 
		// 나보다 2단계 전에선 둘다 선택 가능 , 둘다 조회해보기 
		int t = y == 1 ? 0 : 1;
		result = Math.max(dp(x - 1, t), Math.max(dp(x - 2, 0), dp(x - 2, 1)));
		return cache[x][y] = result + arr[x][y];
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int t=0; t<test; t++)
		{
			ans = 0;
			n = scan.nextInt();
			for (int i=0; i<100003; i++)
			{
				cache[i][0] = cache[i][1] = -1;
				arr[i][0] = arr[i][1] = 0;
			}
			for (int i=0; i<n; i++)
				arr[i][0] = scan.nextInt();
			for (int i=0; i<n; i++)
				arr[i][1] = scan.nextInt();
			cache[0][0] = arr[0][0];
			cache[0][1] = arr[0][1];
            // 2가지 경우 중 더 큰 값을 찾아서 정답으로 갱신 
			ans = Math.max(dp(n - 1, 0), dp(n - 1, 1));
			System.out.println(ans);
		}
	}
}
