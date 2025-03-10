import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, ans;
    static int[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int[] dp = new int[K + 1];
        dp[0] = 1;

        for (int coin : arr) {
            for (int i = coin; i <= K; i++) dp[i] += dp[i - coin];
        }

        ans = dp[K];
    }
}