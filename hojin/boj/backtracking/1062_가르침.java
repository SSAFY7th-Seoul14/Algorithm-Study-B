import java.util.*;
import java.io.*;

public class BOJ1062_가르침 {
	static int k;
	static int[] selected;
	static Set[] sets;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1<= n <=50
		int n = stoi(st.nextToken());
		sets = new HashSet[n];
		for (int i = 0; i < n; ++i) {
			sets[i] = new HashSet<Integer>();
		}
		// 0 <= k <= 26
		k = stoi(st.nextToken());
		for (int i = 0; i < n; ++i) {
			// 8 <= <= 15
			char[] tmp = br.readLine().toCharArray();
			for (char c : tmp) {
				// 각 문자열마다 set 생성
				sets[i].add(c - 'a' + 1);
			}
		}
		max = 0;
		// k가 acint보다도 작으면 0 출력
		if (k < 5) {
			System.out.println(max);
		} else {
			selected = new int[k];
			selected[0] = 1;// a
			selected[1] = 3;// c
			selected[2] = 9;// i
			selected[3] = 14;// n
			selected[4] = 20;// t
			// acint는 반드시 포함되야하므로 미리 선택해두기
			combi(5, 1);
			System.out.println(max);
		}
		br.close();
	}

	private static void combi(int cnt, int start) {
		if (cnt == k) {
			int cntRead = 0;
			for (Set set : sets) {
				// set 개수가 k보다도 크면 건너뛰기
				int size = set.size();
				if (size > k)
					continue;
				int cntCompare = 0;
				for (int i = 0; i < k; ++i) {
					if (set.contains(selected[i])) {
						++cntCompare;
					}
				}
				// setSize만큼을 다 세어줘야 읽을 수 있는 것
				if (cntCompare == size)
					++cntRead;
			}
			max = Math.max(max, cntRead);
			return;
		}
		// 1 3 9 14 20
		for (int i = start; i <= 26; ++i) {
			//acint는 건너뛰기
			if (i == 1 || i == 3 || i == 9 || i == 14 || i == 20)
				continue;
			selected[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
