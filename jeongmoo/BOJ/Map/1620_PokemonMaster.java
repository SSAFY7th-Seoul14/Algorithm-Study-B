import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 백준1620번. 나는야 포켓몬 마스터 이다솜
public class BOJ1620_PokemonMaster {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Map<String, String> map = new HashMap<>();
		Map<String, String> map2 = new HashMap<>();		
		for (int i = 1; i <= n; i++) {
			String input = br.readLine();
			map.put(Integer.toString(i), input);
			map2.put(input, Integer.toString(i));
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < m; i++) {
			String find = br.readLine();
			if (map.containsKey(find)) {
				bw.write(map.get(find)+"\n");
			} else {
				bw.write(map2.get(find)+"\n");
			}		
		}
		bw.flush();
		bw.close();
	}
}
