import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2096번. 내려가기
public class BOJ2096_GoDown {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][3];
        
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        int[][] max = new int[n][3];
        int[][] min = new int[n][3];
        for (int i = 0; i < 3; i++) {
        	max[0][i] = map[0][i];
        	min[0][i] = map[0][i];
		}
        
        for (int i = 1; i < n; i++) {
        	max[i][0] = Math.max(max[i-1][0], max[i-1][1]) + map[i][0];
        	max[i][1] = Math.max(Math.max(max[i-1][0], max[i-1][1]), max[i-1][2]) + map[i][1];
        	max[i][2] = Math.max(max[i-1][2], max[i-1][1]) + map[i][2];
        	
        	min[i][0] = Math.min(min[i-1][0], min[i-1][1]) + map[i][0];
        	min[i][1] = Math.min(Math.min(min[i-1][0], min[i-1][1]), min[i-1][2]) + map[i][1];
        	min[i][2] = Math.min(min[i-1][2], min[i-1][1]) + map[i][2];
		}
    	
        int r1 = Math.max(Math.max(max[n-1][0], max[n-1][1]), max[n-1][2]);
        int r2 = Math.min(Math.min(min[n-1][0], min[n-1][1]), min[n-1][2]);
        
        System.out.println(r1 + " " + r2);
    }
}
