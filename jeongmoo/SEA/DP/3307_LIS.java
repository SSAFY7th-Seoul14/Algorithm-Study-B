import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SW Expert Academy 3307번. 최장 증가 부분 수열
public class SWEA3307_LIS {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int tc = 1; tc <= t; tc++) {
        	int n = Integer.parseInt(br.readLine());
        	int[] data = new int[n];
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < n; i++) {
				data[i] = Integer.parseInt(st.nextToken());
			}
        	
        	// 계산
        	int[] lis = new int[n];
        	int size = 0;
        	for (int i = 0; i < n; i++) {
				// 최초에는 넣어준다.
        		if (size == 0) {
					lis[0] = data[i];
					size++;
					continue;
				}
        		
        		int start = 0;
        		int end = size;
        		while(start<end) {
        			int mid = (start+end)/2;
        			if (lis[mid] < data[i]) {
        				start = mid+1;
        			} else if (lis[mid] > data[i]) {
        				end = mid;
        			}
        		}
        		lis[start] = data[i];
        		if (size <= start)
        			size++;
			}
        	
        	System.out.printf("#%d %d\n", tc, size);
        }
    }
}