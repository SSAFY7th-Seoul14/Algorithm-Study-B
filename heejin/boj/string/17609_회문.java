import java.io.*;
import java.util.*;

// BOJ / 회문 / S1 / 1시간 반
// https://www.acmicpc.net/problem/17609
// 로직은 투포인터인거 알고부턴 쉽게 생각해냈는데 구현이 힘들었다. 블로그 참고하여 작성
public class Main_17609 {
	static String input;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			input = br.readLine();
			int start = 0, end = input.length() - 1;
			if(check(start,end)) { //문자를 제거하지 않고 같을 경우
				System.out.println(0);
				continue;
			}else {
				if(check2(start,end)) { //문자를 하나 제거해야 같을 경우
					System.out.println(1);
				}else { //문자 하나 제거해도 같지 않은 경우
					System.out.println(2);
				}
			}
			
		}
	}

	private static boolean check2(int start, int end) {
		while(start<=end) {
			if(input.charAt(start)!=input.charAt(end)) { //다르면
				boolean a = check(start+1, end); //왼쪽 글자 삭제했을 때 결과
				boolean b = check(start, end-1); //오른쪽 글자 삭제했을 때 결과
				if(a==false&& b==false) //왼쪽, 오른쪽 삭제해도 안 될 경우
					return false;	
				else
					return true;
			}
			start++;
			end--;
		}
		return false;
	}

	private static boolean check(int start, int end) {
		while(start<=end) {
			if(input.charAt(start)!=input.charAt(end)) //다르면
				return false;
			start++;
			end--;
		}
		return true; //모두 같으면 true 반환
	}
}
