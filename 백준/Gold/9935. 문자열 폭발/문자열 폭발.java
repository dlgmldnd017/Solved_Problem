import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static String str, target;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();

        target = br.readLine();

        solve();

        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }

    static void solve() {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.size() >= target.length()) {
                boolean check = true;

                for (int j = 0; j < target.length(); j++) {
                    if (stack.get(stack.size() - target.length() + j) != target.charAt(j)) {
                        check = false;
                        break;
                    }
                }

                if (!check) continue;

                for (int j = 0; j < target.length(); j++) stack.pop();
            }
        }

        for (Character c : stack) sb.append(c);
    }
}