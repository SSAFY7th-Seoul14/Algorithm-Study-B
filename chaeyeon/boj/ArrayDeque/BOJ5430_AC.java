import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ5430_AC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		outer : for(int t = 0; t < T; t++) {
			String s = br.readLine();
			int n = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine(),"[,]");
			
			ArrayDeque<Integer> list = new ArrayDeque<>();
			
			boolean front = true; 
			for (int i = 0; i < n; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			for (int i = 0; i < s.length(); i++) {
				switch(s.charAt(i)) {
					case 'R':
						front = !front;
						break;
					case 'D':
						if(list.isEmpty()) {
							sb.append("error").append("\n");
							continue outer;
						}
						if(front) {
							list.removeFirst();
						}
						else 
							list.removeLast();
				}
			}

			sb.append("[");
			if(front) {
				while(list.size() > 1) {
					sb.append(list.removeFirst()).append(",");
				}
			}
			else {
				while(list.size() > 1) {
					sb.append(list.removeLast()).append(",");
				}
			}
			if(!list.isEmpty()) sb.append(list.remove());
			sb.append("]\n");
			
		}
		System.out.println(sb.toString());
		
		
		//StringTokenizer st = new StringTokenizer(br.readLine());

	}

}
