import java.io.*;
import java.util.*;

public class BOJ11478_서로다른부분문자열의개수 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int len = S.length();
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < len; ++i)
			for (int j = i + 1; j <= len; ++j)
				set.add(S.substring(i, j));
		System.out.println(set.size());
		br.close();
	}

}
