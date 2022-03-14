import java.io.*;
import java.util.*;
//BOJ / 순열장난 / S1 / 80분
//https://www.acmicpc.net/problem/10597
public class Main_10597 {
	static boolean visited[];
	static List<Integer> result;
	static int len;
	static String input;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		len = input.length();
		if(len<10)
			N=len;
		else
			N = (len-9)/2+9;
		visited = new boolean[N+1]; //1~29 숫자 가능
		result = new ArrayList<Integer>();
	
		recovery(0);
	}
	private static void recovery(int idx) {
		if(idx>= len) {
			//숫자 오름차순으로 다 존재하는지 확인
			for(int i=1;i<=N;i++) {
				if(!visited[i])
					return;
			}
			for(int i=0;i<result.size();i++)
				System.out.print(result.get(i)+" ");
			System.exit(0);
			return;
		}
		
		String numstr1 = input.substring(idx, idx+1); //한자리 숫자
		int num1 = Integer.parseInt(numstr1);
		if(num1!=0 && !visited[num1]) {
			
			visited[num1]=true;
			result.add(num1);
			recovery(idx+1);
			result.remove(Integer.valueOf(num1));
			visited[num1]=false;
		}
		if(idx+1<len){
			String numstr2 = input.substring(idx, idx+2); // 두자리 숫자
			int num2=Integer.parseInt(numstr2);
			if(num2<=N && !visited[num2]) {
				visited[num2]=true;
				result.add(num2);
				recovery(idx+2);
				result.remove(Integer.valueOf(num2));
				visited[num2]=false;
			}
		}
		
	}
}
