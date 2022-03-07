import java.io.*;
import java.util.*;

//BOJ / 차이를 최대로 / S2 / 10분
//https://www.acmicpc.net/problem/10819
public class Main_10819 {
	static int res; //연산 결과
	static int N;
	static boolean isSelected[];
	static int nums[], input[]; //nums: 순서 섞은 숫자, input: 입력으로 들어오는 숫자
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		input = new int[N];
		isSelected = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		res = Integer.MIN_VALUE;
		
		permutation(0);
		System.out.println(res);
	}
	private static void permutation(int idx) {
		if(idx==N) {
			//연산 시작
			int sum = 0;
			for(int i=0;i<N-1;i++) {
				sum += Math.abs(nums[i]-nums[i+1]); 
			}
			res = Math.max(res, sum); //결과값 갱신
			return;
		}
		for(int i=0;i<N;i++) {
			if(isSelected[i]) continue;
			nums[idx] = input[i];
			isSelected[i]=true;
			permutation(idx+1);
			isSelected[i]=false;
		}
		
	}

}
