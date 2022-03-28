import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
	int key;
	Node left, right;

	public Node(int key) {
		this.key = key;
	}

}

public class BOJ5639_이진검색트리 {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node root = new Node(Integer.parseInt(br.readLine()));
		String input;
		while ((input = br.readLine()) != null) {
			pushNode(root, Integer.parseInt(input));
		}
		postOrder(root);
		System.out.println(sb);
	}

	private static void pushNode(Node node, int input) {
		if (input < node.key) {
			if (node.left == null) {
				node.left = new Node(input);
			} else {
				pushNode(node.left, input);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(input);
			} else {
				pushNode(node.right, input);
			}
		}
	}

	public static void postOrder(Node node) {
		if (node.left != null) {
			postOrder(node.left);
		}
		if (node.right != null) {
			postOrder(node.right);
		}
		sb.append(String.format("%d%n", node.key));
	}
}