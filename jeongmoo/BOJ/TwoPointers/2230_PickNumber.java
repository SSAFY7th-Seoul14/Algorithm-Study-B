import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2230번. 수 고르기
public class BOJ2230_PickNumber {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] data = new int[n];
        
        for (int i = 0; i < n; i++) {
        	data[i] = Integer.parseInt(br.readLine());
		}
        Arrays.sort(data);
        
        // 투포인터
        int min = Integer.MAX_VALUE;
        int end = 0;
        for (int start = 0; start < n; start++) {
        	while(data[end] - data[start] < m && end < n-1)
        		end++;
        	
        	if (m <= data[end] - data[start])
        		min = Math.min(min, data[end] - data[start]);
		}
        System.out.println(min);
    }
}
