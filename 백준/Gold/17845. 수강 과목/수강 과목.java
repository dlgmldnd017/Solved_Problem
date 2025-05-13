import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, ans;
    static int[][] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int[] dp = new int[N + 1];

        for (int i = 0; i < K; i++) {
            int I = arr[i][0];
            int T = arr[i][1];

            for (int j = N; j >= T; j--) dp[j] = Math.max(dp[j], dp[j - T] + I);
        }

        ans = dp[N];
    }
}