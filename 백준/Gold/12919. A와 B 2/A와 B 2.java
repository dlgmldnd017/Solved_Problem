import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder S, T;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = new StringBuilder(br.readLine());
        T = new StringBuilder(br.readLine());

        solve();

        System.out.println(0);
    }

    static void solve() {
        dfs(false, T);
    }

    static void dfs(boolean isReversed, StringBuilder sb) {
        if (S.length() == sb.length()) {
            if (isReversed) sb.reverse();

            if (S.toString().equals(sb.toString())) {
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        if (isReversed) {
            if (sb.charAt(sb.length() - 1) == 'B') dfs(false, new StringBuilder(sb.substring(0, sb.length() - 1)));
            if (sb.charAt(0) == 'A') dfs(true, new StringBuilder(sb.substring(1)));
        } else {
            if (sb.charAt(0) == 'B') dfs(true, new StringBuilder(sb.substring(1)));
            if (sb.charAt(sb.length() - 1) == 'A') dfs(false, new StringBuilder(sb.substring(0, sb.length() - 1)));
        }
    }
}