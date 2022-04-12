import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3078번. 좋은 친구
public class BOJ3078_GoodFriend {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] count = new int[21];
        String[] data = new String[n];
        for (int i = 0; i < n; i++) {
			data[i] = br.readLine();
		}
        
        // 최초값
        long pairCnt = 0;
        int m = k+1; // 작거나 같은이므로 k+1까지
        for (int i = 0; i < n; i++) {		        	
        	if (i>=m)
        		count[data[i-m].length()]--;
			count[data[i].length()]++;
			
			if (count[data[i].length()] > 1)
				pairCnt += count[data[i].length()]-1;
		}
        System.out.println(pairCnt);
    }
}
