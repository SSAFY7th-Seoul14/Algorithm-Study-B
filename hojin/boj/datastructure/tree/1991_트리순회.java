import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 1시간
public class BOJ1991 {
	static class Node {
		char data;
		Node leftN, rightN;

		Node(char data) {
			this.data = data;
		}
	}

	public static void addNode(char data, char left, char right) {
		if (root == null)
			root = new Node(data);
		addNode(root, data, left, right);
	}

	public static void addNode(Node node, char data, char left, char right) {
		if (node == null)
			return;
		if (node.data == data) {
			if (left != '.') {
				node.leftN = new Node(left);
			}
			if (right != '.') {
				node.rightN = new Node(right);
			}
		} else {
			addNode(node.leftN, data, left, right);
			addNode(node.rightN, data, left, right);
		}
	}

	public static void preorder(Node node) throws IOException {
		if (node != null) {
			bw.write(node.data);
			preorder(node.leftN);
			preorder(node.rightN);
		}
	}

	public static void inorder(Node node) throws IOException {
		if (node != null) {
			inorder(node.leftN);
			bw.write(node.data);
			inorder(node.rightN);
		}
	}

	public static void postorder(Node node) throws IOException {
		if (node != null) {
			postorder(node.leftN);
			postorder(node.rightN);
			bw.write(node.data);
		}
	}

	static BufferedReader in;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int n;
	static Node root;

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(in.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			addNode(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
		}
		in.close();
		preorder(root);
		bw.write("\n");
		inorder(root);
		bw.write("\n");
		postorder(root);
		bw.flush();
		bw.close();
	}
}