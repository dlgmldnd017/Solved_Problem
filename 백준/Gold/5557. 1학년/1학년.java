import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[];
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        long dp[][] = new long[N][21];
        dp[0][arr[0]] = 1;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] <= 0) continue;

                int plus = j + arr[i];
                int minus = j - arr[i];

                if (plus <= 20) dp[i][plus] += dp[i - 1][j];
                if (minus >= 0) dp[i][minus] += dp[i - 1][j];
            }
        }

        ans = dp[N-2][arr[N-1]];
    }
}
