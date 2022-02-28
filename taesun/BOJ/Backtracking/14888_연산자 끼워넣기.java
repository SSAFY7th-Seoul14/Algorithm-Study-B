import java.io.*;
import java.util.*;

public class Main {
	static int ans_min = Integer.MAX_VALUE, ans_max = Integer.MIN_VALUE, n;
	static int num[] = new int[11];
	static int op[] = new int[4];
	
	// 브루트포스 + 백트래킹 문제 
	static void dfs(int idx, int value)
	{
		if (idx == n)
		{
			ans_max = Math.max(ans_max, value);
			ans_min = Math.min(ans_min, value);
			return;
		}
		else
		{
			for (int i = 0; i < 4; i++)
			{
				// 사용할 연산자 개수가 없으면 pass
				if (op[i] == 0) 
					continue;
				// 연산자 썼으면 개수 감소
				op[i]--;  
				if (i == 0)
					dfs(idx + 1, value + num[idx]);
				else if (i == 1)
					dfs(idx + 1, value - num[idx]);
				else if (i == 2)
					dfs(idx + 1, value * num[idx]);
				else
					dfs(idx + 1 , value / num[idx]);
				// 다시 원상태로 복구하고 이어서 탐색
				op[i]++; 
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		for (int i=0; i<n; i++)
			num[i] = scan.nextInt();
		for (int i=0; i<4; i++)
			op[i] = scan.nextInt();
		// 편의상 이미 첫번째 숫자는 식에 들어가있다고 가정 
		dfs(1, num[0]);
		System.out.println(ans_max);
		System.out.println(ans_min);
	}	
}
