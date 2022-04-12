import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11503번. 가장 긴 증가하는 부분 수열
public class BOJ11503_LIS {
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
    }
}
