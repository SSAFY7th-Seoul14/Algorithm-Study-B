package com.ssafy.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//BOJ / Ʈ����ȸ / S1 /16�� + ING...
// https://www.acmicpc.net/problem/1991

public class Main_1991 {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static class Node{
		char data;
		Node left;
		Node right;
		
		Node(char data){
			this.data = data;
		}
	}
	
	static class Tree{
		Node root;
		
		public void add(char data, char left, char right) {
			if(root==null) { // Ʈ�� �ʱ�ȭ
				root = new Node(data);
				if(left!='.')
					root.left = new Node(left);
				if(right!='.')
					root.right = new Node(right);
			}
			else // ��� �ִ� ��� ã���� ����
				search(root, data, left, right);
		}
		
		
		public void search(Node node, char data, char left, char right) {
			if(node==null) return; //������ ��尡 �����Ƿ� ��� ����
			else if(node.data==data) { //��� ���� 
				if(left!='.')
					node.left = new Node(left);
				if(right!='.')
					node.right = new Node(right);
			}
			else {
				search(node.left, data, left, right); // ���� ��� Ž��
				search(node.right, data, left, right); // ������ ��� Ž��
			}
		}
		
		//���� ��ȸ
		public void PreOrder(Node root) throws IOException {
			bw.write(root.data);
			
			if(root.left!=null) PreOrder(root.left);
			if(root.right!=null) PreOrder(root.right);
			
		}
		//���� ��ȸ
		public void InOrder(Node root) throws IOException {
			if(root.left!=null) InOrder(root.left);
			bw.write(root.data);
			if(root.right!=null) InOrder(root.right);
		}
		//���� ��ȸ
		public void PostOrder(Node root) throws IOException {
			if(root.left!=null) PostOrder(root.left);
			if(root.right!=null) PostOrder(root.right);
			bw.write(root.data);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Tree trees = new Tree();
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			trees.add(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
		}
		
		//���� ��ȸ
		trees.PreOrder(trees.root);
		bw.write('\n');
		//���� ��ȸ
		trees.InOrder(trees.root);
		bw.write('\n');
		//���� ��ȸ
		trees.PostOrder(trees.root);
		bw.write('\n');
		
		bw.close();
		br.close();
	}

}
