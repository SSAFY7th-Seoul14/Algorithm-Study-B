import java.io.*;
import java.util.*;
//BOJ / 예산  / S3 / 1시간
//https://www.acmicpc.net/problem/2512
//파라메트릭 서치에서 정확히 값 찾는 거 말고 최대값 찾는 경우, left에서 answer값 갱신해줘야 함
public class Main_2512 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] req = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i=0;i<N;i++) 
			req[i] = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());

		Arrays.sort(req); //이분탐색 전 정렬
		
		int left=0; //최소값
		int right = req[N-1]; //최대값
		int ans=0;

		while(left<=right) {
			int mid = (left+right)/2;
			long sum=0;
			for(int i=0;i<N;i++) {
				if(req[i]>mid)
					sum +=mid;
				else
					sum +=req[i];
			}
			if(sum>M)
				right=mid-1;
			else {
				left=mid+1;
				ans = Math.max(ans, mid); //최대값 갱신
			}
				
		}

		System.out.println(ans);
	}
	
	
}


