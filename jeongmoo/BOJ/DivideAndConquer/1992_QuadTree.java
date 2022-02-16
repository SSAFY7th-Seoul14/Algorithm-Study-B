import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1992번. 쿼드트리
public class BOJ1992_QuadTree {
	static StringBuilder sb = new StringBuilder();
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 입력
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		// 처리
		quadTree(n, 0, 0);
		
		
		// 출력
		System.out.println(sb);
	}
	
	public static void quadTree(int size, int x, int y) {		
		int half = size/2;
		boolean same = true;
 outer: for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(map[x][y] != map[i][j]) {
					same = false;
					break outer;
				}
			}
		}
 
 		if (same || size == 1) {
 			sb.append(map[x][y]);
 			return;
 		}
		
		sb.append("(");
		quadTree(half, x, y);
		quadTree(half, x, y+half);
		quadTree(half, x+half, y);
		quadTree(half, x+half, y+half);
		sb.append(")");
	}
}
