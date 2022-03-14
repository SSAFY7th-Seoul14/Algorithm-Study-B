import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 2579번. 계단 오르기
public class BOJ2579_ClimbingStairs {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(br.readLine());
		}
		
		if (n==1) {
			System.out.println(data[0]);
			return;
		}
		if (n==2) {
			System.out.println(data[0]+data[1]);
			return;
		}
		
		
		int[] dp = new int[n];
		dp[0] = data[0];
		dp[1] = dp[0] + data[1];
		dp[2] = Math.max(data[1] + data[2], data[0] + data[2]);
		// 처음 시작은 1번이 아닌 2번계단부터 시작도 가능하므로 3번 계단(dp[2])의 최대값은 1+3, 2+3 중 큰 값이어야 한다.
		// 이를 예외처리하지 않고 2번부터 반복하게 되면 무조건 1,2를 밟은 경우에서 시작하게 됨.
		
		for (int i = 3; i < n; i++) {
			int one = dp[i-3] + data[i-1] + data[i]; // 3계단전에서 점프하고 연속 2개를 밟거나
			int two = dp[i-2] + data[i];			 // 2칸으로 1개를 밟거나
			
			dp[i] = Math.max(one, two);
		}
		
		System.out.println(dp[n-1]);
	}
}

// 반례모음
/*
6
123
14
3
2
13
4
-> 143

6
60
10
61
14
9
7
-> 142

5
100
100
3
2
1
-> 203

6
1
2
3
4
5
6
->16

3
3
2
1
->4

9
100
100
100
100
100
1
1
100
100
-> 501

1
20
-> 20

2
10
20
-> 30
*/