import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int[] dp = new int[N];
        dp[0] = arr[0];

        ans = dp[0];

        for (int i = 1; i < N; i++) {
            dp[i] = arr[i];

            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] <= arr[j]) continue;

                if (dp[i] < arr[i] + dp[j]) dp[i] = arr[i] + dp[j];
            }

            if (ans < dp[i]) ans = dp[i];
        }
    }
}