package com.ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ18258 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		LinkedList<Integer> q = new LinkedList<>();
		
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			StringTokenizer cmd = new StringTokenizer(br.readLine()," ");
			switch(cmd.nextToken()) {
			
			case "push":
				int x = Integer.parseInt(cmd.nextToken());
				q.offer(x);
				break;
				
			case "pop":
				if(!q.isEmpty()) {
					sb.append(q.poll()).append("\n");
				}else {
					sb.append(-1).append("\n");
				}
				break;
				
			case "size" :
				sb.append(q.size()).append("\n");
				break;
				
			case "empty" :
				sb.append(q.isEmpty() ? 1 : 0).append("\n");
				break;
				
			case "front" : 
				sb.append(q.isEmpty() ? -1 : q.peek()).append("\n");
				break;
				
			case "back" : 
				sb.append(q.isEmpty() ? -1 : q.peekLast()).append("\n");
				break;
			}
		}
		System.out.println(sb);
	}

}
