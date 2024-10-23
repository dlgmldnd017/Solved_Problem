import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String X;
    static int N, ans;
    static boolean visited[];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        X = br.readLine();

        N = Integer.parseInt(X);

        visited = new boolean[N];

        ans = Integer.MAX_VALUE;

        solve(0, "");

        if (ans == Integer.MAX_VALUE) ans = 0;
        System.out.println(ans);
    }

    static void solve(int depth, String str) {
        if (depth == X.length()) {
            int tmp = Integer.parseInt(str);

            if (tmp > N) {
                if (ans > tmp) ans = tmp;
            }
            return;
        }

        for (int i = 0; i < X.length(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            solve(depth + 1, str + X.charAt(i));
            visited[i] = false;
        }
    }
}