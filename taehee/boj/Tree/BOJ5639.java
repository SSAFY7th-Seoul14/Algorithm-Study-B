package com;

import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int num;
		Node left;
		Node right;
		
		public Node(int num) {
			this.num = num;
		}
		
		void insert(int n){
			if(n < this.num) { //왼쪽으로
				//비었으면 그냥넣기
				if(this.left == null) this.left = new Node(n);
				//안비었으면 left에서 다시 insert, 재귀
				else this.left.insert(n);
			}else { //오른쪽으로
				if(this.right == null) this.right = new Node(n);
				else this.right.insert(n);
			}
		}
		
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	Node root = new Node(Integer.parseInt(br.readLine()));
    	//트리구성
    	while(true) {
    		String input = br.readLine();
    		if(input == null || input.equals("")) break;
    		root.insert(Integer.parseInt(input));
    	}
    	//후위순회
    	PostOrder(root);
    }

	private static void PostOrder(Node n) {
		if(n == null) return;
		
		PostOrder(n.left);
		PostOrder(n.right);
		System.out.println(n.num);
		return;
	}


}