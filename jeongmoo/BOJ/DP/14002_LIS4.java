import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 14002번. 가장 긴 증가하는 부분 수열 4
public class BOJ14002_LIS4 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] data = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		// LIS 계산
		ArrayList<ArrayList<Integer>> lis = new ArrayList<>();
		int maxSize = 0;
		int maxIdx = 0;
		for (int i = 0; i < n; i++) {
			// pmax, pmaxIdx = 이전(i-1)까지의 가장 긴 수열 길이, 해당 index
			int pmax = 1;
			int pmaxIdx = i;
			ArrayList<Integer> cur = new ArrayList<>();
			for (int j = 0; j < i; j++) {
				// 증가하는 수열이면서, 값이 더 커지는 경우 갱신한다.
				if (data[j] < data[i] && pmax < lis.get(j).size() + 1) {
					pmax = lis.get(j).size() + 1;
					pmaxIdx = j;
				}
			}
			
			// lis가 1이 아니라면, 해당 인덱스를 복사해서 현재 cur에 넣어준다.
			if (pmaxIdx != i) {
				ArrayList<Integer> maxList = lis.get(pmaxIdx);
				for (int num : maxList) {
					cur.add(num);
				}
			}
			
			// 현재 값을 맨 뒤에 넣는다.
			cur.add(data[i]);
			lis.add(cur);
			
			// max값 크기, index 갱신
			if (maxSize < cur.size()) {
				maxSize = cur.size();
				maxIdx = i;
			}
		}
		
		// 출력
		System.out.println(maxSize);
		for (int num : lis.get(maxIdx)) {
			System.out.print(num + " ");
		}
	}
}