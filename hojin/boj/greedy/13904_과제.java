import java.util.*;
import java.io.*;

public class BOJ13904_과제 {
	static class Todo implements Comparable<Todo> {
		int d, w;

		public Todo(int d, int w) {
			this.d = d;
			this.w = w;
		}

		@Override
		public int compareTo(Todo o) {
			return o.w - this.w;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = stoi(br.readLine());
		int maxDay = 0;
		Todo[] todos = new Todo[n];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int d = stoi(st.nextToken());
			maxDay = Math.max(maxDay, d);
			int w = stoi(st.nextToken());
			todos[i] = new Todo(d, w);
		}
		Arrays.sort(todos);
		int[] slots = new int[maxDay + 1];
		for (Todo todo : todos) {
			int d = todo.d;
			int w = todo.w;
			while (d > 0) {
				if (slots[d] == 0) {
					slots[d] = w;
					break;
				}
				--d;
			}
		}
		int sum = 0;
		for (int i : slots) {
			sum += i;
		}
		System.out.println(sum);
		br.close();
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
