/*
 * [그리디] - 정렬 데이터에서 최소 뽑기 때문
 * 1. 인접 학생 간의 차이인 diff 배열 생성
 * 2. diff 배열 오름차순 정렬(여기까진 생각해봄)
 * 3. diff 배열 N-K까지 더하기(큰 수 K개를 제외한다.)
 * 		-> K개의 조를 만드려 할 때, 조 나누는 부분이 K-1개 발생하므로 K-1개만큼의 diff 제외해도 됨
 * 		->(N-1)-(K-1)=N-K 해서 N-K까지의 diff를 더하는 것
 */

import java.io.*;
import java.util.*;
// BOJ / 행복 유치원 / G5 / 30분(아이디어 못 떠올림)
// https://www.acmicpc.net/problem/13164
public class Main_13164 {
	static int N,K;
	static int costs[], diffs[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		costs = new int[N];
		diffs = new int[N-1];
		//비용 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) 
			costs[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N-1;i++) 
			diffs[i]=costs[i+1]-costs[i];
		
		Arrays.sort(diffs);
		
		int res=0;
		for(int i=0;i<N-K;i++) {
			res += diffs[i];
		}
		System.out.println(res);

	}
	
}
