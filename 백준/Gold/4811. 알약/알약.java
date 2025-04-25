import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static long[][] dp = new long[31][31];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int N = Integer.parseInt(br.readLine());

            if (N == 0) return;

            System.out.println(dfs(N, 0));
        }
    }

    static long dfs(int w, int h) {
        if (w == 0 && h == 0) return 1;
        if (dp[w][h] != 0) return dp[w][h];

        long res = 0;

        if (w > 0) res += dfs(w - 1, h + 1);
        if (h > 0) res += dfs(w, h - 1);

        dp[w][h] = res;
        return res;
    }
}
