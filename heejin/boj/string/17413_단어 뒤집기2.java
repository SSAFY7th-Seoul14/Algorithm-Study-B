import java.io.*;
import java.util.*;
// BOJ / 단어 뒤집기2 / S3 / 35분
// https://www.acmicpc.net/problem/17413
public class Main_17413 {
	static int idx=0;
	static Stack<Character> st;
	static String result="";
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		char input2[] = input.toCharArray();
		
		st = new Stack<>();
		
		while(idx<input.length()) {
			if(input2[idx]=='<') { // '<' 만날 경우 '>' 나올 때까지 result에 더하기
				printStack();
				while(input2[idx]!='>')
					result+=input2[idx++];
				result+='>';
				idx++;
			}
			else if(input2[idx]==' ') { // 공백 나오면 여태까지 stack에 쌓인 문자들 result에 더하기
				printStack();
				result+=" ";
				idx++;
			}
			else					//공백과 '<'이 아닌 경우 stack에 문자 쌓기
				st.push(input2[idx++]);
		}
		printStack();
		System.out.println(result);
		
	}
	private static void printStack() { //문자열 뒤집기
		while(!st.isEmpty()) {
			result+=st.pop();
		}

		
	}
}
