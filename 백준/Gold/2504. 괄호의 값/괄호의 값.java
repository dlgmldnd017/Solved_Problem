import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int ans;
    static String input;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Stack<Object> st = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(' || ch == '[') st.push(ch);
            else {
                int sum = 0;

                while (!st.isEmpty()) {
                    Object top = st.pop();

                    if (top instanceof Integer) sum += (Integer) top;
                    else if (ch == ')' && top.equals('(')) {
                        st.push(sum == 0 ? 2 : sum * 2);
                        break;
                    } else if (ch == ']' && top.equals('[')) {
                        st.push(sum == 0 ? 3 : sum * 3);
                        break;
                    } else return;
                }

                if (st.isEmpty() && (ch == ')' || ch == ']')) return;
            }
        }

        while (!st.isEmpty()) {
            Object top = st.pop();

            if (top instanceof Integer) ans += (Integer) top;
            else {
                ans = 0;
                return;
            }
        }
    }
}