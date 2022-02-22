import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		char c;
		int temp, target;
		for (int t=1; t<=test; t++)
		{
			LinkedList<Integer> list = new LinkedList<Integer>();
			int ans = 0;
			int n = scan.nextInt();
			int m = scan.nextInt();
			for (int j = 0; j < n; j++)
			{
				temp = scan.nextInt();
				list.addLast(temp);
			}
			while (m >= 0)
			{
				target = list.getFirst();
				boolean flag = true;
				// 원래는 이거 말고 이터레이터 써야함, 이건  시간 많이 걸림
				for (int i=0; i<list.size(); i++)
				{
					if (list.get(i) > target)
						flag = false;
				}
				if (!flag)  // 뒤로 보내기 
				{
					int a = list.pollFirst();
					list.addLast(a);
					if (m == 0)
						m = list.size() - 1;
					else
						m--;
				}
				else  // 출력 
				{
					list.pollFirst();
					ans++;
					if (m == 0)
						break;
					else
						m--;
				}
			}
			System.out.println(ans);
		}
	}
}
