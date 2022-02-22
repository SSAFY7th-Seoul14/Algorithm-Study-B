import java.util.*;
import java.io.*;


public class SWEA7465
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            // 이 마을 사람 수 N
            int N = Integer.parseInt(st.nextToken());
            // 서로를 알고 있는 관계 수 M
            int M = Integer.parseInt(st.nextToken());
            int[] parent = new int[N+1];
            int cnt = 0;
            // makeSet
            makeSet(parent, N);
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                // union : 합집합
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), parent);
            }
            
            // find : root를 찾는 식
            for (int i = 1; i <= N; i++) {
                if (find(i, parent) == i) cnt++;
            }
            
            sb.append(String.format("#%d %d%n", test_case, cnt));
		}
        System.out.println(sb);
	}
    public static void makeSet(int[] parent, int N) {
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }
    
    public static int find(int i, int[] parent) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i], parent);
    }
    
    public static boolean union(int a, int b, int[] parent) {
        int aRoot = find(a, parent);
        int bRoot = find(b, parent);
        if (aRoot == bRoot) return false;
        parent[bRoot] = aRoot;
        return true;
    }
}