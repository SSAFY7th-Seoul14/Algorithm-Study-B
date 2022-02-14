import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 백준2805번. 나무 자르기
public class BOJ2805_CuttingTrees {	
	static ArrayList<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(list, Collections.reverseOrder());
		
		// 처리
		int total = 0;
		int current = list.get(0)-1;
		int index = 0;
		int num = 0;
		while(total < m) {
			if (index < n) {
				num = list.get(index);
			}
			
			if (index == n || num < current) {
				current--;
				total += index;
			} else {
				total += (num-current);
				index++;
			}
		}
		System.out.println(current);
	}
}