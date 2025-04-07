import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        solve(input);

        System.out.println(ans);
    }

    static void solve(String input) {
        Stack<Object> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(' || ch == '[') stack.push(ch);
            else {
                int temp = 0;

                while (!stack.isEmpty()) {
                    Object top = stack.pop();

                    if (top instanceof Integer) {
                        temp += (Integer) top;
                    } else if (ch == ')' && top.equals('(')) {
                        stack.push(temp == 0 ? 2 : 2 * temp);
                        break;
                    } else if (ch == ']' && top.equals('[')) {
                        stack.push(temp == 0 ? 3 : 3 * temp);
                        break;
                    } else {
                        ans = 0;
                        return;
                    }
                }

                if (stack.isEmpty() && (ch == ')' || ch == ']')) {
                    ans = 0;
                    return;
                }
            }
        }

        while (!stack.isEmpty()) {
            Object o = stack.pop();
            
            if (o instanceof Integer) ans += (Integer) o;
            else {
                ans = 0;
                return;
            }
        }
    }
}
