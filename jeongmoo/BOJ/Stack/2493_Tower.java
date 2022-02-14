import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 2493번. 탑
public class BOJ2493_Tower {
	// 핵심 -> 높이가 더 낮은 이전 값은 필요가 없음.
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		Stack<Point> s = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 입력과 동시에 처리
		for (int i = 0; i < n; i++) {
			// 입력
			int height = Integer.parseInt(st.nextToken());

			// 처리
			// 이전 탑이 있는 경우
			while(!s.empty()) {
				// 이전 탑의 높이가 더 크거나 같으면 수신 => 출력
				Point p = s.peek();
				if(p.x >= height) {
					sb.append(p.y + " ");
					break;
				}
				
				// 현재가 더 큰값이면(else) 이전의 값은 앞으로도 수신 못함 => 제거
				s.pop();
			}
			
			// 스택이 비어있으면 앞에 걸리는 타워가 없다.
			if(s.empty())
				sb.append("0 ");
			
			// 스택에 현재 데이터를 넣는다.
			s.push(new Point(height, i+1));
		}
		
		// 출력
		System.out.println(sb);
	}
}