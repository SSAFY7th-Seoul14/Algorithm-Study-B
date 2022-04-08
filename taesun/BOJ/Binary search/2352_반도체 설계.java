import java.io.*;
import java.util.*;


public class Main {
	
	static int n, ans;
	static int arr[] = new int[40001];
	static ArrayList<Integer> v = new ArrayList<Integer>();
	// n log n lis
	
	// lower bound 구현 실수가 있었음, 확실하게 이해하자 
	static int lower_bound(int value)
	{
		int left = 0,right = v.size() - 1,mid = 0;
		while (left < right)
		{
			mid = (left + right) / 2;
			if (v.get(mid) < value)
				left = mid + 1;
			else
				right = mid;
		}
		return right;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		for (int i=1; i<=n; i++)
			arr[i] = scan.nextInt();
		
		// 이분 탐색을 이용해서 항상 최적의 자리를 찾아 증가 수열을 만든다
		// add를 편하게 하기 위해 미리 나올 수 없는 음수를 넣어둠 
		v.add(arr[1]);
		for (int i=2; i<=n; i++)
		{
			// 현재 증가 수열 중 젤 큰수보다 내가 더 크면 마지막에 넣어준다 
			if (v.get(v.size() - 1) < arr[i])
				v.add(arr[i]);
			// 만약 마지막 수보다 작다면 lower bound를 사용해서 가장 맞는 자리랑 교체 
			else
			{
				int idx = lower_bound(arr[i]);
				
				v.set(idx, arr[i]);
			}
		}
		System.out.println(v.size());
	}
}
