import java.io.*;

public class BOJ2417_정수제곱근 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long no = Long.parseLong(br.readLine());
		long ans = (long) Math.sqrt(no);
		System.out.println(ans * ans >= no ? ans : ans + 1);
		br.close();
	}

}