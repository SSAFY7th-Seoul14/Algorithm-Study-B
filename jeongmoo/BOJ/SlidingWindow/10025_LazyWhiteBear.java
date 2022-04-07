import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 10025번. 게으른 백곰
public class BOJ10025_LazyWhiteBear {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	
    	// 모든 얼음 양동이 위치는 다르므로 심플하게 배열로 잡자.
    	int[] data = new int[1000001];
    	int maxPos = 0;
    	for (int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int pos = Integer.parseInt(st.nextToken());
			data[pos] = num;
			maxPos = Math.max(maxPos, pos);
		}
    	
    	int sum = 0;
    	int max = 0;
    	int m = k*2+1; // -k~k까지 가능하므로 사이즈는 2k+1

    	// 0~max위치까지만 탐색
    	for (int i = 0; i <= maxPos; i++) {
			sum += data[i];
			if (i >= m)
				sum -= data[i-m];
			max = Math.max(max, sum);
		}
    	System.out.println(max);
    }
}
