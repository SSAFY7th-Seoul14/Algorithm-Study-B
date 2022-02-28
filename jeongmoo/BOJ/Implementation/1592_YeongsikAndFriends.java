import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1592번. 영식이와 친구들
public class BOJ1592_YeongsikAndFriends {
	static int n;
	static int min = Integer.MAX_VALUE;
	static int[][] data;
	static boolean[] materials;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		int[] count = new int[n+1];
		
		int index = 1;
		int result = 0;
		while(true) {
			count[index]++;
			result++;
			
			int cur = count[index];
			if (cur == m)
				break;
			
			if (cur%2 == 1) {
				index += l;
				if (index > n) {
					index -= n;
				}
			} else {
				index -= l;
				if (index <= 0) {
					index += n;
				}
			}
		}
		
		System.out.println(result-1);
	}
}

