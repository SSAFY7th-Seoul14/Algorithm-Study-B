import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2559번. 수열
public class BOJ2559_Sequence {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	
    	int[] data = new int[n];
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < n; i++) {
    		data[i] = Integer.parseInt(st.nextToken());
		}
    	
    	// 계산
    	int sum = 0;
    	for (int i = 0; i < k; i++) {
			sum += data[i];
		}
    	int max = sum;
    	
    	for (int i = k; i < n; i++) {
			sum += data[i];
			sum -= data[i-k];
			max = Math.max(max, sum);
		}
    	System.out.println(max);
    }
}
