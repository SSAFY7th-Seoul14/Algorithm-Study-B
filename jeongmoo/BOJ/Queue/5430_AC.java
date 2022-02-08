import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 백준5430번. AC
public class BOJ5430_AC {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		

		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Deque<Integer> deque = new LinkedList<>();
		boolean isReverse = false;
		boolean isError = false;
		for (int i = 0; i < t; i++) {
			deque.clear();
			isReverse = false;
			isError = false;
			
			// 입력
			char[] commands = br.readLine().toCharArray();
			int n = Integer.parseInt(br.readLine());
			String data = br.readLine();
			st = new StringTokenizer(data, "[],");
			
			while(st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				deque.add(num);
			}
			
			// 처리
			for (int j = 0; j < commands.length; j++) {
				switch (commands[j]) {
				case 'R':
					isReverse = !isReverse;
					break;
				case 'D':
					if(deque.isEmpty())
						isError = true;
					else {
						if (!isReverse)
							deque.pollFirst();
						else
							deque.pollLast();
					}
					break;
				default:
					break;
				}
				
				if(isError)
					break;
			}
			
			// 출력
			StringBuilder sb = new StringBuilder();
			if (isError)
				sb.append("error");
			else {
				sb.append("[");
				int size = deque.size();
				for (int j = size; j > 0; j--) {
					if(isReverse)
						sb.append(deque.pollLast() + ",");
					else
						sb.append(deque.pollFirst() + ",");
				}
				
				if (size!=0)
					sb.setLength(sb.length()-1);
				sb.append("]");
			}
			System.out.println(sb.toString());
		}
	}
}
