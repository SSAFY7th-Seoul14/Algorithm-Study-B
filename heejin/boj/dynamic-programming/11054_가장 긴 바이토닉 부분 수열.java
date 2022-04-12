import java.io.*;
import java.util.*;

// BOJ / 가장 긴 바이토닉 부분 수열 / G3 / 15분
// https://www.acmicpc.net/problem/11054
public class Main_11054 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int nums[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
			nums[i] = Integer.parseInt(st.nextToken());
		int LIS[] = new int[N]; //가징 긴 증가하는
		int LDS[] = new int[N]; //가장 긴 감소하는
		
		int res = 0; //가장 긴 바이토닉 수열 길이
		for(int i=0;i<N;i++) {
			LIS[i] = 1;
			for(int j=0;j<i;j++) {
				if(nums[i]>nums[j] && LIS[i]<LIS[j]+1) {
					LIS[i] = LIS[j]+1;
				}
			}
		}
		for(int i=N-1;i>=0;i--) {
			LDS[i] = 1;
			for(int j=N-1;j>i;j--) {
				if(nums[i]>nums[j] && LDS[i]<LDS[j]+1) {
					LDS[i] = LDS[j]+1;
				}
			}
		}
		for(int i=0;i<N;i++) {
			res = Math.max(res, LIS[i]+LDS[i]-1);
		}
		System.out.println(res);
		
	}
}
