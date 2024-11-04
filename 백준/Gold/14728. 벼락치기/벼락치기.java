import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, T, arr[][], dp[][], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            arr[i][0] = K;
            arr[i][1] = S;
        }

        dp = new int[N + 1][T + 1];

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= T; j++) {
                if (j >= arr[i][0]) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - arr[i][0]] + arr[i][1]);
                else dp[i][j] = dp[i - 1][j];
            }
        }

        ans = dp[N][T];
    }
}