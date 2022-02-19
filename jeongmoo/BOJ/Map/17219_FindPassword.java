import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 백준 17219번. 비밀번호 찾기
public class BOJ17219_FindPassword {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		HashMap<String, String> map = new HashMap<>();
		String[][] arr = new String[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			map.put(st.nextToken(), st.nextToken());
		}
		
		// 입력 및 저장
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			String site = br.readLine();
			sb.append(map.get(site)).append("\n");
		}
	
		// 출력
		System.out.println(sb);
	}
}
