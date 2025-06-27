import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
    static int N;
    static String str;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {
            sb.append("#").append(t).append(" ");

            N = Integer.parseInt(br.readLine());

            str = br.readLine();

            solve();

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            st.push(str.charAt(i));

            if (st.peek() == '(' || st.peek() == '[' || st.peek() == '{' || st.peek() == '<') continue;

            char c = st.pop();

            if (c == ')' && st.peek() == '(') st.pop();
            else if (c == ']' && st.peek() == '[') st.pop();
            else if (c == '}' && st.peek() == '{') st.pop();
            else if (c == '>' && st.peek() == '<') st.pop();
            else {
                sb.append(0);
                return;
            }
        }


        sb.append(1);
    }
}