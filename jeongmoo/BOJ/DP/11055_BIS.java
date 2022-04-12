import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11055번. 가장 큰 증가하는 부분 수열
public class BOJ11055_BIS {
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
    	int max = Integer.MIN_VALUE;
    	for (int i = 0; i < n; i++) {
    		lis[i] = data[i];
    		for (int j = 0; j < i; j++) {
    			// 증가 수열 중 합이 더 커야함
				if (data[j] < data[i] && lis[i] < lis[j]+data[i])
					lis[i] = lis[j]+data[i];
			}
    		max = Math.max(max, lis[i]);
		}
    	System.out.println(max);
    }
}
