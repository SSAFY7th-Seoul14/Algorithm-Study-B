import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7453_합이0인네정수 {

	static long cnt;
	static int size;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[n];
		int[] d = new int[n];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			a[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
			c[i] = Integer.parseInt(st.nextToken());
			d[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(a);
		Arrays.sort(b);
		Arrays.sort(c);
		Arrays.sort(d);
		size = n * n;
		// ab끼리 cd끼리 더해주고 작업해준다.
		int[] ab = new int[size];
		int[] cd = new int[size];
		int index = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				ab[index] = a[i] + b[j];
				cd[index++] = c[i] + d[j];
			}
		}
		// ab는 작은값부터
		Arrays.sort(ab);
		// cd는 큰값부터 비교해주기 위해서 정렬 수행
		Arrays.sort(cd);
		cnt = 0;
		solve(ab, cd);
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();
	}

	private static void solve(int[] ab, int[] cd) {
		// ab의 합 중 가장 작은 값부터
		int abPointer = 0;
		// cd의 합 중 가장 큰 값부터
		int cdPointer = size - 1;
		while (abPointer < size && cdPointer >= 0) {
			int abSum = ab[abPointer];
			int cdSum = cd[cdPointer];
			// ab, cd의 합이 0이면 조건에 부합하므로
			int isZero = abSum + cdSum;
			if (isZero == 0) {
				// 조건에 부합하는 개수를 세어주기 위한 작업
				// abPointer는 abSum보다 큰 값이 나타나는 가장 작은 index로 이동
				abPointer = upperBound(ab, abSum);
				// cnt 처리하기 위해 lowerBound 처리
				cdPointer = lowerBound(cd, cdSum);
				long cntAB = abPointer - lowerBound(ab, abSum);
				// cdPointer는 cdSum보다 작은 값이 나타나는 가장 큰 index로 이동하기 위해 --처리
				long cntCD = upperBound(cd, cdSum) - cdPointer--;
				cnt += cntAB * cntCD;
			}
			// 0보다 크면 큰값부터 비교하던 cdPointer를 줄인다.
			else if (isZero > 0) {
				--cdPointer;
			}
			// 0보다 작으면 작은 값부터 비교하던 abPointer를 늘린다.
			else {
				++abPointer;
			}
		}
	}

	// lowerBound는 target과 같은 값을 갖는 가장 작은 index를 구한다.
	private static int lowerBound(int[] arr, int target) {
		int left = 0;
		int right = size;
		while (left < right) {
			int middle = (left + right) / 2;
			if (arr[middle] >= target) {
				right = middle;
			} else {
				left = middle + 1;
			}
		}
		return right;
	}

	// upperBound는 target보다 큰 값이 나타나는 가장 작은 index를 찾는다.
	private static int upperBound(int[] arr, int target) {
		int left = 0;
		int right = size;
		while (left < right) {
			int middle = (left + right) / 2;
			if (arr[middle] <= target) {
				left = middle + 1;
			} else {
				right = middle;
			}
		}
		return right;
	}

}