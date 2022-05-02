import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1062_가르침 {
	static int N,K;
	static ArrayList<Character> list;//모든 단어의 필수알파벳 뺀 중간 알파벳들 담는 배열
	static List<HashSet<Character>> midAlpha;//각 단어마다 필수알파벳 뺀 중간 알파벳들 담는 배열
	static boolean[] isSelected;//알파벳 선택여부
	static int max = 0;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(K < 5) {//반듯이 포함되어야 하는 알바벳수가 5개이기 때문에
			System.out.println(0);
			return;
		}
		
		isSelected = new boolean[26];
		isSelected['a'-'a'] = true;
		isSelected['c'-'a'] = true;
		isSelected['i'-'a'] = true;
		isSelected['n'-'a'] = true;
		isSelected['t'-'a'] = true;
		
		Set<Character> set = new HashSet<>();//가르쳐 줘야하는 알파벳 후보들을 담을 set
		midAlpha = new ArrayList<>();
		for(int i=0; i < N; i++) {
			String word = br.readLine();
			String mid = word.substring(4, word.length()-4);//필수알파벳은 제외
			
			midAlpha.add(new HashSet<>());
			for(int j=0; j < mid.length(); j++) {
				char c = mid.charAt(j);
				
				if(c != 'a' && c != 'c' && c != 'i' && c != 'n' && c != 't') {
					set.add(c);
					midAlpha.get(i).add(c);
				}
			}
		}
		
		list = new ArrayList<>(set);
		
		int pickCnt = Math.min(K-5, list.size());
		combination(0, 0, pickCnt);//조합
		
		System.out.println(max);
		
	}
	
	public static void combination(int cnt, int start, int pickCnt) {
		if(cnt == pickCnt) {
			wordCheck();
			return;
		}
		
		for(int i=start; i < list.size(); i++) {
			if(isSelected[list.get(i)-'a']) continue;
			isSelected[list.get(i)-'a'] = true;
			combination(cnt+1, i+1, pickCnt);
			isSelected[list.get(i)-'a'] = false;
		}
	}

	private static void wordCheck() {
		int count = 0;
		for(int i=0; i < N; i++) {
			int flag = 0;
			for(Character c : midAlpha.get(i)) {
				if(!isSelected[c-'a']) {
					flag = 1;
					break;
				}
			}
			
			if(flag == 0) {
				count++;
			}

		}
		max = Math.max(count, max);
	}

}
