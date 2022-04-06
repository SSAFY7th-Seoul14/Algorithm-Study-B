import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 14002번. 가장 긴 증가하는 부분 수열 4
public class BOJ14002_LIS4 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] data = new int[n];
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
    	
    	// 계산
    	int[] lis = new int[n];
    	int size = Integer.MIN_VALUE;
    	for (int i = 0; i < n; i++) {
    		lis[i] = 1;
    		for (int j = 0; j < i; j++) {
				if (data[j] < data[i] && lis[j]+1 > lis[i])
					lis[i] = lis[j]+1;
			}
    		size = Math.max(size, lis[i]);
		}
    	System.out.println(size);
    	
    	// 경로 출력
    	Stack<Integer> s = new Stack<>();
		for (int i = n-1; i >= 0; i--) {
			if (size == lis[i]) { // 경로의 lis 길이
				s.push(data[i]); // 경로의 수열 값
				size--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (!s.isEmpty()) {
			sb.append(s.pop() + " ");
		}
		System.out.println(sb);
    }
}
