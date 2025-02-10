import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, arr[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int dp[][] = new int[N + 1][M + 1];

        for (int i = 0; i <= N; i++) Arrays.fill(dp[i], -32768 * 100);

        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            dp[i][0] = 0;

            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j];

                int sum = 0;

                for (int k = i; k >= 1; k--) {
                    sum += arr[k];

                    if (k >= 2) dp[i][j] = Math.max(dp[i][j], dp[k - 2][j - 1] + sum);
                    else if (k == 1 && j == 1) dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }

        ans = dp[N][M];
    }
}