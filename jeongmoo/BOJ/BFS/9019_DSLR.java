import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 9019번. DSLR
public class BOJ9019_DSLR {	
	static class Data {
		int num;
		String command;
		
		public Data(int num, String command) {
			this.num = num;
			this.command = command;
		}
	}
	
	static StringBuilder result = new StringBuilder();
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			isVisited = new boolean[10000];
			find(a, b);
		}
		
		System.out.println(result);
	}
	
	static final String[] command = {"D", "S", "L", "R"};
	static boolean[] isVisited;
	public static void find(int start, int end) {
		Queue<Data> q = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			int result = process(start, i);
			q.offer(new Data(result, command[i]));
			isVisited[result] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			Data cur = q.poll();
			if (cur.num == end) {
				result.append(cur.command).append("\n");
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int result = process(cur.num, i);
				if (isVisited[result])
					continue;
				
				sb.setLength(0);
				sb.append(cur.command + command[i]);
				
				q.offer(new Data(result, sb.toString()));
				isVisited[result] = true;
			}
		}
	}
	
	public static int process(int num, int com) {
		if (com == 0) {
			return num*2 % 10000;
		} else if (com == 1) {
			return num==0? 9999 : num-1;
		} else if (com == 2) {
			return num%1000*10 + num/1000;
		} else {
			return num%10*1000 + num/10;
		}
	}
}
