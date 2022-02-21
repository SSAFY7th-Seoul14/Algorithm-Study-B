import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//10분
//한번 풀었던 문제라 쉬웠음.
public class BOJ1931_회의실배정 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int[][] time = new int[n][2];//회의실 시작하는 시간,, 끝나는 시간 저장 배열
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			time[i][0] = Integer.parseInt(st.nextToken());
			time[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//끝나는 시간을 기준으로 오름차순 정렬, 끝나는 시간이 같다면 시작하는 시간 기준으로 오름차순 정렬(1,2)->(2,2)위해
		Arrays.sort(time, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				int result = o1[1] - o2[1];
				if(result == 0) {
					result = o1[0] - o2[0];
				}
				return result;
			}
			
		});
		
		//리스트를 돌면서 시작하는 시간이 전에 끝나는 시간보다 크거나 같으면 사용가능하므로 count++
		int count = 1;
		int end = time[0][1];
		for(int i = 1; i < n; i++) {
			if(time[i][0] >= end) {
				count++;
				end = time[i][1];
			}
		}
		System.out.println(count);

	}

}
