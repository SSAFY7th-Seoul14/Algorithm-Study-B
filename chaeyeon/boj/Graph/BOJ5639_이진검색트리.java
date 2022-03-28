package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5639_이진검색트리 {
	static StringBuilder sb = new StringBuilder();
	public static class Node {
		Node prev, next;
		int val;
		public Node(Node prev, int val, Node next) {
			super();
			this.prev = prev;
			this.next = next;
			this.val = val;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node tree = null;

		String s="";
		while((s = br.readLine()) != null) {
			if(s.trim() == "")
				break;
			int num = Integer.parseInt(s);
			if(tree == null) {//트리가 비어있다면 트리 꼭대기로 넣어줌
				tree = new Node(null, num, null);
			}
			else //트리에 값이 있다면 자기 자리 찾아서 넣어줌
				make(num, tree);
		}
		
		postOrder(tree);
		System.out.println(sb);
	}
	
	//dfs로 후위순회
	private static void postOrder(Node cur) {
		if(cur == null) return;
		
		postOrder(cur.prev);
		postOrder(cur.next);
		sb.append(cur.val).append("\n");

	}
	
	private static void make(int num, Node point) {
		if(point.val < num) {//현재 가리키는 노드보다 값이 클 때
			if(point.next == null)//오른쪽자식이 비어있으면 넣어주기
				point.next = new Node(null, num, null);
			else//오른쪽자식이 있다면 오른쪽자식 기준으로 다시 탐색
				make(num, point.next);
		}
		else if(point.val > num) {//현재 가리키는 노드보다 값이 작을 때
			if(point.prev == null)//왼쪽자식이 비어있으면 넣어주기
				point.prev = new Node(null, num, null);
			else//왼쪽자식이 있다면 왼쪽자식 기준으로 다시 탐색
				make(num, point.prev);
		}

	}

}
