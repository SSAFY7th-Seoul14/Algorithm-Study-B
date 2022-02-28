import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1244번. 스위치 켜고 끄기
public class BOJ1244_SwitchOnOff {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] switches = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			switches[i+1] = Integer.parseInt(st.nextToken());
		}
		
		// 처리
		int student = Integer.parseInt(br.readLine());
		for (int i = 0; i < student; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if (gender == 1) { // 남
				for (int j = 1; j <= n; j++) {
					if (j % num == 0) 
						reverse(switches, j);
				}
			} else { // 여
				reverse(switches, num);
				
				int diff = 1;
				while(true) {
					if (num-diff<1 || num+diff>n)
						break;
					if (switches[num-diff] != switches[num+diff])
						break;
					reverse(switches, num-diff);
					reverse(switches, num+diff);
					diff++;
				}
			}
		}
		
		// 출력
		for (int i = 1; i <= n; i++) {
			System.out.print(switches[i] + " ");
			if (i%20==0)
				System.out.println();
		}
	}
	
	public static void reverse(int[] switches, int idx)  {
		if (1 == switches[idx])
			switches[idx] = 0;
		else
			switches[idx] = 1;
	}
}

