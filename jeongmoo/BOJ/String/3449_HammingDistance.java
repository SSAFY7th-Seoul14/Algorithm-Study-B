import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3449번. 해밍거리
public class BOJ3449_HammingDistance {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < t; i++) {
			String first = br.readLine();
			String second = br.readLine();
			
			int count = 0;
			for (int j = 0; j < first.length(); j++) {
				if(first.charAt(j) != second.charAt(j))
					count++;				
			}
			
			System.out.printf("Hamming distance is %d.\n", count);
		}
	}
}
