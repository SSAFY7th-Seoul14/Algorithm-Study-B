import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888_연산자끼워넣기 {
	
	static int[] input;
	static int[] oper;
	
	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine().trim());
		
		input = new int[N];
		oper = new int[N-1];
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int cnt=0;
		for(int i = 0; i < 4; i++) {
			int op = Integer.parseInt(st.nextToken());
			for(int j = 0; j < op; j++)
				oper[cnt++] = i;
		}
		
		do {
			int result = input[0];

			for(int i = 0; i < oper.length; i++) {
				switch(oper[i]) {
					case 0:
						result += input[i+1];
						break;
					case 1:
						result -= input[i+1];
						break;
					case 2:
						result *= input[i+1];
						break;
					case 3:
						result /= input[i+1];
						break;
						
				}
			}
			min = Math.min(result, min);
			max = Math.max(result, max);
		}while(np(N-1));
		
		System.out.println(max);
		System.out.println(min);
		
		
	}

	private static boolean np(int L) {
		int i = L-1;
		while(i > 0 && oper[i-1] >= oper[i]) --i;
		
		if(i == 0) return false;
		
		int j = L-1;
		while(oper[i-1] >= oper[j]) --j;
		
		swap(i-1, j);
		
		int k = L-1;
		while(i < k) {
			swap(i++,k--);
		}
		
		return true;
		
	}
	
	public static void swap(int x, int y) {
		int tmp = oper[x];
		oper[x] = oper[y];
		oper[y] = tmp;
	}

}
