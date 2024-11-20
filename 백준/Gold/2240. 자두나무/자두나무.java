import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, W, arr[], dp[][], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arr = new int[T + 1];

        for (int i = 1; i <= T; i++) arr[i] = Integer.parseInt(br.readLine());

        dp = new int[T + 1][W + 1];

        solve();

        System.out.println(ans);
    }

    static void solve() {

        for (int t = 1; t <= T; t++) {
            int treePos = arr[t];

            for (int w = 0; w <= W; w++) {
                if (w == 0) {
                    if (treePos == 1) dp[t][w] = dp[t - 1][w] + 1;
                    else dp[t][w] = dp[t - 1][w];

                } else if (w % 2 == 0) {
                    if (treePos == 1) dp[t][w] = Math.max(dp[t - 1][w] + 1, dp[t - 1][w - 1]);
                    else dp[t][w] = Math.max(dp[t - 1][w - 1] + 1, dp[t - 1][w]);

                } else {
                    if (treePos == 2) dp[t][w] = Math.max(dp[t - 1][w] + 1, dp[t - 1][w - 1]);
                    else dp[t][w] = Math.max(dp[t - 1][w - 1] + 1, dp[t - 1][w]);
                }

                ans = Math.max(ans, dp[t][w]);
            }
        }

    }
}