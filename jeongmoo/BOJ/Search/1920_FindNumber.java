import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준1920번. 수 찾기
public class BOJ1920_FindNumber {
	static ArrayList<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int result = binarySearch(Integer.parseInt(st.nextToken()), 0, n-1);
			sb.append(result+"\n");
		}
		System.out.println(sb);
	}
	
	public static int binarySearch(int key, int low, int high) {
		int mid;
		int cur;
		while(low <= high) {
			mid = (high+low)/2;
			
			cur = list.get(mid);
			if(key == cur)
				return 1;
			else if(key < cur)
				high = mid-1;
			else
				low = mid+1;
		}
		return 0;
	}
}
