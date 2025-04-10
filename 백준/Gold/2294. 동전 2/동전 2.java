import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        Arrays.fill(dp, K + 1);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = arr[i]; j <= K; j++) dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
        }

        ans = dp[K] > K ? -1 : dp[K];
    }
}