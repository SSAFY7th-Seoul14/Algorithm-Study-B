import java.io.*;
import java.util.*;

class node{
	int value;
	node left = null;
	node right = null;
	public node(int temp) {
		// TODO Auto-generated constructor stub
		this.value = temp;
	}
	void setLeft(node newLeft) 
	{ 
		left = newLeft;
	}
	void setRight(node newRight)
	{
		right = newRight;
	}
}
public class Main {
	
	static int n,m, ans, num;
	static node tree;
	
	// 이진탐색트리 규칙대로 노드를 하나씩 추가해준다 
	static node insert(node root, node node)
	{
		if (root == null)
			return node;
		if (root.value > node.value)
			root.setLeft(insert(root.left, node));
		else 
			root.setRight(insert(root.right, node));
		return root;
	}
	
	// postorder 실행 
	static void postOrder(node node)
	{
		if (node != null)
		{
			postOrder(node.left);
			postOrder(node.right);
			System.out.println(node.value);
		}
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		int temp;
		
		// 루트는 1번 인덱스 사용 
		while (scan.hasNext())
		{
			temp = scan.nextInt();
			node newNode = new node(temp);
			tree = insert(tree, newNode);
		}
		postOrder(tree);
		// eof 넣고 싶으면 ctrl + z + enter
	}
}