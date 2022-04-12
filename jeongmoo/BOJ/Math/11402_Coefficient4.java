import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11402번. 이항 계수 4
public class BOJ11402_Coefficient4 {
	static int p;
	static long[] factorial;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
    	// 입력
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	long n = Long.parseLong(st.nextToken()); 
    	long r = Long.parseLong(st.nextToken());
    	p = Integer.parseInt(st.nextToken());
    	
    	// 뤼카의 정리
    	// p진수로 변환하므로 p-1이 최대값. 
    	factorial = new long[p];
    	factorial[0] = 1;
    	for (int i = 1; i < p; i++) {
			factorial[i] = factorial[i-1]*i % p;
		}
    	
    	// 계산
    	long result = 1;
    	while (n > 0 && r > 0) {
    		int nn = (int)(n%p);
    		int rr = (int)(r%p);
    		
    		if (nn<rr) {
    			result = 0;
    			break;
    		}
    		
    		result = (result * combination(nn, rr)) % p;
    		n/=p;
    		r/=p;
    	}
    	
    	// 출력
    	System.out.println(result);
    }
    
    public static long combination(int n, int r) {
    	long bottom = (factorial[n-r]*factorial[r])%p;
    	return (pow(bottom, p-2) * factorial[n])%p;
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