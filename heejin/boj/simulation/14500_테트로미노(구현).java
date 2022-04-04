import java.io.*;
import java.util.*;

// BOJ / 테트로미노 / G5 / 1시간
// https://www.acmicpc.net/problem/14500
public class Main_14500 {
	static int res;
	static int map[][];
	static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//모든 좌표에 대해 테트로미노 5개에 대한 합 구하기
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				tet1(i,j);
				tet2(i,j);
				tet3(i,j);
				tet4(i,j);
				tet5(i,j);
			}
		}
		System.out.println(res);
		
	}
	private static void tet5(int x, int y) {
		//1.
		int sum = 0;
		if(y+1<M && x-1>=0 && x+1<N) {
			sum +=map[x][y];
			for(int i=x-1;i<x+2;i++) 
				sum +=map[i][y+1];
			res = Math.max(res, sum);
		}
		if(x+1<N && y-1>=0 && y+1<M) {
			sum=0;
			sum +=map[x][y];
			for(int i=y-1;i<y+2;i++)
				sum +=map[x+1][i];
			res = Math.max(res, sum);
		}
		if(y-1>=0 && x-1>=0 && x+1<N) {
			sum = 0;
			sum +=map[x][y];
			for(int i=x-1;i<x+2;i++)
				sum +=map[i][y-1];
			res = Math.max(res, sum);
		}
		if(x-1>=0 && y-1>=0 && y+1<M) {
			sum = 0;
			sum +=map[x][y];
			for(int i=y-1;i<y+2;i++)
				sum +=map[x-1][i];
			res = Math.max(res, sum);
		}
		
	}
	private static void tet4(int x, int y) {
		//1. 세로로 내려가는 경우
		int sum = 0;
		if(x+2<N) {
			for(int i=x;i<x+2;i++)
				sum += map[i][y];
			if(y-1>=0) {
				int sum2 = sum + map[x+1][y-1];
				sum2 += map[x+2][y-1];
				res = Math.max(res, sum2);
				
			}
			if(y+1<M) {
				int sum2 = sum + map[x+1][y+1];
				sum2 +=map[x+2][y+1];
				res = Math.max(res, sum2);
			}
		}
		//2. 가로로 뻗어지는 경우
		if(y+2<M) {
			sum = 0;
			for(int i=y;i<y+2;i++)
				sum +=map[x][i];
			if(x-1>=0) {
				int sum2 = sum + map[x-1][y+1];
				sum2 += map[x-1][y+2];
				res = Math.max(res, sum2);
			}
			if(x+1<N) {
				int sum2 = sum + map[x+1][y+1];
				sum2 += map[x+1][y+2];
				res = Math.max(res, sum2);
			}
		}
		
	}
	private static void tet3(int x, int y) { //ㄴ자 테트로미노
		//1. 세로로 내려가는 경우
		int sum = 0;
		if(x+2<N) {
			for(int i=x;i<x+3;i++)
				sum += map[i][y];
			if(y-1>=0) {
				int sum2 = sum + map[x][y-1];
				res = Math.max(res, sum2);
				int sum3 = sum + map[x+2][y-1];
				res = Math.max(res, sum3);
			}
			if(y+1<M) {
				int sum2 = sum + map[x][y+1];
				res = Math.max(res, sum2);
				int sum3 = sum + map[x+2][y+1];
				res = Math.max(res, sum3);
			}
		}
		sum = 0;
		//2. 가로로 넓어지는 경우
		if(y+2<M) {
			for(int i=y;i<y+3;i++)
				sum +=map[x][i];
			if(x-1>=0) {
				int sum2 = sum + map[x-1][y];
				res = Math.max(res, sum2);
				int sum3 = sum + map[x-1][y+2];
				res = Math.max(res, sum3);
			}
			if(x+1<N) {
				int sum2 = sum + map[x+1][y];
				int sum3 = sum + map[x+1][y+2];
				res = Math.max(res, sum2);
				res = Math.max(res, sum3);
			}
		}
		
	}
	private static void tet2(int x, int y) { //정사각형 테트로미노
		int sum = 0;
		if(x+1<N && y+1<M) {
			for(int i=x;i<x+2;i++) {
				for(int j=y;j<y+2;j++)
					sum += map[i][j];
			}
			res = Math.max(res, sum);
		}
		
	}
	private static void tet1(int x, int y) { //일직선 테트로미노
		//세로 테트로미노
		int sum=0;
		if(y+3<M) {
			for(int i=y;i<y+4;i++)
				sum += map[x][i];
			res = Math.max(res, sum);
		}

		//가로 테트로미노
		sum = 0;
		if(x+3<N) {
			for(int i=x;i<x+4;i++)
				sum +=map[i][y];
			res = Math.max(res, sum);
		}
	}
}
