package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			String commands = br.readLine();
			int n = Integer.parseInt(br.readLine());
			
			StringBuilder sb = new StringBuilder("[");
			boolean reverse = false;
			boolean error = false;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
			LinkedList<Integer> q = new LinkedList<>();
			for(int i=0; i<n; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			for(int i=0; i<commands.length(); i++) {
				char cmd = commands.charAt(i);
				if(cmd == 'R') reverse = !reverse;
				else {
					if(q.isEmpty()) {
						error = true;
						break;
					}else {
						if(reverse) { //µÚÁýÇûÀ» ¶§
							q.pollLast();
						}else {
							q.poll();
						}
					}
				}
			}
			if(error) System.out.println("error");
			else {
				if(reverse) {
					while(!q.isEmpty()) {
						sb.append(q.pollLast()).append(",");
					}
				}else {
					while(!q.isEmpty()) {
						sb.append(q.poll()).append(",");
					}					
				}
				if(sb.length()>1) sb.setLength(sb.length()-1);
				sb.append("]");
				System.out.println(sb);
			}
		}
		
	}

}
