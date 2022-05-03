import java.io.*;
import java.util.*;

// BOJ / 물건 팔기 / S4 / 30분
public class Main_1487 {
	static int price[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		price = new int[N][2]; // 최대금액, 배송비

		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			price[i][0] = Integer.parseInt(st.nextToken());
			price[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(price, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
		
		//각 가격 설정 시 얻을 수 있는 값 구하기
		int res = Integer.MIN_VALUE; //최대 이익을 낼 수 있는 가격
		int maxBenefit = Integer.MIN_VALUE; // 최대 이익
		for(int i=0;i<N;i++) {
			int std = price[i][0];  // 기준 가격
			int benefit = 0; // 이익
			for(int j=i;j<N;j++) {
				if(std-price[j][1]>0) {
					benefit += std-price[j][1];
				}
			}
			if(maxBenefit<benefit) {
				maxBenefit = benefit;
				res = std;
			}
		}
		if(maxBenefit<=0) // 이익 없을 때 
			System.out.println(0);
		else
			System.out.println(res);
	}
}
