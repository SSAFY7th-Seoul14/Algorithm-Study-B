import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2352번. 반도체 설계
public class BOJ2352_Semiconductor {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] data = new int[n];
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
    	
    	// 계산
    	// 증가 수열
    	int lisSize = getLisSize(n, data);
    	System.out.println(lisSize);
    }
	
	public static int getLisSize(int n, int[] data) {
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
    		if (size == end)
    			size++;
		}
    	return size;
	}
}
