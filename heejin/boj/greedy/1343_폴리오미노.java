import java.util.*;
import java.io.*;
//BOJ / 폴리오미노 / S5 / 30분
//https://www.acmicpc.net/problem/1343
public class Main_1343 {
	static int N; //input 문자열의 길이
	static char answer[];
	static boolean flag;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		N = input.length();

		answer = new char[N+1];
		
		int cnt=0, start=0;
		flag=true;
		
		for(int i=0;i<N;i++) {
			if(!flag) {
				break;
			}
			char cur = input.charAt(i);
			if(cur=='X')
				cnt++;
			else if(cur=='.') {
				answer[i]='.';
				fillPoly(start,cnt);
				start=i+1;
				cnt=0;
			}
			if(i==N-1) {
				fillPoly(start,cnt);
			}
		}
		if(flag) {
			for(int i=0;i<N;i++)
				System.out.print(answer[i]);
		}
		else
			System.out.println(-1);
		
		
	}
	private static void fillPoly(int startIdx, int cnt) {
		int idx=startIdx;
		while(cnt>0) {
			if(cnt>=4 && cnt%2==0) {
				for(int i=0;i<4;i++)
					answer[idx++]='A';
				cnt-=4;
				continue;
			}
			else if(cnt%2==0) {
				for(int i=0;i<2;i++)
					answer[idx++]='B';
				cnt-=2;
				continue;
			}
			else {
				flag=false;
				return;
			}
		}
	}

}
