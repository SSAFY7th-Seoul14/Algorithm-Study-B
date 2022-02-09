import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준1991번. 트리 순회
class Node {
	char c;
	Node left;
	Node right;
	
	Node(char c) {
		this.c = c;
	}
	
	void setLeft(Node child) {
		this.left = child; 
	}
	void setRight(Node child) {
		this.right = child; 
	}
}

class Tree {
	Node head = null;
	Queue<Character> q = new LinkedList<>();
	
	void add(Node node) {
		if (head == null) {				
			head = node;
			return;
		}
			
		add(head, node);
	}
	
	void add(Node parent, Node child) {
		// 부모 찾기
		if(parent.left != null) {
			if (parent.left.c == child.c) {
				parent.left = child;
				return;
			}
			add(parent.left, child);
		}
		
		if(parent.right != null) {
			if (parent.right.c == child.c) {
				parent.right = child;
				return;
			}
			add(parent.right, child);
		}
	}
	
	void preorder(Node node) {
		q.offer(node.c);
		if (node.left != null) {
			preorder(node.left);
		}
		if (node.right != null) {
			preorder(node.right);
		}
	}
	
	void inorder(Node node) {
		if (node.left != null) {
			inorder(node.left);
		}
		q.offer(node.c);
		if (node.right != null) {
			inorder(node.right);
		}
	}
	
	void postorder(Node node) {
		if (node.left != null) {
			postorder(node.left);
		}
		if (node.right != null) {
			postorder(node.right);
		}
		q.offer(node.c);
	}
	
	void Print(int type) {
		switch (type) {
		case 1:
			preorder(head);
			break;
		case 2:
			inorder(head);
			break;
		case 3:
			postorder(head);
			break;
		default:
			break;
		}
		
		for (int i = q.size(); i > 0; i--) {
			System.out.print(q.poll());
		}
		System.out.println();
	}
}

public class BOJ1991_TreeTraversal {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Tree t = new Tree();
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			char parent = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			Node node = new Node(parent);
			if (left != '.')
				node.setLeft(new Node(left));
			
			if (right != '.')
				node.setRight(new Node(right));
			
			t.add(node);
		}
		
		t.Print(1);
		t.Print(2);
		t.Print(3);		
	}
}
