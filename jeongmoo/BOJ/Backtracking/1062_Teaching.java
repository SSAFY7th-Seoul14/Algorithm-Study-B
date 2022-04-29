import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1062번. 가르침
public class BOJ1062_Teaching {
	static int n, k, result;
	static int[][] wordAlpha;
	static int[] alphaCnt = new int[26];
	static int k2 = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		wordAlpha = new int[n][26];
		
		for (int i = 0; i < n; i++) {
			for (char c : br.readLine().toCharArray()) {
				wordAlpha[i][c-'a']++;
				alphaCnt[c-'a']++;
			}
		}
		
		// 사용된 알파벳의 총개수
		for (int i = 0; i < 26; i++) {
			if (alphaCnt[i] > 0)
				k2++;
		}
		
		// anta, tica는 모든 단어에 포함되므로 acint는 무조건 뽑아야 한다.
		// acint를 못 뽑으면 무조건 0
		if (k < 5) {
			System.out.println(0);
			return;
		}
		
		// 5개는 뽑는다고 생각하고 6개부터 뽑자
		int flag = 0;
		int[] acint = {'a', 'c', 'i', 'n', 't'};
		for (int alpha : acint) {
			flag |= 1<<(alpha-'a');
		}
		find(5, 0, flag);
		System.out.println(result);
	}
	
	public static void find(int idx, int start, int flag) {
		// k가 사용된 알파벳 총개수(2k)보다 크면 백트래킹을 통해 check가 안될수있다.
		// 그 경우에는 모두 (k2) 뽑았을 때도 체크한다. 
		if (idx == k || idx == k2) {
			check(flag);
			return;
		}
		
		// 알파벳 26개중 k개 선택
		for (int i = start; i < 26; i++) {
			if (alphaCnt[i] == 0) continue; // 하나도 없는 알파벳은 뽑지 않는다.
			if ((flag&1<<i) != 0) continue; // 미리 선택된 acint는 넘어간다.
			find(idx+1, i+1, flag|1<<i);
		}
	}
	
	public static void check(int flag) {
		int count = 0;
		
		// n개 단어 체크
		for (int j = 0; j < n; j++) {
			boolean isAvailable = true;
			
			// 선택되지 않은 알파벳이 있으면 못 읽음
			for (int i = 0; i < 26; i++) {
				if (alphaCnt[i] == 0) continue; // 없는 알파벳은 체크 x
				// 뽑은 알파벳이 현재 단어에 있는지 체크
				if ((flag & 1<<i) == 0 && wordAlpha[j][i] > 0) {
					isAvailable = false;
					break;
				}				
			}
			
			if (isAvailable)
				count++;
		}
		
		result = Math.max(result, count);
	}
}
