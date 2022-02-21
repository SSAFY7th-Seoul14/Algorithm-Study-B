import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//20220217
//30분

//왜 result를 스택말고 배열로 만들어 줬을 때 시간이 단축되는지 모르겠음
public class BOJ17298_오큰수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());//수열크기
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];//수열
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stack = new Stack<>();//계산과정에 쓰일 스택(큰거담아줌)
		Stack<Integer> result = new Stack<>();//결과담을 스택
		//int[] result = new result[N];// result배열로 만들었을 때 시간이 훨씬 단축됨
		
		for(int i=arr.length-1; i >= 0; i--) {//뒤에서부터 시작할 것임
			while(!stack.isEmpty() && arr[i] >= stack.peek())
				stack.pop();
			
			if(stack.isEmpty()) {
				stack.push(arr[i]);
				result.push(-1);//자신보다 오른쪽편에 큰게 없다는 뜻
			}
			else {
				result.push(stack.peek());
				stack.push(arr[i]);
			}
		}
		
		while(!result.isEmpty()) {
			sb.append(result.pop()).append(" ");
		}
		
		System.out.println(sb);
		
	}

}
