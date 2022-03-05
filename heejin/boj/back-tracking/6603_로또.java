import java.io.*;
import java.util.*;

// BOJ / 로또 / S2 / 10분
//https://www.acmicpc.net/problem/6603
public class Main_6603 {
	static int k;
	static int nums[];
	static int input[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if(k==0) break; 
			
			nums = new int[6]; //순열 값 저장
			input = new int[k]; // input 집합 S 저장
			
			//집합 S 입력받기
			for(int i=0;i<k;i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
			comb(0,0); //K개의 숫자에서 6개 고르기 시작
			System.out.println();
			
		}
	}
	private static void comb(int idx, int start) {
		if(idx==6) {
			for(int num: nums)
				System.out.print(num+" ");
			System.out.println();
			return;
		}
		for(int i=start;i<k;i++) {
			nums[idx]=input[i];
			comb(idx+1,i+1);
		}
		
	}

}
