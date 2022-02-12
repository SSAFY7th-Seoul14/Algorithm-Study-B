package com.ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	char data;
	Node left, right;
	public Node(char data) {
		super();
		this.data = data;
	}
}

class Tree{
	Node Root;
	
	public void add(char s, char l, char r) {
		if(Root == null) {
			if(s !='.') Root = new Node(s);
			if(l !='.') Root.left = new Node(l);
			if(r !='.') Root.right= new Node(r);
		}else {
			search(Root, s, l, r);
		}
	}
	public void search(Node Root, char s, char l, char r) {
		if (Root == null) return;
		//루트가 s인 경우
		else if(Root.data == s) {
			if(l != '.') Root.left = new Node(l);
			if(r != '.') Root.right = new Node(r);
		}else { //못찾으면 재귀로 양쪽탐색 ( 분할 )
			search(Root.left,s,l,r);
			search(Root.right,s,l,r);
		}
	}
		public void PostOrder(Node n) {
			//VLR
			if(n.left != null) PostOrder(n.left);
			if(n.right != null) PostOrder(n.right);
			System.out.print(n.data);
		}
		
		public void InOrder(Node n) {
			//LVR
			if(n.left != null) InOrder(n.left);
			System.out.print(n.data);
			if(n.right != null) InOrder(n.right);
		}
		
		public void PreOrder(Node n) {
			//LRV
			System.out.print(n.data);
			if(n.left != null) PreOrder(n.left);
			if(n.right != null) PreOrder(n.right);
		}
	
}
public class BOJ1991 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Tree tree = new Tree();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char data = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			tree.add(data,left,right);
		}
		tree.PreOrder(tree.Root);
		System.out.println();
		tree.InOrder(tree.Root);
		System.out.println();
		tree.PostOrder(tree.Root);
	}

}
