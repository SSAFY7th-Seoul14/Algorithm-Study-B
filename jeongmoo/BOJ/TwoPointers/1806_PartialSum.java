import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1806번. 부분합
public class BOJ1806_PartialSum {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] data = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	data[i] = Integer.parseInt(st.nextToken());
		}
        
        // 투포인터
        int min = Integer.MAX_VALUE;
        int end = 0;
        int sum = 0;
        for (int start = 0; start < n; start++) {
        	while(sum < s && end < n)
        		sum += data[end++];
        	
        	if (s <= sum)
        		min = Math.min(min, end-start);
        	
        	sum -= data[start];
		}
        
        if (min == Integer.MAX_VALUE)
        	System.out.println("0");
        else
        	System.out.println(min);
    }
}
