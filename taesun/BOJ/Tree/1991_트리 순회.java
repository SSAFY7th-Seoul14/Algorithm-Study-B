import java.util.Scanner;
class node {
	char name = 0;
	node left = null;
	node right = null;
	node(char name)
	{
		this.name = name;
	}
}



public class Main {
	static void preorder(node node)
	{
		if (node != null)
		{
			System.out.print(node.name);
			preorder(node.left);
			preorder(node.right);
			return;
		}
	}
	
	static void inorder(node node)
	{
		if (node != null)
		{
			inorder(node.left);
			System.out.print(node.name);
			inorder(node.right);
			return;
		}
	}
	static void postorder(node node)
	{
		if (node != null)
		{
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.name);
			return;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		node tree[] = new node[100];
		for (int i=0; i<26; i++)
			tree[i] = new node((char) (65 + i));
		int idx;
		char name, left, right;
		for (int i=0; i<n; i++)
		{
			name = scan.next().charAt(0);
			idx = name - 65;

			left = scan.next().charAt(0);
			if (left != '.')
			{
				tree[idx].left = tree[left - 65];
			}
		
			right = scan.next().charAt(0);
			if (right != '.')
			{
				tree[idx].right = tree[right - 65];
			}
		}
		preorder(tree[0]);
		System.out.println();
		inorder(tree[0]);
		System.out.println();
		postorder(tree[0]);
		System.out.println();
	}
}
