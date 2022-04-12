import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 7453번. 합이 0인 네 정수
public class BOJ7453_SumZero {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] data = new int[n][4];
        
        for (int i = 0; i < n; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < 4; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        // 2개 2개씩 쌍을 만든다.
        int[] ab = new int[n*n];
        int[] cd = new int[n*n];
        for (int i = 0; i < n; i++) {
        	int a = data[i][0];
        	int c = data[i][2];
			for (int j = 0; j < n; j++) {
				ab[i*n+j] = a + data[j][1];
				cd[i*n+j] = c + data[j][3];
			}
		}
        Arrays.sort(ab);
        Arrays.sort(cd);
        
        // 투포인터
        int start = 0;
        int end = cd.length-1;
        long count = 0;
        while (true) {
        	if (start == ab.length || end == -1)
        		break;
        	
        	int sum = ab[start] + cd[end];
        	if (sum == 0) {
        		// 여기서 중복 개수를 구해서 처리하지 않으면
        		// start가 커질 때 쌍이되는 경우, end가 작아질때 쌍이되는 경우 2개중에 1개밖에 확인 안되므로
        		// 모든 중복경우를 다 체크해야한다.
        		
        		// 중간에 포인터가 바뀌므로 미리 찾는 값은 캐싱해둔다.
        		int abSum = ab[start];
        		int cdSum = cd[end];
        		
        		start = upperBound(ab, abSum);
        		long abCnt = start - lowerBound(ab, abSum);
        		end = lowerBound(cd, cdSum);
        		long cdCnt = upperBound(cd, cdSum) - end;
        		count += abCnt*cdCnt;
        		
        		// 여기서 start가 upperBound로 하나 더 커지므로 이번 반복에서는 index를 더 옮기면 안 된다.
        	} else {
        		if (sum > 0)
            		end--;
            	else
            		start++;
        	}
        }
        
        System.out.println(count);
    }
	
	public static int lowerBound(int[] data, int find) {
		int start = 0;
		int end = data.length;
		while (start < end) {
			int mid = (start+end)/2;
			if (data[mid] < find) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return end;
	}
	
	public static int upperBound(int[] data, int find) {
		int start = 0;
		int end = data.length;
		while (start < end) {
			int mid = (start+end)/2;
			if (data[mid] <= find) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return end;
	}
}
