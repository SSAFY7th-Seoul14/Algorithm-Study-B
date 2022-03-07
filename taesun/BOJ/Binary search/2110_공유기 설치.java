import java.io.*;
import java.util.*;

public class Main {
	static int n, ans = -1 , c;
	// 2110  시간 복잡도 n logn  =  이진 탐색 * n번 서치 
	// 파라메트릭 서치 , 여기서 이분탐색 대상은 답이 될 수 있는 해의 범위 , 공유기 설치 가능 여부가 분별함수 역할 
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		c = scan.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = scan.nextInt();
		Arrays.sort(arr);
		int left, right, mid; // -> 현재 마지막으로 설치된 위치
		left = 1;
		right = arr[n - 1]- arr[0];   // 가능한 해의 범위 (1 ~ 최 우측 - 최 좌측 좌표)
		while (left <= right)
		{
			int count = 1 , index = 0;  // 시작점에 하나 설치하고 시작했다고 가정 
			mid = (left + right) / 2;  // mid 값은 내가 test 해보고 싶은 정답 (범위는 가장 왼쪽 좌표 ~ 가장 오른쪽 좌표)
			for (int j = 1; j < n; j++)  
			{
				if (arr[j] - arr[index] >= mid)  // 여기가 중요 , mid 보다 간격이 작을경우 mid가 내가 가정한 최인접 거리가 아니게됨 -> mid보다 작으면 설치할 필요 x 
				{
					count++; 
					index = j;  // 위치 갱신 
				}
			}
			if (count >= c) // 공유기가 더 많이 설치 -> 간격이 너무 좁다 -> 늘려야됨
			{
				left = mid + 1;
				if (ans < mid)  // ans는 최대값이어야 하므로 
					ans = mid;
			}
			else   // 공유기가 더 적게 설치 -> 간격이 너무 큼 -> 줄여야됨
				right = mid - 1;
		}
		System.out.println(ans);
	}
}
