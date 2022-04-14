import java.io.*;
import java.util.*;

// BOJ / 행렬 / S1 / 50분 (아이디어 못 떠올림)...
// https://www.acmicpc.net/problem/1080
public class Main_1080 {
	static int N, M;
	static int arrA[][], arrB[][];
	static boolean isSame[][];
	static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arrA = new int[N][M]; // 행렬 A
		arrB = new int[N][M]; // 행렬 B

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++)
				arrA[i][j] = input.charAt(j) - '0';
		}
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++)
				arrB[i][j] = input.charAt(j) - '0';
		}

		if (N < 3 || M < 3) { // 행, 열 크기가 3보다 작으면
			if (isSame())
				res = 0;
			else
				res = -1;
		} else {
			for (int i = 0; i < N - 2; i++) {
				for (int j = 0; j < M - 2; j++) {
					if (arrA[i][j] != arrB[i][j]) {
						flip(i, j); // 3X3 부분배열 뒤집기
						res++;
					}
				}
			}
		}

		if (isSame())
			System.out.println(res);
		else
			System.out.println(-1);

	}

	private static void flip(int r, int c) { //부분배열 뒤집기
		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				arrA[i][j] = arrA[i][j] ^ 1;
			}
		}

	}

	private static boolean isSame() { //행렬 A와 행렬 B가 같은지
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arrA[i][j] != arrB[i][j]) {
					flag = false;
					return flag;
				}
			}
		}
		return flag;
	}

}
