import java.io.*;
import java.util.*;

// 반례   bc가 1개인 경우에 값이 이상하게 나옴 
public class Main {
	static int n, ans;
	static int arr[][] = new int[501][501];
	static int h, w;
	
	static void count_rain()
	{
		int temp, wall;
		for (int i = 0; i < h; i++)
		{
			temp = wall = 0;
			//양쪽 맨 끝 블럭이 빈 공간이면 어차피 물이 고일 수 없음
			// 가로로 돌면서 물이 고일 수 있는 부분인지 체크 
			for (int j = 1; j < w-1; j++)
			{
				if (arr[i][j] == 0 && arr[i][j - 1] == 1)
				{
					wall = 1;
					arr[i][j] = 2;
					continue;
				}
				if (wall == 1 && arr[i][j] == 0)
				{
					arr[i][j] = 2;
					if (arr[i][j + 1] == 1)
						wall = 0;
				}
			}
			// 예외처리 부분 -> 맨 끝 블록이 비어있는 경우 
			if (wall == 1)
			{
				for (int j = w - 1; j >= 0; j--)
				{
					if (arr[i][j] != 1)
						arr[i][j] = 0;
					else 
						break;
				}
			}
		}
		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < w; j++)
			{
				if (arr[i][j] == 2)
					ans++;
			}
		}
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		h = scan.nextInt();
		w = scan.nextInt();
		int input;
		for (int i = 0; i < w; i++)
		{
			input = scan.nextInt();
			for (int j = 0; j < input; j++)
				arr[h - 1 - j][i] = 1;
		}
		count_rain();
		System.out.println(ans);
		return;
	}	
}