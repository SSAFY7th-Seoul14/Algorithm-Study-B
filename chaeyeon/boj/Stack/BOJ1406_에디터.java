import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1406_에디터 {

	public static void main(String[] args) throws Exception {
		//stack풀이방법
		//stack2개를 이용하라는 힌트를 얻고 다시 풀어봄
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Character> lstack = new Stack<>();
		Stack<Character> rstack = new Stack<>();
		
		String str = br.readLine(); 

		for(int i = 0; i < str.length(); i++) {
			lstack.push(str.charAt(i));
		}
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
				case "P":
					lstack.push(st.nextToken().charAt(0));
					break;
				case "L":
					if(!lstack.isEmpty()) {
						rstack.push(lstack.pop());
					}
					break;
				case "D":
					if(!rstack.isEmpty()) {
						lstack.push(rstack.pop());
					}
					break;
				case "B":
					if(!lstack.isEmpty()) {
						lstack.pop();
					}
					break;
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(char c : lstack) {
			sb.append(c);
		}
		while(!rstack.isEmpty())
			sb.append(rstack.pop());
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
		
		//LinkedList로 풀었을 때 시간초과
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine(); 
		List<Character> list = new LinkedList<>();
		
		for(int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}
		
		int N = Integer.parseInt(br.readLine());
		
		int cursor = list.size();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
				case "P":
					list.add(cursor, st.nextToken().charAt(0));
					cursor++;
					break;
				case "L":
					if(cursor != 0) {
						cursor--;
					}
					break;
				case "D":
					if(cursor != list.size()) 
						cursor++;
					break;
				case "B":
					if(cursor != 0) {
						list.remove(cursor-1);
						cursor--;
					}
					break;
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(char c : list) {
			sb.append(c);
		}
		bw.write(sb.toString());*/
	}

}
