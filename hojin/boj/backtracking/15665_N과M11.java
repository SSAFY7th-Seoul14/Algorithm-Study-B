import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ15665_N과M11 {
	static int n, m, setSize;
	static int[] ansList;
	static Integer[] numList;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// n개의 자연수
		n = Integer.parseInt(st.nextToken());
		// 수열의 길이 m
		m = Integer.parseInt(st.nextToken());
		// N개의 자연수 중에서 M개를 고른 수열
		ansList = new int[m];

		HashSet<Integer> set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			// 10,000보다 작거나 같은 자연수
			// 어차피 같은 숫자를 여러번 쓸 수 있기 때문에 입력 받을 때부터 같은 숫자를 묶어버린다.
			set.add(Integer.parseInt(st.nextToken()));
		}
		br.close();
		// 입력받은 set을 Array로 바꿔주기
		// set을 array로 바꿀때는 Wrapper 클래스 사용이 필요하다.
		setSize = set.size();
		numList = set.toArray(new Integer[setSize]);
		// 문제와 별개로 comparator 사용을 위해서는 역시 Wrapper class가 필요
		Arrays.sort(numList);
		permu(0);
		System.out.println(sb);
	}

	private static void permu(int cnt) {
		if (cnt == m) {
			for (int i : ansList) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 0; i < setSize; i++) {
			ansList[cnt] = numList[i];
			permu(cnt + 1);
		}
	}

}