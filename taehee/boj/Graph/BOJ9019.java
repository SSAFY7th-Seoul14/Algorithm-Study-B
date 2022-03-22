package com;

import java.io.*;
import java.util.*;

public class Main {
	
	static class R{
		int x;
		String cmd;
		
		public R(int x, String cmd) {
			this.x = x;
			this.cmd = cmd;
		}
		
	}
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int t = Integer.parseInt(br.readLine());
    	
    	for (int i = 0; i < t; i++) {
    		
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		boolean[] visit = new boolean[10000];
    		
//    		숫자, 명령어
    		Queue<R> q = new LinkedList<>();
    		q.add(new R(a,""));
    		visit[a] = true;
    		
    		while(!q.isEmpty()) {
    			R r = q.poll();
    			
    			if(r.x == b) {
    				System.out.println(r.cmd);
    				break;
    			}
    			int temp = D(r.x);
    			if(!visit[temp]) {
    				q.add(new R(temp,r.cmd+"D"));
    				visit[temp] = true;
    			}
    			temp = S(r.x); 
    			if(!visit[temp]) {
    				q.add(new R(temp,r.cmd+"S"));
    				visit[temp] = true;
    			}
    			temp = L(r.x); 
    			if(!visit[temp]) {
    				q.add(new R(temp,r.cmd+"L"));
    				visit[temp] = true;
    			}
    			temp = R(r.x); 
    			if(!visit[temp]) {
    				q.add(new R(temp,r.cmd+"R"));
    				visit[temp] = true;
    			}
    		}
		}
    }
	private static int R(int x) {
		int th = x / 1000;
		int hu = x / 100 % 10;
		int te = x / 10 % 10;
		int on = x % 10;
		return 1000*on + 100 * th + 10 * hu + te;
	}
	private static int L(int x) {
		int th = x / 1000;
		int hu = x / 100 % 10;
		int te = x / 10 % 10;
		int on = x % 10;
		return 1000*hu + 100 * te + 10 * on + th;
	}
	private static int S(int x) {
		if(x==0) return 9999;
		return x-1;
	}
	private static int D(int x) {
		return x*2 % 10000;
	}

}