import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//40분
//특정 비트를 0으로 만드는 remove와 특벙비트를 0은 1 1->0으로 바꾸는 toggle을 어떻게 해야할지 고민하느라 오래 걸렸다.
public class BOJ11723_집합 {

	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		
		int M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int list = 0;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
				case "add":
					int x = Integer.parseInt(st.nextToken());
					list = list | 1<<x;
					break;
				case "check":
					if((list & 1<<Integer.parseInt(st.nextToken())) != 0) 
						sb.append(1 + "\n");
					else
						sb.append(0 + "\n");
					break;
				case "remove":
					list = list & ~(1<<Integer.parseInt(st.nextToken()));
					break;
				case "toggle":
					list = list ^ (1<<Integer.parseInt(st.nextToken()));
					break;
				case "empty":
					list = list & 0;
					break;
				case "all":
					list = list | ~0;
					break;

			}
		}
		System.out.println(sb);
	}

}
