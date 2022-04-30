import java.util.*;
import java.io.*;

public class BOJ2250_트리의높이와너비 {
	static class Node {
		int val;
		int row, col;
		Node left, right;

		public Node(int val) {
			this.val = val;
			this.left = null;
			this.right = null;
			this.row = 1;
		}

	}

	static int colVal = 1;
	static int maxLv = 10000;
	static int[][] table;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 노드의 개수를 나타내는 정수 N(1 ≤ N ≤ 10,000)
		int n = stoi(br.readLine());
		Node[] nodes = new Node[n + 1];
		for (int i = 1; i <= n; ++i) {
			nodes[i] = new Node(i);
		}
		// 각 노드가 나타나는 횟수를 세어줄 cnt 배열
		// root node의 경우 1번만 나온다.
		int[] cnt = new int[n + 1];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int no = stoi(st.nextToken());
			int left = stoi(st.nextToken());
			int right = stoi(st.nextToken());
			++cnt[no];
			if (left > 0) {
				nodes[no].left = nodes[left];
				++cnt[left];
			}
			if (right > 0) {
				nodes[no].right = nodes[right];
				++cnt[right];
			}
		}
		// 최대 깊이를 몰라 우선 10000을 기준으로 생성
		table = new int[maxLv + 1][2];
		for (int i = 1; i <= maxLv; ++i) {
			// 가장 작은 col 담아둘 0
			table[i][0] = Integer.MAX_VALUE;
			// 가장 큰 col 담아둘 1
			table[i][1] = 0;
		}
		for (int i = 1; i <= n; ++i) {
			// 1번 세어준 root에서 inorder 수행
			if (cnt[i] == 1) {
				inorder(nodes[i]);
				break;
			}
		}
		// 가장 큰 너비일 경우
		int maxWidth = 0;
		// 정답 lv 담을 곳
		int ansLv = 0;
		for (int i = 1; i <= maxLv; ++i) {
			if ((table[i][1] - table[i][0] + 1) > maxWidth) {
				maxWidth = table[i][1] - table[i][0] + 1;
				ansLv = i;
			}
		}
		System.out.printf("%d %d", ansLv, maxWidth);
		br.close();
	}

	// inorder 수행
	static public void inorder(Node cur) {
		// inorder가 base에서 리턴될 때부터 col을 세어주기
		if (cur.left == null) {
			cur.col = colVal++;
			// inorder 이후 빠져 나올 때 최대 lv까지만 세어주기 위해 maxLv 구하기
			maxLv = Math.max(maxLv, cur.row);
			// 자신의 row(lv) 가장 왼쪽 갱신
			table[cur.row][0] = Math.min(cur.col, table[cur.row][0]);
			// 자신의 row(lv) 가장 오른쪽 갱신
			table[cur.row][1] = Math.max(cur.col, table[cur.row][1]);
		} else {
			// cur의 left가 null이 아닐 경우 left의 row는 현재보다 1 크다.
			cur.left.row = cur.row + 1;
			inorder(cur.left);
			cur.col = colVal++;
			// inorder 이후 빠져 나올 때 최대 lv까지만 세어주기 위해 maxLv 구하기
			maxLv = Math.max(maxLv, cur.row);
			// 자신의 row(lv) 가장 왼쪽 갱신
			table[cur.row][0] = Math.min(cur.col, table[cur.row][0]);
			// 자신의 row(lv) 가장 오른쪽 갱신
			table[cur.row][1] = Math.max(cur.col, table[cur.row][1]);
		}
		if (cur.right != null) {
			// cur의 right가 null이 아닐 경우 right의 row는 현재보다 1 크다.
			cur.right.row = cur.row + 1;
			inorder(cur.right);
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
