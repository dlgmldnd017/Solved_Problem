import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N, H, ans;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            st.nextToken();

            H = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek() > H) {
                stack.pop();
                ans++;
            }

            if (stack.isEmpty() || stack.peek() < H) {
                stack.push(H);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.pop() > 0) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
