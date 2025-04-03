import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1_000_000_000;
    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(st.nextToken());

        dp = new int[K + 1][N + 1];

        solve();

        System.out.println(dp[K][N]);
    }

    static void solve() {
        for (int i = 0; i <= K; i++) dp[i][0] = 1;

        for (int k = 1; k <= K; k++) {
            for (int n = 0; n <= N; n++) {
                if (n > 0) dp[k][n] = (dp[k][n - 1] + dp[k - 1][n]) % MOD;
                else dp[k][n] = dp[k - 1][n];
            }
        }
    }
}
