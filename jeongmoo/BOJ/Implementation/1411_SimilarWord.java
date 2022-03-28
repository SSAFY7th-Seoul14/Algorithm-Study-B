import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

// 백준 1411번. 비슷한 단어
public class BOJ1411_SimilarWord {
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		String[] list = new String[n];
		
		for (int i = 0; i < n; i++) {
			list[i] = br.readLine().trim();
		}

		// 처리
		int count = 0;
		for (int i = 0; i < n; i++) {
			String a = list[i];
			for (int j = i+1; j < n; j++) {
				String b = list[j];
				if (similar(a, b))
					count++;
			}
		}
		
		// 결과
		System.out.println(count);
	}
	
	public static boolean similar(String a, String b) {
		HashMap<Character, Character> map = new HashMap<>();
		
		for (int i = 0; i < a.length(); i++) {
			char ca = a.charAt(i);
			char cb = b.charAt(i);
			
			// 이미 사용한 알파벳 체크
			for (Character key : map.keySet()) {
				char value = map.get(key);
				// 사용한 알파벳 중에 현재 b자리의 알파벳이 있다면. 그값이 a가 아니면 안된다.
				// (2개 이상의 알파벳이 같은 알파벳으로 바뀌면 안되므로)
				if (value == cb && key != ca)
					return false;
			}
			
			// map에 ca가 없으면 cb값으로 변환한다 생각하고 넣어준다.
			if (!map.containsKey(ca)) {			
				map.put(ca, cb);
			} else {
				// 들어 있던 값이면 변환된값이 비교할 값이랑 같은지 확인한다.
				if (map.get(ca) != cb)
					return false;
			}
		}
		
		return true;
	}
}
