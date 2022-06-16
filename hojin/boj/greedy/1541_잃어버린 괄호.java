import java.io.*;

public class BOJ1541_잃어버린괄호 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] charArr = br.readLine().toCharArray();
		int sumMax = 0;
		int curVal = 0;
		boolean prevMinus = false;
		for (char c : charArr) {
			switch (c) {
			case '+':
				if (prevMinus) {
					sumMax -= curVal;
				} else {
					sumMax += curVal;
				}
				curVal = 0;
				break;
			case '-':
				if (prevMinus) {
					sumMax -= curVal;
				} else {
					sumMax += curVal;
					prevMinus = true;
				}
				curVal = 0;
				break;
			default:
				curVal *= 10;
				int n = c - '0';
				curVal += n;
				break;
			}
		}
		sumMax += prevMinus ? -curVal : curVal;
		bw.write(String.valueOf(sumMax));
		bw.flush();
		bw.close();
		br.close();
	}

}
