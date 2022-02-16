package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int[][] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		tree(n, 0, 0);

		System.out.println(sb);
	}

	private static void tree(int n, int i, int j) {
		if (compress(n, i, j)) {
			sb.append(arr[i][j]);
			return;
		}
		sb.append("(");
		tree(n / 2, i, j);
		tree(n / 2, i, j + n / 2);
		tree(n / 2, i + n / 2, j);
		tree(n / 2, i + n / 2, j + n / 2);
		sb.append(")");
	}

	private static boolean compress(int n, int i, int j) {
		int temp = arr[i][j];
		for (int x = i; x < i + n; x++) {
			for (int y = j; y < j + n; y++) {
				if (temp != arr[x][y]) {
					return false;
				}
			}
		}
		return true;
	}

}
