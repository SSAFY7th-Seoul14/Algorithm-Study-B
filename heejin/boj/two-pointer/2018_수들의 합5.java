import java.io.*;

// BOJ / 수들의 합5 / S5 / 15분
//https://www.acmicpc.net/problem/2018
public class Main_2018_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
	
		int start=0, end=0; //투포인터 설정
		int sum=0, cnt=0; //sum: 합, cnt: 가지수(정답)
		while(start<=N) {
			while(++end<=N) { //end 증가
				sum += end; //부분합을 증가
				if(sum>=N) {
					if(sum==N) cnt++;
					break;
				}
			}
			while(++start<=N) { //start 증가
				sum -= start; //부분합을 감소
				if(sum<=N) {
					if(sum==N) cnt++;
					break;
				}
			}	
		}
		System.out.println(cnt);
	}
}
