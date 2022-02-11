import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준2805번. 나무 자르기
public class BOJ2805_CuttingTrees {	
	static ArrayList<Integer> list = new ArrayList<>();
	static int n, m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		// 처리
		int begin=0;
        int end=1000000000;
        int answer=0;
        
        // 이진탐색
        // 인덱스나 값을 찾듯이 마찬가지로 높이를 0~최대범위에서 줄여나가면 답을 찾음.
        while(begin<=end)
        {
        	int mid = (begin+end)/2;
        	long result = search(mid);
        	
        	if(result>=m)
        	{
        		answer = mid; 
        		begin = mid+1;
        	}
        	else end = mid-1;
        }
        
		System.out.print(answer);
	}
	
	// 이 경우 search에서 모든 경우의 수를 돌기때문에 정렬을 구지 안해도 됨.
	// sum을 long으로 받아야 함을 주의.
	static long search(int h)
	{
		long sum=0;
		for(int i=0; i<n; i++)
		{ 
		   int num = list.get(i)-h;
		   if(num>0) sum+=num;	
		}
		return sum;
	}
}