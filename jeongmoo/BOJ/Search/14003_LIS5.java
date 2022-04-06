import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 14003번. 가장 긴 증가하는 부분 수열 5
public class BOJ14003_LIS5 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] data = new int[n];
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
    	
    	// 계산
    	int[] lis = new int[n];
    	ArrayList<int[]> path = new ArrayList<>(); // path에 경로 저장
    			
    	int size = 0;
    	lis[size++] = data[0];
    	path.add(new int[] {1, data[0]});
    	
    	for (int i = 1; i < n; i++) {    		
    		int start = 0;
    		int end = size;
    		while(start<end) {
    			int mid = (start+end)/2;
    			if (lis[mid] < data[i])
    				start = mid+1;
    			else
    				end = mid;
    		}
    		lis[end] = data[i];
    		path.add(new int[] {end+1, data[i]}); // 길이, 값 쌍으로 경로 저장
    		
    		if (size <= end)
    			size++;
		}
    	System.out.println(size);
    	
    	// 경로 출력
    	Stack<Integer> s = new Stack<>();
		for (int i = n-1; i >= 0; i--) {
			if (size == path.get(i)[0]) { // 경로의 lis 길이
				s.push(path.get(i)[1]); // 경로의 수열 값
				size--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (!s.isEmpty()) {
			sb.append(s.pop() + " ");
		}
		System.out.println(sb);
    }
}
