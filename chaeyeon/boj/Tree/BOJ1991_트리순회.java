package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 2022-02-10
 * 1시간+
 * 구글참조
 */
public class BOJ1991_트리순회 {
	static List<Node> tree;
	static StringBuilder sb = new StringBuilder();
	
	static class Node {
		char data;
		Node left;
		Node right;
		
		public Node() {
			
		}
		public Node(char data) {
			this.data = data;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		tree = new ArrayList<>();
		
		StringTokenizer st;
		
		//'A'노드부터 N개까지 만든다.
		for(int i = 0; i < N; i++) {
			tree.add(new Node((char)(i+'A')));
		}
		
		//노드의 왼쪽자식 오른쪽자식에 입력받은 노드를 넣어주어 연결시킨다.
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			char data = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			if(left != '.') {
				//입력받은 문자 - 'A'하면 리스트에 해당 문자가 어디 인덱스에 저장되어 있는지 알 수 있음.
				tree.get(data-'A').left = tree.get(left-'A');
			}
			char right = st.nextToken().charAt(0);
			if(right != '.') {
				tree.get(data-'A').right = tree.get(right-'A');
			}
			
		}
		
		preOrder(tree.get(0));
		sb.append("\n");
		inOrder(tree.get(0));
		sb.append("\n");
		postOrder(tree.get(0));
		sb.append("\n");
		
		System.out.println(sb);
		
	}
	
	public static void preOrder(Node n) {
		if(n == null) {
			return;
		}
		
		sb.append(n.data);
		preOrder(n.left);
		preOrder(n.right);
		
	}
	
	public static void inOrder(Node n) {
		if(n == null) {
			return;
		}
		
		
		inOrder(n.left);
		sb.append(n.data);
		inOrder(n.right);
		
	}
	
	public static void postOrder(Node n) {
		if(n == null) {
			return;
		}
		
		postOrder(n.left);
		postOrder(n.right);
		sb.append(n.data);
		
	}

}
