
import java.io.*;
import java.util.*;

//BOJ / 외판원 순회 2 / S2 / 40분
//https://www.acmicpc.net/problem/10971
public class Main_10971 {
	static int res = Integer.MAX_VALUE; //외판원 순회 비용(정답)
	static int N;
	static int map[][];
	static boolean visited[];
	static int selected[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		selected = new int[N];
		//인접행렬 입력받기
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.println(res);
		
	}
	private static void dfs(int idx) {
		if(idx==N) {
			//순회 비용 계산
			int sum=0, src=0, dst=0;
			for(int i=1;i<N;i++) {
				src = selected[i-1];
				dst = selected[i];
				sum+= map[src][dst];
			}
			
			sum += map[dst][selected[0]];
			res= Math.min(res, sum);
			return;
		}
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			
			if(idx==0) {
				selected[idx] = i;
				visited[i]=true;
				dfs(idx+1);
				visited[i]=false;
			}
			else if(idx==N-1) {
				int src = selected[idx-1];
				int dst = i;
				if(map[src][dst]!=0 && map[i][selected[0]]!=0) {
					selected[idx] = i;
					visited[i]=true;
					dfs(idx+1);
					visited[i]=false;
				}
			}
			else {
				int src = selected[idx-1];
				int dst = i;
				if(map[src][dst]!=0) { //유망하면
					selected[idx] = i;
					visited[i]=true;
					dfs(idx+1);
					visited[i]=false;
				}
			}
		}
		
	}
	
	
}
