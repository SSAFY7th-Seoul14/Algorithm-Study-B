import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889_스타트와링크 {
	static int N;
	static int[][] map;
	static int[] bucket;
	static int min = Integer.MAX_VALUE;
	static boolean[] flag;
	
	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {//시너지 입력받음
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bucket = new int[N/2];
		flag = new boolean[N];
		
		calc(0,0);
		System.out.println(min);
	}
	
	public static void calc(int cnt, int start) {
		if(cnt == N/2) {
			int[] other = new int[N/2];
			int idx = 0;
			for(int i = 0; i < N; i++) {
				if(!flag[i]) other[idx++] = i; 
			}
			min = Math.min(min, synergy(other));
			return;
		}
		
		for(int i = start; i < N; i++) {
			bucket[cnt] = i;
			flag[i] = true;
			calc(cnt+1, i+1);
			flag[i] = false;
		}
	}
	
	public static int synergy(int[] other) {
		int sum1 = 0;
		int sum2 = 0;
		for(int i=0; i<N/2; i++) {
			for(int j=i; j < N/2; j++) {
				sum1 += map[bucket[i]][bucket[j]] + map[bucket[j]][bucket[i]];
				sum2 += map[other[i]][other[j]] + map[other[j]][other[i]];
			}
		}
		
		return Math.abs(sum1 - sum2);
	}

}
