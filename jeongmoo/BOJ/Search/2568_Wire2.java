import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 2568번. 전기줄 2
public class BOJ2568_Wire2 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	ArrayList<int[]> data = new ArrayList<>();
    	
    	StringTokenizer st;
    	for (int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
			data.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
    	// A열 번호 순으로 정렬한다.
    	Collections.sort(data, (o1,o2)->o1[0]-o2[0]);
    	
    	// 계산
    	ArrayList<int[]> lis = new ArrayList<>();
    	ArrayList<int[]> path = new ArrayList<>();
    	
    	// 초기값, 초기경로
    	lis.add(data.get(0));
    	path.add(new int[] {lis.size(), data.get(0)[0]}); // 길이, A값
    	
    	for (int i = 1; i < n; i++) {    		
    		int start = 0;
    		int end = lis.size();
    		while(start<end) {
    			int mid = (start+end)/2;
    			if (lis.get(mid)[1] < data.get(i)[1]) // B값 기준으로 LIS 만든다. 
    				start = mid+1;
    			else
    				end = mid;
    		}
    		if (lis.size() == end)
    			lis.add(data.get(i));
    		else {
    			lis.set(end, data.get(i));
    		}
    		path.add(new int[] {end+1, data.get(i)[0]}); // 현재 길이, A값
		}
    	
    	// LIS에 포함되지 않는 값들을 넣는다.
    	Stack<Integer> s = new Stack<>();
    	int size = lis.size();
		for (int i = n-1; i >= 0; i--) {
			if (size == path.get(i)[0]) { // 경로의 lis 길이
				size--;
			} else {
				// lis에 포함되지 않는 값을 넣는다.
				s.push(path.get(i)[1]); // 경로의 A인덱스값
			}
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(n-lis.size()).append("\n");
		while (!s.isEmpty()) {
			sb.append(s.pop()).append("\n");
		}
		System.out.println(sb);
    }
}
