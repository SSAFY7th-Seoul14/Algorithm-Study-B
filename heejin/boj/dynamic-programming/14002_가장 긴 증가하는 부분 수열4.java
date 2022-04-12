import java.io.*;
import java.util.*;

// BOJ / 가장 긴 증가하는 부분 수열 4 / G4 / 30분
// https://www.acmicpc.net/problem/14002
public class Main_14002 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int nums[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		int LIS[] = new int[N];
		List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<N;i++)
			list.add(new ArrayList<Integer>());
		
		int res=0;
		for(int i=0;i<N;i++) {
			LIS[i] = 1;
			for(int j=0;j<i;j++) {
				if(nums[i]>nums[j] && LIS[i]<LIS[j]+1) {
					LIS[i] = LIS[j]+1;
				}
			}
			if(res<LIS[i]) {
				res = LIS[i];
			}
		}
		System.out.println(res); //가장 긴 증가하는 부분 수열의 길이
		
		
		//부분 수열 리스트 역추적
		Stack<Integer> stack = new Stack<>();
		for(int i=N-1;i>=0;i--) {
			if(LIS[i]==res) {
				stack.push(nums[i]);
				res--;
			}
		}
		while(!stack.isEmpty())
			System.out.print(stack.pop()+" ");
		
	}
}
