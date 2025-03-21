import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, arr[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];

        for (int i = 1; i < N + 1; i++) arr[i] = Integer.parseInt(br.readLine());

        if (N == 1) ans = arr[1];
        else solve();

        System.out.println(ans);
    }

    static void solve() {
        int dp[] = new int[N + 1];
        dp[0] = 0;
        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + arr[i], arr[i - 1] + arr[i] + dp[i - 3]));
        }

        ans = dp[N];
    }
}
