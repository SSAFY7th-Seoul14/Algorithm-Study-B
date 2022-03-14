import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.awt.*;

public class Main {
	
	static int arr[][] = new int[5][10];
	static int ans, k;
	
	static void swap(int index , int flag)
	{
		if (flag == -1) // 시계 반대 방향 회전
		{
			int temp = arr[index][0];
			for (int i = 0; i < 7; i++)
				arr[index][i] = arr[index][i + 1];
			arr[index][7] = temp;
		}
		else  //시계 회전 
		{
			int temp = arr[index][7];
			for(int i = 7; i > 0 ; i--)
				arr[index][i] = arr[index][i - 1];
			arr[index][0] = temp;
		}
	}
	static void rotate(int t , int r , int flag) 
	{
		if (t == 0)  // 1번째 톱니바퀴
		{
			if ((arr[t][2] != arr[t + 1][6]) && (flag != 1))
				rotate(t + 1, r * -1 , -1);
		}
		else if (t == 3) // 4번째 톱니바퀴
		{
			if ((arr[t][6] != arr[t - 1][2]) && (flag != -1))
				rotate(t - 1, r * -1 , 1);
		}
		else // 2 ,3번째 톱니바퀴
		{
			if ((arr[t][2] != arr[t + 1][6]) && (flag != 1))  // 오른쪽 판정
				rotate(t + 1, r * -1, -1);
			if ((arr[t][6] != arr[t - 1][2]) && (flag != -1))  // 왼쪽 판정
				rotate(t - 1, r * -1 , 1);
		}
		swap(t , r);  // 나 자신이 회전
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < 4; i++)
		{
			String s = scan.next();
			for (int j = 0; j < 8; j++)
				arr[i][j] = s.charAt(j) - '0';
		}
		k = scan.nextInt();
		int t, r;
		for (int i=0; i<k; i++)
		{
			t = scan.nextInt();
			r = scan.nextInt();
			rotate(t - 1 , r , 0);
		}
		// 다 돌고 점수 계산 
		for (int i = 0; i < 4; i++)
		{
			if (arr[i][0] != 0)
				ans += Math.pow(2,i);
		}
		System.out.println(ans);
	}
}
