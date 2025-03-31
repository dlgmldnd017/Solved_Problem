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
        dfs(T.toString());
    }

    static void dfs(String str) {
        if (str.length() == S.length()) {
            if (str.equals(S.toString())) {
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        if (str.endsWith("A")) dfs(str.substring(0, str.length() - 1));

        if (str.charAt(0) == 'B') dfs(new StringBuilder(str.substring(1)).reverse().toString());
    }
}