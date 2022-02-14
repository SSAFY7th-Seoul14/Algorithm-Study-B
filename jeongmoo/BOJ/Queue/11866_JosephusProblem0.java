import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준11866번. 요세푸스 문제 0
public class BOJ11866_JosephusProblem0 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			q.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while(!q.isEmpty()) {
			for (int i = 0; i < k-1; i++) {
				q.offer(q.poll());
			}
			sb.append(q.poll()+", ");
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb);
	}
}
