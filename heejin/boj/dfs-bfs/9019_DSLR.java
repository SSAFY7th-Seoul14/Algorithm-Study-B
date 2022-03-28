import java.io.*;
import java.util.*;

// BOJ / DSLR / G5 / 1시간 반+
// https://www.acmicpc.net/problem/9019
public class Main_9019_2 {
	static int A, B;
	static boolean visited[];
	static String cmd[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			visited = new boolean[10000]; //각 숫자별 방문여부 저장
			cmd = new String[10000]; //각 숫자별 명령어 저장
			Arrays.fill(cmd, ""); //String 배열은 초기화 시 null이기에 초기화
			
			bfs();
			System.out.println(cmd[B]);
			
		}
	}
	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(A);
		visited[A]=true;
		
		while(!q.isEmpty() && !visited[B]) {
			int cur = q.poll();
			
			int D = cur*2>9999?(cur*2)%10000:cur*2;
			int S = (cur-1)>=0?cur-1:9999;
			int L = (cur%1000)*10+cur/1000;
			int R = (cur%10)*1000+cur/10;

			if(!visited[D]) {
				q.add(D);
				visited[D]=true;
				cmd[D] = cmd[cur]+"D";
			}
			if(!visited[S]) {
				q.add(S);
				visited[S]=true;
				cmd[S] = cmd[cur]+"S";
			}
			if(!visited[L]) {
				q.add(L);
				visited[L]=true;
				cmd[L] = cmd[cur]+"L";
			}
			if(!visited[R]) {
				q.add(R);
				visited[R]=true;
				cmd[R] = cmd[cur]+"R";
			}
		}
	}
}
