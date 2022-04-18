package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Coin{
		int x,y;

		public Coin(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static char[][] arr;
	static int n,m,ans = 11;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		Coin[] coins = new Coin[2];
		int idx = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if(arr[i][j] == 'o') coins[idx++] = new Coin(i,j);
			}
		}
		dfs(coins,0);
		System.out.println((ans==11) ? -1 : ans);
	}

	private static void dfs(Coin[] coins, int cnt) {
		if(cnt > ans || cnt>=10) {
			return;
		}
		int[][] next = new int[2][2];
		for(int i=0; i<4; i++) {
			for(int j=0;j<2;j++) {
				next[j][0] = coins[j].x + dx[i];
				next[j][1] = coins[j].y + dy[i];
			}
			int drop = 0;
			for(int j=0;j<2;j++) { //범위밖이면 떨어짐
				if(next[j][0]<0 || n<=next[j][0] || next[j][1]<0 || m<=next[j][1]) drop++;
			}
			
			if(drop==1) {
				ans = Math.min(ans, cnt+1);
				return;
			}
			if(drop==2) continue;
			
			for (int j = 0; j < 2; j++) {
				if(arr[next[j][0]][next[j][1]] == '#') { //벽이면 안움직이기
					next[j][0] = coins[j].x;
					next[j][1] = coins[j].y;
				}
			}
			Coin[] newCoins = new Coin[2];
			for (int j = 0; j < 2; j++) {
				newCoins[j] = new Coin(next[j][0],next[j][1]);
			}
			dfs(newCoins,cnt+1);
		}
		return;
	}

}