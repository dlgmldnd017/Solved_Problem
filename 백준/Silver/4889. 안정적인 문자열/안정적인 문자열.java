import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int ans;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int i = 1;

        while (true) {
            String input = br.readLine();

            if (input.startsWith("-")) break;

            solve(input);

            sb.append(i++ + ". " + ans + "\n");

            ans = 0;
        }

        System.out.println(sb);
    }

    static void solve(String str) {
        Stack<String> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            st.push(str.charAt(i) + "");

            if (st.size() >= 2) {
                String next = st.pop();
                String prev = st.pop();

                if (next.equals("}") && prev.equals("{")) continue;

                st.push(prev);
                st.push(next);
            }
        }

        while (st.size() != 0) {
            String next = st.pop();
            String prev = st.pop();

            if (next.equals("}") && prev.equals("{")) continue;
            else if (next.equals("{") && prev.equals("}")) ans += 2;
            else ans++;
        }
    }
}