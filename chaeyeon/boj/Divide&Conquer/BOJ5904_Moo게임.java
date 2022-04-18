package divideAndConquer;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ5904_Moo게임 {
	
	static char result;
	static ArrayList<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int N = s.nextInt();

		int k = 0;
		int size = 0;
		while(true) {
			size = size*2+(k+3);
			list.add(size);
			if(size >= N) break;
			k++;
		}
		calc(k, N);
		System.out.println(result);

	}
	private static void calc(int k, int N) {
		
		if(k==0) {
			result = N==1 ? 'm' : 'o';
			return;
		}
		
		if(list.get(k-1) < N && list.get(k)-list.get(k-1)>=N) {
			result = list.get(k-1)+1 == N ? 'm' : 'o';
			return;
		}
		
		else if(list.get(k-1) >= N) calc(k-1, N);
		
		else calc(k-1, N-(list.get(k)-list.get(k-1)));
		
	}

}
