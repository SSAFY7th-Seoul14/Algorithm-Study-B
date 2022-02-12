package com.ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ10866 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		LinkedList<Integer> deq = new LinkedList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0;i<n;i++) {
			
			String[] cmd = br.readLine().split(" ");
			if(cmd[0].equals("push_front")) {
				deq.offerFirst(Integer.parseInt(cmd[1]));
			}
			if(cmd[0].equals("push_back")) {
				deq.offer(Integer.parseInt(cmd[1]));
			}
			if(cmd[0].equals("pop_front")) {
				if(!deq.isEmpty()) {
					System.out.println(deq.poll());
				}else {
					System.out.println(-1);
				}
			}
			if(cmd[0].equals("pop_back")) {
				if(!deq.isEmpty()) {
					System.out.println(deq.pollLast());
				}else {
					System.out.println(-1);
				}
			}
			if(cmd[0].equals("size")) {
				System.out.println(deq.size());
			}
			if(cmd[0].equals("empty")) {
				System.out.println(deq.isEmpty() ? 1 : 0);
			}
			if(cmd[0].equals("front")) {
				System.out.println(deq.isEmpty() ? -1 : deq.peek());
			}
			if(cmd[0].equals("back")) {
				System.out.println(deq.isEmpty() ? -1 : deq.peekLast());
			}
		}
	}

}
