import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1759번. 암호 만들기
public class BOJ1759_CreatePassword {		
	static int L, C;
	static char result[];
	static char alpha[];
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		result = new char[L];
		alpha = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			char c = st.nextToken().charAt(0);
			alpha[i]= c;
		}
		Arrays.sort(alpha);
		
		// 처리
		combination(0, 0, 0, 0);
		
		// 출력
		System.out.println(sb);
	}
	
	public static void combination(int idx, int start, int vowel, int consonant) { // 모음, 자음
		if (idx == L) {
			// L개 다 뽑았을 때 모음, 자음 개수가 1개, 2개 이상만 출력
			if (vowel > 0 && consonant > 1) {
				for (int i = 0; i < L; i++) {
					sb.append(result[i]);
				}
				sb.append("\n");
			}
			return;
		}
		
		for (int i = start; i < C; i++) {
			result[idx] = alpha[i];
			if (vowelCheck(alpha[i]))
				combination(idx+1, i+1, vowel+1, consonant);
			else
				combination(idx+1, i+1, vowel, consonant+1);
				
		}
	}
	
	public static boolean vowelCheck(char c) {
		if (c=='a' || c=='e' || c=='i' || c=='o' || c=='u')
			return true;
		return false;
	}
}