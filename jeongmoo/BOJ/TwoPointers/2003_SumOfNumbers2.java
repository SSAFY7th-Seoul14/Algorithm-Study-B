import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2003번. 수들의 합 2
public class BOJ2003_SumOfNumbers2 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] data = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	data[i] = Integer.parseInt(st.nextToken());
		}
        
        int end = 0;
        int sum = 0;
        int count = 0;
        for (int start = 0; start < n; start++) {
        	while(sum < m && end < n)
        		sum += data[end++];
        	
        	if (sum == m)
        		count++;
        	
        	sum -= data[start];
		}
        System.out.println(count);
    }
}
