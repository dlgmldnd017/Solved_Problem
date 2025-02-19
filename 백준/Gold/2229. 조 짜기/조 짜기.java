import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static int[] arr;

    public static void main(String[] args) throws Exception {
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

        Arrays.fill(dp, 0);

        for (int i = 0; i < N; i++) {
            int minVal = arr[i], maxVal = arr[i];

            for (int j = i; j >= 0; j--) {
                maxVal = Math.max(maxVal, arr[j]);
                minVal = Math.min(minVal, arr[j]);

                int score = maxVal - minVal;

                if (j == 0) dp[i] = Math.max(dp[i], score);
                else dp[i] = Math.max(dp[i], dp[j - 1] + score);
            }
        }

        ans = dp[N - 1];
    }
}