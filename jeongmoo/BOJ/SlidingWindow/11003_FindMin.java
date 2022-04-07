import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 백준 11003번. 최솟값 찾기
public class BOJ11003_FindMin {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
               
        // 처리
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        Deque<int[]> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {    	
        	// 빼기
        	// index 체크하여 윈도우 밖으로 나가면 제거
        	if (!deque.isEmpty() && deque.peekFirst()[1]+l <= i)
        		deque.pollFirst();

        	// 더하기 직전 체크
        	// 이전 값이 현재보다 크면 뺀다. 즉 덱의 가장 왼쪽(0)에는 최소값이 들어가야 한다.
        	int num = Integer.parseInt(st.nextToken());
        	while (!deque.isEmpty() && deque.peekLast()[0] > num)
        		deque.pollLast();
        	
        	// 더하기
        	// 덱에 num과 index 쌍으로 저장한다.
        	deque.offerLast(new int[] {num, i});
        	sb.append(deque.peekFirst()[0]).append(" ");
		}
        System.out.println(sb);
    }
}
