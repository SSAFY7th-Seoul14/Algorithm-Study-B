package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1966_프린터큐 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			Queue<Integer> pri = new LinkedList<>();;
			List<Integer> sortPri = new LinkedList<>();;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				int n = Integer.parseInt(st.nextToken());
				pri.add(n);
				sortPri.add(n);
			}
			Collections.sort(sortPri);
			
			int count = 0;
			while(true) {
				int pop = pri.remove();
				if(pop == sortPri.get(sortPri.size()-1)) {
					count++;
					if(M == 0) {
						break;
					}
					sortPri.remove(sortPri.size()-1);
				} else {
					pri.add(pop);
				}
				M = M==0 ? pri.size()-1 : M-1;
				
				
			}
			
			System.out.println(count);
			
		}
	}

}
