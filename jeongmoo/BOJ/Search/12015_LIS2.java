import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 12015번. 가장 긴 증가하는 부분 수열 2
public class BOJ12015_LIS2 {
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
    	int size = 0;
    	lis[size++] = data[0];
    	for (int i = 1; i < n; i++) {    		
    		int start = 0;
    		int end = size;
    		while(start<end) {
    			int mid = (start+end)/2;
    			if (lis[mid] < data[i])
    				start = mid+1;
    			else
    				end = mid;
    		}
    		lis[end] = data[i];
    		if (size <= end)
    			size++;
		}
    	System.out.println(size);
    }
}
