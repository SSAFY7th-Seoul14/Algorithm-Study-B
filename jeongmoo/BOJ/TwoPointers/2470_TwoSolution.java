import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2470번. 두 용액
public class BOJ2470_TwoSolution {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] data = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	data[i] = Integer.parseInt(st.nextToken());
		}
        Arrays.sort(data);
        
        // 투포인터
        int min = Integer.MAX_VALUE;
        int num1 = 0;
        int num2 = 0;
        
        int start = 0;
        int end = n-1;
        while (start < end) {
        	int sum = data[start] + data[end];
        	if (Math.abs(sum) < min) {
        		min = Math.abs(sum);
        		num1 = data[start];
        		num2 = data[end];
        	}
        	
        	if (sum > 0)
        		end--;
        	else
        		start++;
        }
        
        System.out.println(num1 + " " + num2);
    }
}
