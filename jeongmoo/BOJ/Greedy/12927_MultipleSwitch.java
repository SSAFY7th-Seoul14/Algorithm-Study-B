import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준12927번. 배수 스위치
public class BOJ12927_MultipleSwitch {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		if (!str.contains("Y")) {
			System.out.println("0");
			return;
		}
		
		char[] data = str.toCharArray();
		int count = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] == 'Y') {
				count++;
				for (int j = i; j < data.length; j+=i+1) {
					if(data[j] == 'Y')
						data[j] = 'N';
					else
						data[j] = 'Y';
				}
			}
		}
		
		System.out.println(count);
	}
}
