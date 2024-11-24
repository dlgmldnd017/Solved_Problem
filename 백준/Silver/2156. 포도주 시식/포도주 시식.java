import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[], dp[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(arr[1]);
            return;
        }

        dp = new int[N + 1];

        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i] + arr[i - 1]));
        }

        ans = dp[N];
    }
} 