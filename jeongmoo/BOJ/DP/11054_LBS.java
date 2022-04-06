import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11054번. 가장 긴 바이토닉 부분 수열
public class BOJ11054_LBS {
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
    	// 증가 수열
    	for (int i = 0; i < n; i++) {
    		lis[i] = 1;
    		for (int j = 0; j < i; j++) {
    			// 증가 수열 중 합이 더 커야함
				if (data[j] < data[i] && lis[i] < lis[j]+1)
					lis[i] = lis[j]+1;
			}
		}

    	// 감소 수열
    	int max = Integer.MIN_VALUE;
    	int[] lds = new int[n];
    	for (int i = n-1; i >= 0; i--) {
    		lds[i] = 1;
    		for (int j = n-1; j > i; j--) {
    			// 역으로 증가수열 찾는다.
				if (data[j] < data[i] && lds[i] < lds[j]+1)
					lds[i] = lds[j]+1;
			}
    		max = Math.max(max, lis[i]+lds[i]-1);
		}
    	System.out.println(max);
    }
}
