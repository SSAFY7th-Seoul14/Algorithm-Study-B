/*
 * [브루트포스]
 * 1차 - 0부터 9876543210까지 탐색하면 시간초과 난다는 거 인지함 .
 * 그래서 10자리의 숫자를 저장하는 char 배열을 저장하여 N번재 최소수 찾으려 하나 시간초과
 * 2차 - 인터넷에서 힌트 봄. 1일 땐 1, 10 2일떈 2, 20, 21, 210
 *  3일땐 3, 30, 31, 32, 310, 320, 321... 이런식으로 첫번째 숫자마다 가능한 수 다름
 *  그래서 첫 숫자가 num이면 num%10까지 가능함. 그만큼 list에 추가가 끝나면 다음 숫자로 가기 위해
 *  
 */

import java.io.*;
import java.util.*;
// BOJ / 감소하는 수 / G5 / 80분
//https://www.acmicpc.net/problem/1038
public class Main_1038 {
	static int N;
	static List<Long> list;
	static int cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<Long>();
		if(N<=10)
			System.out.println(N);
		else if(N>1022)
			System.out.println(-1);
		else {
			for(int i=0;i<10;i++)
				findNum(i,1); //i:시작 수, 1: 숫자 자릿수
			Collections.sort(list);
			System.out.println(list.get(N));
		}
		
	}
	// num=3
	// 0~9
	// 10
	// 20 21
	// 30 31 310 32 320 321 3210...
	private static void findNum(long num, int idx) {
		if(idx>10)
			return;
		list.add(num);
		for(int i=0;i<num%10;i++) {
			findNum((num*10)+i,idx+1);
		}
	}
}