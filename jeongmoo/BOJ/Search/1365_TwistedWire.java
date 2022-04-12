import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1365번. 꼬인 전기줄
public class BOJ1365_TwistedWire {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] data = new int[n];
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
    	
    	// 계산
    	int lisSize = getLisSize(n, data);
    	System.out.println(n-lisSize);
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
