import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11401번. 이항 계수 3
public class BOJ11401_Coefficient3 {
	static final long p = 1000000007;
	static long[] factorial;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
    	// 입력
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken()); 
    	int r = Integer.parseInt(st.nextToken());
    	
    	factorial = new long[n+1];
    	factorial[0] = 1;
    	for (int i = 1; i <= n; i++) {
			factorial[i] = factorial[i-1]*i % p;
		}
    	
    	// 계산
    	long result = combination(n, r) % p;
    	
    	// 출력
    	System.out.println(result);
    }
    
    public static long combination(int n, int r) {
    	long temp1 = pow(factorial[n-r], p-2)%p;
    	long temp2 = (temp1*pow(factorial[r], p-2))%p;
    	return (temp2 * factorial[n])%p;
    }    
    
    public static long pow(long x, long y) {
    	long res = 1;
    	while (y > 0) {
			if (y %2 == 1) {
				res = res*x % p;
			}
    		y = y>>1;
        	x = x*x % p;
    	}
    	return res;
    }
}