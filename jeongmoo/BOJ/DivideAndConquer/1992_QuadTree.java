import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

// 백준1992번. 쿼드 트리
public class BOJ1992_QuadTree {
	static StringBuilder sb = new StringBuilder();
	static char[][] map;
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		sb.append(quadtree(n, 0, 0));
		System.out.println(sb);
	}
	
	static String quadtree(int size, int x, int y) {
		int nextSize = size/2;
		String first;
		String second;
		String third;
		String fourth;
		if (size == 2) {
			first = Character.toString(map[y][x]); 
			second = Character.toString(map[y][x+nextSize]); 
			third = Character.toString(map[y+nextSize][x]); 
			fourth = Character.toString(map[y+nextSize][x+nextSize]); 
		} else {
			first = quadtree(nextSize, x, y);
			second = quadtree(nextSize, x+nextSize, y);
			third = quadtree(nextSize, x, y+nextSize);
			fourth = quadtree(nextSize, x+nextSize, y+nextSize);
		}
		
		// 반례
		// 0101
		// 0000
		// 0101
		// 0000 의 경우 정답 ((0100)(0100)(0100)(0100))이 나와야하므로 같은지 체크
		boolean allSame = true;
		char temp = first.charAt(0);
		for (int i = 1; i < first.length(); i++) {
			if (first.charAt(i) != temp) {
				allSame = false;
				break;
			}			
		}
		
		if (first.equals(second) && second.equals(third) && third.equals(fourth) && allSame)
			return first;
		else
			return "("+first + second + third + fourth+")";
	}
}
