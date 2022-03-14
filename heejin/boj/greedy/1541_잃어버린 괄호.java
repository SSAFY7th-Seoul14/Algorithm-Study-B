import java.io.*;
import java.util.*;

//BOJ / 잃어버린 괄호 / S2 / 40분
//https://www.acmicpc.net/problem/1541
public class Main_1541 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		int res=0; //최종 정답
		boolean flag = true; //처음엔 더해야 함
		StringTokenizer st = new StringTokenizer(input, "-");
		while(st.hasMoreTokens()) {
			int sum=0; //+들의 합
			StringTokenizer st2 = new StringTokenizer(st.nextToken(),"+");
			while(st2.hasMoreElements()) {
				sum += Integer.parseInt(st2.nextToken());
			}
			if(flag) {
				res += sum;
				flag=false;
			}
			else
				res -= sum;
		}
		System.out.println(res);
	}
}
