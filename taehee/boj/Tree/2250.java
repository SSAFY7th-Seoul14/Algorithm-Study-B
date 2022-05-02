package com;

import java.io.*;
import java.util.*;

public class Main {

	static class Node{
		int level,root,left,right;

		public Node(int level, int left, int right) {
			this.level = level;
			this.left = left;
			this.right = right;
		}
		
	}
	static Node[] tree;
	static ArrayList<Integer> order = new ArrayList<>();
	static int maxLevel = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		tree = new Node[n+1];
		for (int i = 1; i < n+1; i++) {
			tree[i] = new Node(0,0,0);
		}
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			tree[num].left = left;
			tree[num].right = right;
			if(left != -1) tree[left].root = num;
			if(right != -1) tree[right].root = num;
		}
		int root = 1;
		while(tree[root].root > 0) {
			root = tree[root].root;
		}
		Inorder(root,1); //LVR, root가 레벨1
		int[][] arr = new int[maxLevel+1][n+1];
		for(int i=1; i<n+1; i++) { //순서대로 열, level 행에 값
			arr[tree[order.get(i-1)].level][i] = order.get(i-1);
		}
		//너비 구하기
		int maxWidth = 0;
		int level = 0;
		for (int i = 1; i <= maxLevel; i++) {
			int left = n+1;
			int right = 0;
			for (int j = 1; j < n+1; j++) {
				if(arr[i][j] > 0) {
					left = Math.min(left, j);
					right = Math.max(right, j);
				}
			}
			int width = right - left + 1;
			if(width > maxWidth) {
				maxWidth = width;
				level = i;
			}
		}
		System.out.println(level + " " + maxWidth);
	}
	private static void Inorder(int root, int level) {
		if(root > 0) {
			tree[root].level = level;
			maxLevel = Math.max(maxLevel, level);
			Inorder(tree[root].left,level+1);
			order.add(root);
			Inorder(tree[root].right,level+1);			
		}
		
	}
	
}
