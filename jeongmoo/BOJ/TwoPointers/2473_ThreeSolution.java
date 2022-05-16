import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2473번. 세 용액
public class BOJ2473_ThreeSolution {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] data = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	data[i] = Integer.parseInt(st.nextToken());
		}
        Arrays.sort(data);
        
        int left = 0;
        int right = n-1;
        long min = Long.MAX_VALUE;
        long num1=0, num2=0, num3=0;
        boolean isFind = false;
        // 첫번째 값을 선택한 후
        for (int i = 0; i < n; i++) {
        	long first = data[i];
        	
        	// 그 값 뒤에서 2개를 고른다.
        	left = i+1;
        	right = n-1;
	    	while(left < right) {
	         	long sum = first + data[left] + data[right];
	         	if (Math.abs(sum) < min) {
	         		min = Math.abs(sum);
	         		num1 = first;
	         		num2 = data[left];
	         		num3 = data[right];
	         	}
	         	
	         	if (0 == sum) {
	         		isFind = true;
	         		break;
	         	}
	         	
	         	if (0 < sum)
	         		right--;
	         	else
	         		left++;
	        }
	    	
	    	if (isFind)
         		break;
		}
       
        System.out.println(num1 + " " + num2 + " " + num3);
    }
}
