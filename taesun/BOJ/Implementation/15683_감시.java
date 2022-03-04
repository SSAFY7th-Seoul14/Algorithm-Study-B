import java.io.*;
import java.util.*;
/*
class pair implements Comparable<pair> {
	int first, second;

	public pair(int x, int y) {
		this.first = x;
		this.second = y;
	}

	@Override
	public int compareTo(pair o) {
		return this.second - o.second;
	}
}
*/

public class Main {
	static int n, m, ans = Integer.MAX_VALUE, cnt;
	static int arr[][] = new int[10][10];
	static int direct[] = new int[8];
	// 빔의 조합으로 3개까지만 사용 
	static int cctv[][] = new int [8][3];

	// (x, y)에서 d 방향으로 빔을 쏠때
	static void beam(int d , int x , int y)
	{
		if (d == 1)  // 오른쪽
		{
			for (int i = y; i < m; i++)
			{
				if (arr[x][i] == 6)
					break;
				if (arr[x][i] == 0)
					arr[x][i] = -1;
			}
		}
		else if (d == 2) // 아래
		{
			for (int i = x; i < n; i++)
			{
				if (arr[i][y] == 6)
					break;
				if (arr[i][y] == 0)
					arr[i][y] = -1;
			}
		}
		else if (d == 3) // 왼쪽 
		{
			for (int i = y; i >= 0; i--)
			{
				if (arr[x][i] == 6)
					break;
				if (arr[x][i] == 0)
					arr[x][i] = -1;
			}
		}
		else if (d == 4) // 위 
		{
			for (int i = x; i >=0; i--)
			{
				if (arr[i][y] == 6)
					break;
				if (arr[i][y] == 0)
					arr[i][y] = -1;
			}
		}
	}
	
	static void cctv_on()
	{
		int temp = 0;
		for (int i = 0; i < cnt; i++)
		{
			int x = cctv[i][0];
			int y = cctv[i][1];
			int type = cctv[i][2];

			if (type == 1)
				beam( direct[i] , x, y);
			else if (type == 5)
			{
				beam(1, x, y);
				beam(2, x, y);
				beam(3, x, y);
				beam(4, x, y);
			}
			else if (type == 2)
			{
				if (direct[i] % 2 == 0)
				{
					beam(1, x, y);
					beam(3, x, y);
				}
				else
				{
					beam(2, x, y);
					beam(4, x, y);
				}
			}
			else if (type == 3)
			{
				beam(direct[i], x, y);
				if(direct[i]< 4)
					beam(direct[i]+1, x, y);
				else 
					beam(1, x, y);
			}
			else if (type == 4)
			{
				beam(direct[i], x, y);
				if (direct[i] == 1)
				{
					beam(4, x, y);
					beam(2, x, y);
				}
				else if (direct[i] == 2)
				{
					beam(1, x, y);
					beam(3, x, y);
				}
				else if (direct[i] == 3)
				{
					beam(2, x, y);
					beam(4, x, y);
				}
				else if (direct[i] == 4)
				{
					beam(1, x, y);
					beam(3, x, y);
				}
			}
		}

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				if (arr[i][j] == 0)  // 카운트 
					temp++;
				if (arr[i][j] == -1)  // 다시 초기화
					arr[i][j] = 0;
			}
		}
		ans = Math.min(ans, temp);
		return;
	}

	static void brute(int d)
	{
		if (d == cnt)  // cctv 8개일때 최대 65536
		{
			cctv_on();
			return;
		}
		// 모든 방향을 돌아보면서 완전 탐색 
		direct[d] = 1;
		brute(d + 1);
		direct[d] = 2;
		brute(d + 1);
		direct[d] = 3;
		brute(d + 1);
		direct[d] = 4;
		brute(d + 1);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		for (int i = 0; i < 8; i++)
			direct[i] = -1;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				arr[i][j] = scan.nextInt();
				if (arr[i][j] > 0 && arr[i][j] < 6)
				{
					// cctv의 좌표와 방향 저장
					cctv[cnt][0] = i;
					cctv[cnt][1] = j;
					cctv[cnt][2] = arr[i][j];
					// 시작 방향은 1로 설정
					direct[cnt] = 1;
					// 개수도 미리 카운트 
					cnt++;
				}
			}
		}
		brute(0);
		System.out.println(ans);
	}
}
