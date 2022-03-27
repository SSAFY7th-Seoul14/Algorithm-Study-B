package binarySearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//ÀÌºÐÅ½»ö
public class BOJ3020_°³¶Ë¹ú·¹ {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[] bottom = new int[N/2];
		int[] top = new int[N/2];
		for(int i=0; i < N/2; i++) {
			bottom[i] = Integer.parseInt(br.readLine());
			top[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bottom);
		Arrays.sort(top);
		
		
		int min = Integer.MAX_VALUE;
		int mincnt = 0;
		for(int i=1; i <= H; i++) {
			int cnt=0;
			int left = 0;
			int right = N/2-1;
			while(left <= right) {
				int mid = (left+right)/2;
				if(i <= bottom[mid]) {
					right = mid-1;
				}
				else {
					left = mid+1;
				}
			}
			cnt += N/2-left;
			
			left = 0;
			right = N/2-1;
			while(left <= right) {
				int mid = (left+right)/2;
				if(H-i+1 <= top[mid]) {
					right = mid-1;
				}
				else {
					left = mid+1;
				}
			}
			cnt += N/2-left;
			
			if(cnt < min) {
				min = cnt;
				mincnt = 1;
			} 
			else if(cnt == min) {
				mincnt++;
			}
		}
		System.out.println(min + " " + mincnt);
	}

}
