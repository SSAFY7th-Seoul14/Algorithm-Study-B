import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SW Expert Academy 5607번. 조합
public class SWEA5607_Combination {
	static final long p = 1234567891;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for (int tc = 1; tc <= t; tc++) {
        	// 입력
        	st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken());
        	int r = Integer.parseInt(st.nextToken());
        	
        	long[] factorial = new long[n+1];
        	factorial[1] = 1;
        	for (int i = 2; i <= n; i++) {
				factorial[i] = factorial[i-1]*i % p;
			}
        	
        	long r1 = (factorial[n]*pow(factorial[n-r], p-2))%p;
        	long r2 = (r1*pow(factorial[r], p-2))%p;
        	long result = r2%p;

        	// 출력
        	System.out.printf("#%d %d\n", tc, result);
        }
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