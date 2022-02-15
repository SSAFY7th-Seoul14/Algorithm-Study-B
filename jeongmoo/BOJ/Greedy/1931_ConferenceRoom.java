import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준1931번. 회의실 배정
public class BOJ1931_ConferenceRoom {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int[][] arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());	// start
			arr[i][1] = Integer.parseInt(st.nextToken());	// end
		}
		
		// end값 오름차순 정렬. 같으면 start 오름차순
		Arrays.sort(arr, (o1, o2)->o1[1]!=o2[1]?o1[1]-o2[1]:o1[0]-o2[0]);
		
		// 종료시간 기준으로 정렬해서 1개씩 뽑기
		int count = 1;
		int index = 1; 
		int end = arr[0][1]; // 0번 선택하고 1번부터 시작
		while(index < n) {
			if(arr[index][0] >= end) {
				end = arr[index][1];
				count++;
			}
			index++;
		}
		
		System.out.println(count);
	}
}
