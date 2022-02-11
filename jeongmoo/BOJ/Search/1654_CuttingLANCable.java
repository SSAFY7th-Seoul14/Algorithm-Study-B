import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준1654번. 랜선 자르기
public class BOJ1654_CuttingLANCable {	
	static int k, n;
	static int arr[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		arr = new int[k];
		for (int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		// 처리
		long begin=1;   				// begin이 0이면 0,1일때 mid가 0이 될수 있음
		long end=arr[k-1];        
		long max = 0;
        while(begin<=end) {
        	long mid = (begin+end)/2;	// 여기서 int범위를 넘어갈 수 있음!! -> long 사용
        	
        	// 계산
        	int total = 0;
        	for (int i = 0; i < k; i++) {
				total += arr[i]/mid;
			}
        	if(n<=total) { // 토막 개수가 같거나 더 많으면
        		begin = mid+1; 	// 길이를 늘려서 개수를 줄여야함
        		if (max < mid) { // 이중에서 mid값 최대를 구해야함.
        			max = mid;
        		}
        	} else {
        		end = mid-1;	// 길이를 줄여서 개수를 늘려야함
        	}
        }
        
		System.out.print(max);
	}
}