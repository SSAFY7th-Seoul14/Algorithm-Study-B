import java.io.*;
import java.util.*;

// BOJ/ 공유기 설치 / G5 / 30분
// https://www.acmicpc.net/problem/2110
public class Main_2110 {
	static int N, C;
	static int arr[]; //공유기 저장
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		//공유기 최대 '최소거리' 탐색 시작
		int left = 1; //최소거리
		int right = arr[N-1]-arr[0]; //최대거리
		
		int distance=0; //공유기 간격
		int res=0; //공유기 간 최대 '최소거리'(정답)
		
		while(left<=right) {
			int mid = (left+right)/2; //기준점
			int start = arr[0];
			int cnt=1; //공유기 수
			
			//공유기 간 간격이 mid(기준점)보다 크면 공유기 설치
			for(int i=1;i<N;i++) {
				distance = arr[i]-start; //공유기 간 간격
				if(distance>=mid) {
					start = arr[i];
					cnt++; //공유기 수 +1
				}
			}
			if(cnt>=C) { //공유기가 더 많으면 -> 간격 늘리기(최대 '최소 간격' 구하고 있으니 res 변수에 저장)
				res = mid;
				left = mid+1;
			}
			else { //공유기 부족하면 -> 간격 줄이기
				right = mid-1;
				
			}
		}
		System.out.println(res);
	}
}
