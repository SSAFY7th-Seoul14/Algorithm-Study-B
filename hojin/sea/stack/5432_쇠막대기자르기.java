import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Solution {
 
    static BufferedReader in;
    static StringBuilder sb;
    static int T, ans;
    static Stack<Character> stack;
 
    public static void main(String[] args) throws Exception {
        in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
 
        T = Integer.parseInt(in.readLine().trim());
        stack = new Stack<Character>();
        char[] charList;
        for (int tc = 1; tc <= T; tc++) {
            stack.clear();
            charList = in.readLine().toCharArray();
            ans = 0;
            for (int i = 0; i < charList.length; i++) {
                if (charList[i] == '(') {
                    stack.push(charList[i]);
                    ans++;
                } else {
                    stack.pop();
                    if (charList[i - 1] == '(') {
                        ans += stack.size() - 1;
                    }
                }
            }
            sb.append("#" + tc + " " + ans + "\n");
        }
        in.close();
        System.out.println(sb);
    }
}
