import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1074번. Z
public class BOJ1074_Z {
	static int r, c;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		visit(n, 0, 0, 0);
	}
	
	static int count = 0;
	public static void visit(int n, int x, int y, int num) {
		if(n==0) {
			System.out.println(num);
			return;
		}
		
		// r행 -> y값
		// c열 -> x값
		// 해당 값이 어떤 사분면에 있는지 파악해서 해당 사분면으로 재귀
		int half = (int)Math.pow(2, n-1);
		int dist = half*half;
		if (y+half<=r) {
			if (x+half<=c)	// 오른쪽 아래
				visit(n-1, x+half, y+half, num + dist*3);
			else 			// 왼쪽 아래
				visit(n-1, x, y+half, num + dist*2);
		} else {
			if (x+half<=c)	// 오른쪽 위
				visit(n-1, x+half, y, num + dist);
			else 			// 왼쪽 위
				visit(n-1, x, y, num + 0);
		}
	}
}
