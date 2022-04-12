import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 백준 11974번. Subsequences Summing to Sevens
public class BOJ11974_Subsequences {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] data = new int[n+1];
    	
    	// 나머지값 0~6까지 저장할 리스트
    	ArrayList<ArrayList<Integer>> modList = new ArrayList<>();
    	for (int i = 0; i < 7; i++) {
    		modList.add(new ArrayList<>());
		}
    	
    	// 누적합이 커질 수 있으므로 7을 나눈 값을 저장한다.
    	// 누적합의 나머지가 0 -> 다음 나머지 0 까지는 7의 배수가 된다.
    	// 마찬가지로 나머지1 ->1, 2->2, ...으로 같은 나머지값이면 연속된다는 것
    	int mod = 0;
    	modList.get(0).add(0); // 최초에 아무것도 넣지 않은 경우에는 나머지 0인 경우이므로 0번 인덱스를 먼저 넣어둔다.
    	for (int i = 1; i <= n; i++) {
			data[i] = Integer.parseInt(br.readLine());
			mod = (mod + data[i]) % 7;
			modList.get(mod).add(i);
		}
    	
    	int max = 0;
    	for (int i = 0; i < 7; i++) {
    		ArrayList<Integer> list = modList.get(i);
    		int size = list.size();
    		// 최소 2개는 있어야 시작과 끝을 확인 가능하다.
    		if (size >= 2) {
    			max = Math.max(max, list.get(size-1) - list.get(0));
    		}
    	}
    	
		System.out.println(max);
    }
}
