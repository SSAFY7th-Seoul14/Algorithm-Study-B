import java.io.*;
import java.util.*;

// BOJ / 수 찾기 / S4
// https://www.acmicpc.net/problem/1920
public class Main_1920 {
	static int N,M;
	static int nums[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Arrays.sort(nums);
		for(int i=0;i<M;i++) {
			int num = Integer.parseInt(st.nextToken());
			System.out.println(isIn(num));
		}
	}
	private static int isIn(int num) {
		int start = 0;
		int end=N-1;
		while(start<=end) {
			int mid = (start+end)/2;
			if(nums[mid]==num)
				return 1;
			else if(nums[mid]<num) { //숫자가 중간 기준 오른쪽에 있음
				start = mid+1;
			}
			else if(nums[mid]>=num) { //숫자가 중간 기준 왼쪽에 있음
				end = mid-1;
			}
		}
		return 0;
	}

}
