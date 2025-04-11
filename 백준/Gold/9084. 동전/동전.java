import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            M = Integer.parseInt(br.readLine());

            ans = 0;

            solve();

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        int[] dp = new int[M + 1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            int coin = arr[i];

            for (int j = coin; j <= M; j++) dp[j] += dp[j - coin];
        }

        ans = dp[M];
    }

}